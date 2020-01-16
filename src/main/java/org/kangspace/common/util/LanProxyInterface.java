package org.kangspace.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2018/10/15 11:15
 *
 * @author kangxuefeng@etiantian.com
 */
public class LanProxyInterface {
    private static  Logger logger = Logger.getLogger(LanProxyInterface.class.getName());
    static String LOGIN_URL = "http://140.143.241.248:9990/login";
    static String CONFIG_DETAIL_URL = "http://140.143.241.248:9990/config/detail";
    /**
     * 登录
     * @param username
     * @param password
     * @author kangxuefeng@etiantian.com
     * @date 2018/10/15 11:16
     * @return  token,将token设置到cookie中,Cookie: token=c736c6c171344ae1802b0da42d3f1983

     */
    public String login(String username,String password){
        String result = http(LOGIN_URL,"POST","{\"username\":\""+username+"\",\"password\":\""+password+"\"}");
        String dataPrefix = "\"data\":\"";
        Integer dataIdx = result.indexOf(dataPrefix);
        if(dataIdx<1)
            throw new RuntimeException("登录错误,"+result);
        return result.substring(dataIdx+dataPrefix.length(),result.length()-2);


    }
    /**
     * 获取配置详情
     * @param token
     * @author kangxuefeng@etiantian.com
     * @date 2018/10/15 11:17
     * @return 配置详情内容
     */
    public String fetchConfigDetail(String token){
        return http(CONFIG_DETAIL_URL,"POST","","token="+token);
    }

    /**
     * http请求
     * @param method
     * @param param
     * @author kangxuefeng@etiantian.com
     * @date 2018/10/15 11:20
     * @return
     */
    public static String http(String url,String method,String param){
        return http(url, method, param, null);
    }

    /**
     * http请求
     * @param method
     * @param param
     * @author kangxuefeng@etiantian.com
     * @date 2018/10/15 11:20
     * @return
     */
    public static String http(String url,String method,String param,String cookie){
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent","User-Agent: Mozilla/5.0 (iPad; CPU OS 11_0 like Mac OS X) AppleWebKit/604.1.34 (KHTML, like Gecko) Version/11.0 Mobile/15A5341f Safari/604.1");

            if(cookie != null){
                connection.addRequestProperty("Cookie",cookie);
            }

            if("POST".equals(method.toUpperCase())){
                // 发送POST请求必须设置如下两行
                connection.setDoOutput(true);
                connection.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(connection.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }else{
                // 建立实际的连接
                connection.connect();
            }
            /*
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            */
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE,"发送"+method+"请求出现异常！" + e.getMessage(),e);
        } finally {
            if(out != null) out.close();
            try {
                if (in != null) in.close();
            } catch (Exception e2) {}
        }
        return result;
    }

    public static void main(String[] args) {
        LanProxyInterface lanProxyInterface = new LanProxyInterface();
        String token = lanProxyInterface.login("zstar","zaq12wsx"); //"c736c6c171344ae1802b0da42d3f1983"
        System.out.println("token:"+token);
        String detailList = lanProxyInterface.fetchConfigDetail(token);
        System.out.println(detailList);
    }
}
