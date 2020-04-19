package org.kangspace.common.util;

/**
 * 2019/7/30 15:28
 *
 * @author kango2gler@gmail.com
 */
public class PointUtil {

    private static String SECURITY_KEY="EXEXAMMANAGE_KSXT";
    private static String DEFAULT_UID="1";

    //获取加密串，访问knewton后台管理接口用
    public static String getSign(long time){
        String sign = MD5.getMD5Result(DEFAULT_UID + time + SECURITY_KEY);
        return sign;
    }


    public static void main(String[] args) {
       /* String url = PointUtil.getLowChapterPoint(null, "837,873");
        if(StringUtils.isNotBlank(url)){
            JSONObject json = HttpUtil.sendPostUrl(url, null, "utf-8");
            if(json!=null&&json.containsKey("result")&&json.getInt("result")==1){
                JSONObject data = json.getJSONObject("data");
                if(data!=null&&data.containsKey("pointId")){

                }
            }
        }*/
        Long time = System.currentTimeMillis();

        System.out.println(time);
        System.out.println(getSign(time));
    }
}
