package org.kangspace.common;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: www.etiantian.com</p>
 * @author pluto
 * @version 1.0
 */

public class Encrypt {

  public Encrypt() {
  }
  static final public String KEY = "ettpLatforMsiZhonGJ8";

  public static byte[] encryptString(String src, String key)
  {
    try
    {
      java.security.MessageDigest md=java.security.MessageDigest.getInstance("MD5");
      md.update((src+key).getBytes("GBK"));
      return md.digest();
    }
    catch(java.security.NoSuchAlgorithmException e)
    {
      //e.printStackTrace();
      return null;
    }
    catch(java.io.UnsupportedEncodingException e)
    {
      //e.printStackTrace();
      return null;
    }
  }
  

  public static String byte2hex(byte[] b) //二行制转字符串
  {
    String hs="";
    String stmp="";
    for (int n=0;n<b.length;n++)
    {
      stmp=(Integer.toHexString(b[n] & 0XFF));
      if (stmp.length()==1) hs=hs+"0"+stmp;
      else hs=hs+stmp;
      //if (n<b.length-1)  hs=hs+":";
    }
    return hs;
  }
  
  static final public String encrypt(String str, long key)
  {
	  return Encrypt.byte2hex(Encrypt.encryptString(str, String.valueOf(key)));
  }
  static final public String encrypt(String str, String key)
  {
	  return Encrypt.byte2hex(Encrypt.encryptString(str,key));
  }

  /**
   * 返回加密后的资源路径
   * @param resourceID
   * @return
   * @throws Exception
   */
  static final public String getResourcePath(String resourceID)
  {
	  if(resourceID==null) return null;
	  String temp = MD5.getMD5Result(resourceID)+KEY;
	  return MD5.getMD5Result(temp);
  }
  static final public String getResourcePath(long resourceID)
  {
	  if(resourceID<0) return null;
	  return getResourcePath(""+resourceID);
  }
}
