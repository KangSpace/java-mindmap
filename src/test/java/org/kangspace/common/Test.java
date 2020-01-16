package org.kangspace.common;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.kangspace.common.config.ConfigHelper;
import org.kangspace.common.shadowsocks.Shadowsocks;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangxuefeng@etiantian.com
 * @desc
 * @date 2017/4/28 10:22
 */
@RunWith(JUnit4.class)
public class Test {

    @org.junit.Test
    public void configHelperTest() {
        ConfigHelper c1 = new ConfigHelper("mongo");
        System.out.println(c1.getValue("MONGO_HOST"));
        ConfigHelper c2 = new ConfigHelper("test");
        System.out.println(c2.getValue("MONGO_HOST"));

        System.out.println(c1.getValue("MONGO_HOST"));
    }

    @org.junit.Test
    public void shadowsockTest() {
        String[] args = {"1080", "20dot.com", "80", ""};
        Shadowsocks.main(args);
    }

    /**
     * 正则测试,域名http替换为https
     *
     * @author kangxuefeng@etiantian.com
     * @date 2017/9/11 14:40
     */
    @org.junit.Test
    public void regexpTest() {
        String str = "http://school.etiantian.com/aixue21/im2.0.1http://school.etiantian.com/?m=getStudyTaskInfo2.do&jid=8456072&schoolId=53095&taskId=-7763934775967&time=1504780191744&sign=NTcxNDU3OTI0NGIzYWVmMzM1ZDRkZTJkMmIyMDU2MDQ";
        //str.replaceAll("http://(!?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)?)","https://$1")
        str.replace("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$", "");
        System.out.println(str.replaceAll("http(://?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)?)", "https$1"));
    }

    @org.junit.Test
    public void mapTest() {
        Map<Integer, String> map = new HashMap<>();
        Integer i = null;
        System.out.println(map.get(i));
    }

