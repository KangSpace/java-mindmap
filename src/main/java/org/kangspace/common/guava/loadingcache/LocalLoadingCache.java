package org.kangspace.common.guava.loadingcache;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <pre>
 * 本地缓存LoadingCache的使用:
 * 核心内容:
 * 1. LoadingCache 实现了ConcurrentMap,其中使用segment方式(segment实现ReentrantLock类似JDK1.8前的ConcurrentHashMap)实现了并发map的读写
 * 2. 缓存定时到期是在写入期间定期维护,偶尔在读取期间维护,即只有在读取或写入缓存时,才会触发校验旧缓存的有效性,如:设置expireAfterWrite(1s),则1s后缓存不会自动失效
 *      而是在,get()/put()时校验缓存是否失效,然后重新load新数据
 * 3. 缓存失效并load新值时,会触发removalListener,该事件是由ConcurrentLinkedQueue消息队里负责处理{@link LocalCache#removalNotificationQueue},
 *      先将数据加入到移除通知队列,然后load新数据
 *
 * 测试:
 * 1. LoadingCache的正常使用 OK
 * <code>
 *         //获取或刷新缓存数据
 *         mapLoadingCache.get("1");
 *         //更新缓存
 *         mapLoadingCache.put("1",new HashMap<>());
 *          //使某个缓存失效
 *         mapLoadingCache.invalidate("1");
 *         //使多个缓存失效
 *         mapLoadingCache.invalidateAll(Arrays.asList(args));
 *         //所有缓存失效
 *         mapLoadingCache.invalidateAll();
 *         //刷新缓存
 *         mapLoadingCache.refresh("1");
 * </code>
 * 2. LoadingCache的失效情况
 * 定时到期是在写入期间定期维护，偶尔在读取期间维护
 * a. 超过 maximumSize 数的新缓存,缓存将尝试逐出最近或经常未使用的条目。
 * b. expireAfterWrite 在数据写入一定时间后失效
 * c. expireAfterAccess 在数据访问一定时间后失效,多次访问会刷新最后访问时间
 * d. invalidate , invalidateAll,invalidateAll 设置缓存失效,该操作直接删除缓存(即删除map中的数据),会触发removalListener事件
 * 3. LoadingCache并发情况下对一个超时Key的加载情况
 * {@link LocalCache#getOrLoad(Object key)} 中相同Key
 * a. 新缓存加入时,LoadingCache(实现同JDK1.8前segment分段锁)多线程同步实现,{@link LocalCache.Segment#lockedGetOrLoad}加锁,
 *    在createNewEntry=true时,会先设置当前key loading状态（loadingValueReference = new LocalCache.LoadingValueReference();),
 *    然后解锁,进行下一步调用load加载数据或waitForLoadingValue等待数据加载完成
 * b.load(),reload(),refresh()的同步情况
 *      1. load()
 *      load操作总是同步的
 *      2.reload
 *      reload是之前cache中有值，需要刷新该值，比如设置了expireAfterXXX之后，到了缓存过期需要更新的时间。
 *      手动调用refresh方法的时候也会触发reload。
 *      reload是异步操作,返回{@link LocalCache.Segment#loadAsync }
 *      3.refresh
 *      手动调用了refresh，会导致loadingcache的重新load操作。调用的是Segment中的refresh方法，
 *      里面有loadAsync方法，LoadingValueReference的loadFuture中会根据之前cache中是否有值来决定load或者reload。
 * c. <b>并发环境下,多个线程同时调用同一个key的mapLoadingCache.get(key);方法时,只有一个线程进行load,reload操作,其他线程阻塞,直到load完成</b>
 *      {@link LocalCache.Segment#lockedGetOrLoad} this.lock(),createNewEntry ,valueReference.isLoading() 标记当前缓存是否在加载中:
 *      先加锁,然后进行如下2步骤:
 *          1. createNewEntry = true 时,先保存当前key到table中,然后设置loading状态标记,之后正常load加载新数据
 *          2. createNewEntry = false 调用waitForLoadingValue,等待缓存加载完成后获取值
 * 3. LoadingCache.CacheBuild#load()方法中若抛出异常,则会影响整个LoadingCache的缓存
 * </pre>
 */
public class LocalLoadingCache {
    static LoadingCache<String, Map<String, String>> mapLoadingCache = CacheBuilder.newBuilder()
            //最大数据条数,超过后,缓存将尝试逐出最近或经常未使用的条目
            //缓存先移除再加入新缓存
            .maximumSize(5)
            //在最后一次访问后的一段时间内过期
            //指定在条目创建，其值的最新替换或最后一次访问后经过固定的时间后，应自动将每个条目从缓存中删除。
            //所有缓存读取和写入操作（包括Cache.asMap().get(Object)和Cache.asMap().put(K, V)）都会重置访问时间
            .expireAfterAccess(10, TimeUnit.SECONDS)
            //缓存创建后的一段时间后过期,只有查询时,才会触发删除缓存操作
            //缓存失效后不会删除缓存,而是会再下次获取缓存时,先检查缓存是否有效,删除旧缓存,再保存新缓存
            //定时到期是在写入期间定期维护，偶尔在读取期间维护
//            .expireAfterWrite(10, TimeUnit.SECONDS)
            //缓存创建后的一段时间后刷新,只有查询时,才会触发刷新
            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<Object, Object>() {
                @Override
                public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
                    System.out.println(MessageFormat.format("缓存被移除监听:[thread:{0},time:{1},cache:{2}]",
                            Thread.currentThread(), new Date(), removalNotification.toString()
                    ));
                }
            })
            .build(new CacheLoader<String, Map<String, String>>() {
                @Override
                public Map<String, String> load(String loadingCacheKey) {
                    //缓存不存在时加载新数据
                    System.out.println(MessageFormat.format("load 新缓存:[thread:{0},time:{1},loadingCacheKey:{2}]",
                            Thread.currentThread(), System.currentTimeMillis(), loadingCacheKey
                    ));
                    if (loadingCacheKey.equals("2")) {
                        throw new RuntimeException(loadingCacheKey + " exception");
                    }
                    //load 等待1s
                    try {
                        Thread.sleep(1*1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return ImmutableMap.of(loadingCacheKey, new Date().toString());
                }
            });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        concurrentLoadNewValueTest();
        commonUseTest();
    }

    /**
     * <pre>
     * 并发下load新值测试
     * 即:同一个key高并发下,load是否是同步处理
     * </pre>
     */
    public static void concurrentLoadNewValueTest() throws InterruptedException {
        ExecutorService pool = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            pool.execute(()->{
                getCache("1");
            });
        }
        Thread.sleep(1000*1000L);
        pool.shutdown();
    }

    private static String getCache(String key){
        Map<String,String> result = new HashMap<>();
        try {
            System.out.println("Thread:"+Thread.currentThread()+" getCache:"+key+","+System.currentTimeMillis()+" start");
            result = mapLoadingCache.get(key);
            System.out.println("Thread:"+Thread.currentThread()+" getCache:"+key+","+System.currentTimeMillis()+" end");

        } catch (ExecutionException e) {
//            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 基本使用测试
     */
    public static void commonUseTest()throws ExecutionException, InterruptedException{
        System.out.println("1:"+getCache("1"));
        try {
            System.out.println("2:" + getCache("2"));
        } catch (Exception e) {

        }
        new Thread(()->{
            String cache3 = getCache("3");
            System.out.println("Thread:"+Thread.currentThread()+",cache3:"+cache3);
        },"load异常后其他线程访问Thread").start();
        System.out.println("3:"+getCache("3"));
        System.out.println("4:"+getCache("4"));
        System.out.println("等待9s");
        Thread.sleep(9*1000L);
        //等待缓存创建后刷新缓存
        //此处会重新刷新缓存,因为配置了.refreshAfterWrite(3, TimeUnit.SECONDS)
        System.out.println("1:"+getCache("1"));
        System.out.println("2:"+getCache("2"));
        System.out.println("3:"+getCache("3"));
        System.out.println("4:"+getCache("4"));
        System.out.println("等待1s");
        Thread.sleep(1*1000L);

        System.out.println("9s后重新访问1,再等待1s,查看expireAfterAccess最后访问时间是否刷新, 1:"+mapLoadingCache.get("1"));

        System.out.println("第二次等待4s");
        Thread.sleep(4*1000L);
        System.out.println("查看是否会触发refreshAfterWrite,1:"+mapLoadingCache.get("1"));

        //更新缓存
        mapLoadingCache.put("1",new HashMap<>());
        //使某个缓存失效
        mapLoadingCache.invalidate("1");
        //使多个缓存失效
        mapLoadingCache.invalidateAll(Arrays.asList("1"));
        //所有缓存失效
//        mapLoadingCache.invalidateAll();
        //刷新缓存
        mapLoadingCache.refresh("1");
        mapLoadingCache.refresh("1");

    }

}
