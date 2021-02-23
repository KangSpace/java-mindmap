package org.kangspace.common;

/**
 * 2019/7/22 17:44
 *
 * @author kango2gler@gmail.com
 */
public class CCAPICaller {

   /* public static void main(String[] args) {
        String roomId = "";
        String userId = "";
        String apiKey = "";
        String recordUrl = "?";
        Map<String, String> map = new TreeMap<String, String>();
        map.put("roomid", roomId);
        map.put("userid", userId);
        String thqs = CCUtil.createHashedQueryString(map, new Date().getTime(), apiKey);
        String resultJson = HttpUtil.doGet(recordUrl + thqs);
        JSONObject jsonObject = JSONObject.parseObject(resultJson);
        String resultCode = jsonObject.get("result").toString();
        String count = jsonObject.get("count").toString();
        System.out.println(jsonObject.toJSONString());
    }*/

    public static void main(String[] args) {
        String ip = "::ffff:117.136.88.197";
        if(ip.startsWith("::ffff:") && ip.indexOf(":",7) == -1){
            ip = ip.substring(7);
        }
        System.out.println(ip);
    }


}