    @org.junit.Test
    public void mapTest2() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        System.out.println(map);
    }

    @org.junit.Test
    public void Inte() {
        System.out.println(new Float(11.11).intValue());
    }

    @org.junit.Test
    public void Inte2() {
        System.out.println(11F / 3);
    }

    @org.junit.Test
    public void instanceOf() {
        List<Long> list = null;

        System.out.println(list instanceof List);
        System.out.println(List.class.isInstance(list));
    }

    @org.junit.Test
    public void classNameTest() {
        System.out.println(Test.class.getName());
    }

    @org.junit.Test
    public void urlTest() {
        System.out.println(Test.class.getName());
    }

    private static String KEY = "ettTimeBomb20060525-explosive";// 密钥

    private static String sooonerKEY = "EttCDN19d88658663f38bySoooner";//北京电信cdn的验证key   soooner公司

    private static String URI_PREFIX = "/security/";// 通知lighty此为加密地址

    public static int RESTYPE_COMMON = 1;// 加密的资源类型 普通资源

    public static int RESTYPE_VIDEO = 2;// 视频资源

    public static int RESTYPE_HD = 3;// 高清课件资源

    /**
     * 计算加密后的uri
     *
     * @param serverLoc   服务器地址。如 http://www.etiantian.com
     * @param resPath     资源的路径，如 e243b13bbb031b5bb71f70fe9ba4de8f/；vga512051106/x_x_x/
     * @param resFileName 文件名  400.mp4, 2000.mp4 , cover.jpg
     * @return serverLoc + 加密地址标记 + md5 + time + resPath + resFileName
     */
    public static String calHDResourceUriBak(String serverLoc, String resPath, String resFileName) {
        String filepathelement = null;//
        String resFileLoc = null;// 资源相对地址

        filepathelement = "/etthd/";
        filepathelement += resPath + resFileName;
        //路径+文件名全加密
        resFileLoc = new StringBuffer().append(filepathelement).toString();


        // 当前时间的十六进制值
        String hexTime = Long
                .toHexString(System.currentTimeMillis() / 1000);
        // 加密，生成验证值
        String md5 = Encrypt.byte2hex(Encrypt.encryptString(KEY
                + filepathelement, hexTime));
        // 合成加密后的合法uri
        return new StringBuffer().append(serverLoc).append(URI_PREFIX).append(
                md5).append("/").append(hexTime).append(resFileLoc).toString();
    }

    public static Map<String, String> m3u8ResourceUriA(String p, String tsT, String h) throws Exception {

        Map<String, String> rtnMap = new HashMap<String, String>();
        final String ettKEY = "ettTimeBomb20060525-explosive";// 密钥
        //当前时间
        long expireSec = 60 * 60 * 12;//时间过期值，默认12h
        long expireTime = (System.currentTimeMillis() / 1000) + expireSec;
        String fp = "/hls/" + p + "/" + tsT;


        MessageDigest md = MessageDigest.getInstance("MD5");
        String md5Str = ettKEY + fp + expireTime;
        md.update(md5Str.getBytes("UTF-8"));
        byte[] enc = md.digest();
        String md5A = new sun.misc.BASE64Encoder().encode(enc);
        String md5B = md5A.replace("+", "-").replace("/", "_").replace("=", "");
        //String encT = "http://hls.hd.etiantian.com" + fp + "?st=" + md5B + "&e=" + expireTime;
        String host = "http://hls.hd.etiantian.com";
        if (h.indexOf("http://hd.etiantian.com") == -1)
            host = h;

        String encT = host + fp + "?st=" + md5B + "&e=" + expireTime;

        rtnMap.put("fileName", tsT);
        rtnMap.put("fullPath", encT);

        return rtnMap;
    }

    @org.junit.Test
    public void mp4Test() throws Exception {
        System.out.println(calHDResourceUriBak("http://us1.hd.etiantian.com", "hwjj000435/", "cover.jpg"));//400.mp4
        System.out.println(m3u8ResourceUriA("hwjj000435", "400.mp4", "http://us1.hd.etiantian.com"));
    }
    @org.junit.Test
    public void tryTest() {
        System.out.println(tryTestRun());
    }

    public int tryTestRun() {
        try {
            return 1;
        } catch(Exception e) {
            return 2;
        }finally{
            return 3;
        }
    }
    @org.junit.Test
    public void jsonTest(){
        String json = "";
    }
    @org.junit.Test
    public void encode() throws Exception {
//        String key = "e4dd80ede2710366e2ba1a672f801e8a051bbf4a";
//        String key = "251b9378f53534484e257695a441b901";
        String key = "ba0c40f9d4551706f3594c7209f232f30b268e6efbcdb789a8b3dff817616d25";
        String b = "74CwfJd4+7LYgFhXi1cx0IQC35UQqYVFycCE+EVyw1E=";
        String c = "bHUvfbiVZUmm2sQRKwiAcw==";
        key = new BASE64Encoder().encode(key.getBytes());
        key = key.substring(0, 16);
        b = aes(b,key);
        c=  aes(c,key);
        System.out.println("b = "+b);
        System.out.println("c = "+c);
    }


    public String aes(String str, String key) throws Exception {
        try {
            Key a = new SecretKeySpec(key.getBytes(),"AES");
            byte[] decode = Base64.getDecoder().decode(str);
            Cipher instance = Cipher.getInstance("AES/CBC/NoPadding");
            instance.init(2, a, new IvParameterSpec("DOUBANFRODOAPPIV".getBytes()));
            return Base64.getEncoder().encodeToString(instance.doFinal(decode));
//            return new String(instance.doFinal(decode));
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }

    }

    public String aes1(String key, String str) throws Exception {
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("utf-8")));
    }

    public String hmac(String str, String str2) throws Exception {
//        byte[] raw = str.getBytes("utf-8");
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "HmacSHA1");
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKeySpec signinKey = new SecretKeySpec(str.getBytes(), HMAC_SHA1_ALGORITHM);
        //用给定密钥初始化 Mac 对象
        mac.init(signinKey);
        //完成 Mac 操作
        return Base64.getEncoder().encodeToString(mac.doFinal(str2.getBytes()));
    }

    @org.junit.Test
    public void graph(){
        Font[] f= GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        for(Font dd:f)
        {
            System.out.println(dd);
        }
    }
}
