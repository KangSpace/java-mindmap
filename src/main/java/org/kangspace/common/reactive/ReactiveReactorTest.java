package org.kangspace.common.reactive;

import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * <pre>
 * Reactive编程-Reactor
 * Flux:包含0..n个元素的异步序列
 * Mono:包含0或1个元素的异步序列
 * FLux和Mono可以互相转换
 * Reference: <a href="https://projectreactor.io/docs/core/release/reference/">https://projectreactor.io/docs/core/release/reference/</a>
 * 其他操作符见: <a href="https://projectreactor.io/docs/core/release/reference/#which-operator">https://projectreactor.io/docs/core/release/reference/#which-operator</a>
 * </pre>
 */
public class ReactiveReactorTest {
    static void main(){
        debug();
//        mainTest();
//        publishOnSchedulers();
//        subscribeOnSchedulers();
//        handlingError();
        handlingErrorRetry();
//        transform();
//        context();
//        fluxCreate();
//        handle();
    }
    static void mainTest(){
        //创建Flux对象 Flux.just(..data): 指定序列中的所有元素
        Flux<Object> flux = Flux.just(Integer.valueOf(1),Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(4));
        System.out.println("Flux.just(..data) 创建:");
        subscribe(flux);
        //基于状态的generate
        flux = Flux.generate(()->0,(state,sink)->{
            sink.next("3 x " + state + " = " + 3*state);
            if (state == 10) sink.complete();
            return state + 1;
        });
        System.out.println("带状态Flux.generate(state) 创建:");
        subscribe(flux);
        //generate 只提供序列中单个消息的产生逻辑(同步通知)，其中的 sink.next()最多只能调用一次
        flux = Flux.generate(genert->{
//            for (int i = 0; i < 10; i++) {
                int i=0;
                //genert.next只能调用一次，反之会抛出java.lang.IllegalStateException: More than one call to onNext
                genert.next(i);
//            }
            genert.complete();
        });
        System.out.println("Flux.generate() 创建:");
        subscribe(flux);
        //generate 只提供序列中单个消息的产生逻辑(同步通知)，其中的 sink.next()最多只能调用一次
        flux = Flux.push(emitter->{
            for (int i = 0; i < 10; i++) {
                emitter.next(i);
            }
            emitter.complete();
        });
        System.out.println("Flux.pupush(emitter-{}) 创建:");
        subscribe(flux);
        //create 提供的是整个序列的产生逻辑，sink.next()可以调用多次(异步通知)
        //且支持onDispose,onCancel,onRequest回调
        flux = Flux.create(emiter->{
            for (int i = 0; i < 10; i++) {
                emiter.next(i);
            }
            emiter.onDispose(()->{
                System.out.println("flux Dispose");
            });
            emiter.complete();
        });
        System.out.println("Flux.create() 创建:");
        subscribe(flux);

        //flux.buffer 缓冲,满2个元素后处理
        System.out.println("Flux.buffer() 消费:");
        flux.buffer(2).subscribe(consumer->{
            System.out.println("buffer2 consumer:"+consumer.toString());
        });
        //flux.bufferUntil 缓冲过滤,元素累积直到大于3的数，返回
        System.out.println("Flux.bufferUntil() 消费:");
        flux.bufferUntil((ele)->ele instanceof Integer && (Integer)ele>3).subscribe(consumer->{
            System.out.println("bufferUntil>3 consumer:"+consumer.toString());
        });
        //flux.filter 过滤元素
        System.out.println("Flux.filter() 消费:");
        flux.filter(ele->ele instanceof Integer && (Integer)ele>3).subscribe(consumer->{
            System.out.println("buffer2 consumer:"+consumer.toString());
        });
        //flux.take 获取元素
        System.out.println("Flux.take() 消费:");
        flux.take(1).subscribe(consumer->{
            System.out.println("take consumer:"+consumer.toString());
        });
        //flux.takeWhile 获取元素
        System.out.println("Flux.takeWhile() 消费:");
        flux.takeWhile(t->{
            System.out.println(t);
            return (Integer)t<3;
        }).subscribe(consumer->{
            System.out.println("takeWhile>3 consumer:"+consumer.toString());
        });

        //Mono.just创建Mono,只有1个或0个元素
        System.out.println("Mono.just() 消费:");

        Mono mono = Mono.just(Integer.valueOf(1));
        mono.subscribe(System.out::println);

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void handle(){
//        Mono.create((callback)->{}).handle((state,sink)->{});
        Flux.create((emitter)->{
            for (int i = 0; i < 10; i++) {
                System.out.println("emit:"+i);
                emitter.next(i);
            }
            emitter.complete();
        }).handle((obj,sink)->{
            System.out.println("obj:"+obj);
        }).subscribe();
    }

    static void fluxCreate(){
        Flux.create(emiter->{
            for (int i = 0; i < 10; i++) {
                emiter.next(i);
            }
            emiter.onDispose(()->{
                System.out.println("flux Dispose");
            });
            emiter.complete();
        }).doOnNext((t)->{
            System.out.println("doOnNext:"+t);
        }).subscribe((t)->{
            System.out.println("subscribe:"+t);
        }).dispose();
    }

    static void schedulers(){
        Schedulers.single().createWorker().schedule(()->{
            System.out.println("Schedulers.single().createWorker().schedule() run");
        });
        Schedulers.newSingle("newSingle").schedule(()->{
            System.out.println("Schedulers.newSingle(\"newSingle\") run");
        });
        Flux.interval(Duration.ofMillis(300), Schedulers.newSingle("Flux.interval test"));
    }

    /**
     * <pre>
     * publishOn
     * </pre>
     */
    static void publishOnSchedulers(){
        //
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        final Flux<String> flux = Flux
                .range(1, 3)
                //运行在T1
                .map(i -> Thread.currentThread().getName()+"-"+ (10 + i))
                //切换上下文,从s中取线程
                .publishOn(s)
                //运行在s中的线程中
                .map(i -> Thread.currentThread().getName()+ ",value: " + i);

        Thread t = new Thread(() -> flux.subscribe(System.out::println),"T1");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flux.subscribe().dispose();
        s.dispose();
        System.out.println("end");
    }
    /**
     * <pre>
     * subscribeOn
     * </pre>
     */
    static void subscribeOnSchedulers(){
        //
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
        final Flux<String> flux = Flux
                .range(1, 3)
                //运行在s中的线程中
                .map(i -> Thread.currentThread().getName()+"-"+ (10 + i))
                //设置消费者从s中取线程,影响消费者执行线程,放在任何位置都会影响
                .subscribeOn(s)
                //运行在s中的线程中
                .map(i -> Thread.currentThread().getName()+ ",value: " + i);
        Thread t = new Thread(() -> flux.subscribe(System.out::println),"T1");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flux.subscribe().dispose();
        s.dispose();
        System.out.println("end");
    }

    /**
     * 错误处理
     */
    static void handlingError(){
        boolean b = Exceptions.isErrorCallbackNotImplemented(new UnsupportedOperationException());
        System.out.println("Exceptions.isErrorCallbackNotImplemented(new UnsupportedOperationException()) :"+b);
        Flux.just(1, 2, 0,3,4,5)
                .map(i -> "100 / " + i + " = " + (100 / i)) //this triggers an error with 0
                //在onErrorReturn之前定义,会触发该回调,在onErrorReturn后定义,则不会触发回调
                .doOnError((e)->{
                    System.out.println("doOnError");
                })
                .onErrorReturn(e->e.getMessage().contains("1"),"1")
                //发送错误时,处理错误,并设置返回值
                .onErrorReturn("Divided by zero :(")
                .onErrorResume((emmit)-> Flux.create((e) -> e.next("")))
                .subscribe((t)-> System.out.println("subscribe:"+t),
            //定义onErrorReturn后,subscribe的errorConsumer不会被触发
            (e)-> System.out.println("subscribe error:"+e.getMessage())); // error handling example

        Flux.just("timeout1")
                .flatMap(k -> Mono.just(k))
//                .onErrorResume(original -> Flux.error(
//                        new BusinessException("oops, SLA exceeded", original))
//                )
                ;
        Flux.just("timeout1")
                .flatMap(k -> Mono.just(k))
                .doFinally(type -> {
                    System.out.println("doFinally:"+type);
                })
//                .onErrorMap(original -> new BusinessException("oops, SLA exceeded", original))
                ;

        AtomicBoolean isDisposed = new AtomicBoolean();
        Disposable disposableInstance = new Disposable() {
            @Override
            public void dispose() {
                isDisposed.set(true);
            }

            @Override
            public String toString() {
                return "DISPOSABLE";
            }
        };
        Flux<String> flux =
                Flux.using(
                        () -> disposableInstance,
                        disposable -> Flux.just(disposable.toString()),
                        Disposable::dispose
                );

    }

    static void handlingErrorRetry() {
        Thread t = Thread.currentThread();
        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
//                .log()
                .retry(2)
                .elapsed()
                .onErrorResume((e)->{
//                    throw new RuntimeException(e);
                    return Mono.error(e);
                })
                .subscribe(System.out::println, (e)->{
                    t.interrupt();
//                    throw new RuntimeException(e);
                });

        try {
            Thread.sleep(21100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void debug() {
        Hooks.onOperatorDebug();
    }

    /**
     * <pre>
     * transform: 封装Flux操作链
     * transformDeferred: 封装Flux操作链(针对每个Subscriber单独生产操作链)
     * </pre>
     */
    static void transform(){
        Flux.just("A").transform((t)-> t.filter(tt -> tt.equals("A")));

        AtomicInteger ai = new AtomicInteger();
        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
            if (ai.incrementAndGet() == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };
        //hot操作转换为cold操作:
        Flux.defer(() -> Flux.just(""));
        //cold操作转换为hot操作:
        Flux.just("").share().replay();

        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .transformDeferred(filterAndMap);

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: " + d));
        Mono.justOrEmpty(Optional.empty()).subscribe();
    }

    static void context(){
        String key = "message";
        Mono<String> r = Mono
                .deferWithContext(ctx -> Mono.just("Hello " + ctx.get(key)))
                .subscriberContext(ctx -> ctx.put(key, "Reactor"))
                .flatMap( s -> Mono.deferWithContext(ctx ->
                        Mono.just(s + " " + ctx.get(key))))
                .subscriberContext(ctx -> ctx.put(key, "World"))
                .subscriberContext(ctx->{
                    System.out.println(Arrays.asList(ctx.stream().toArray()));
                    return ctx;
                });
        r.subscribe((val) -> {
            System.out.println(val);
        }).dispose();
    }

    /**
     * 消费数据
     */
    static void subscribe(Flux flux){
        Disposable disposable =flux.doOnNext(consumer -> {
            System.out.println("2nd Next: ->"+consumer.toString());
        }).subscribe(consumer->{
            System.out.println(consumer.toString());
        },errorConsumer->{
            System.out.println(errorConsumer.toString());
        },()->{
            System.out.println("subscribe flux complete;");
        });
        //包装一个Disposable,并处理
        Disposables.swap().update(disposable);
        //组合多个Disposable,并可一次处理这些Disposable
        Disposables.composite(disposable).dispose();
        //disposable.dispose();
    }

    public static void main(String[] args) {
        main();
    }
}
