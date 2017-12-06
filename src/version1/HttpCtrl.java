package version1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCtrl {

public static String doGet(String strURL,String authorizationStr){
    String result="";
    try{
      URL url = new URL(strURL);// 创建连接
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setUseCaches(false);
      connection.setRequestMethod("GET"); // 设置请求方式
      connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
      connection.setRequestProperty("Accept-Language", "en_US, zh_CN, es_ES");
      connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
      connection.setRequestProperty("Authorization",authorizationStr);//对应坑2，token放到这里
      connection.connect();
      // 处理响应数据
      BufferedReader in = null;
      try {
         in = new BufferedReader(new InputStreamReader(
            connection.getInputStream(),"UTF-8"));
        String line;
        while ((line = in.readLine()) != null) {
          result += line;
        }
      } finally {
        if(in!=null) in.close();
      }
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Http Get Error");
      /*StackTraceElement stes[]=e.getStackTrace();
      log.info(HttpHelper.class.toString()+"function:doGet");
      log.info("---------------------http请求地址-----------------------");
      log.info(strURL);
      log.info("---------------------http请求内容-----------------------");
      log.info(result);
      for(StackTraceElement ste:stes){
        log.error(ste.getFileName()+":"+ste.getLineNumber()+":"+ste.getMethodName());
      }
*/    }
    return result;
  }
  public static String doPostForLinkedIn(String strURL, String jsonParams){   
    String result = "";
    try{
      URL url = new URL(strURL);// 创建连接
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setUseCaches(false);
      connection.setRequestMethod("POST"); // 设置请求方式
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置发送数据的格式
      connection.connect();
      PrintWriter out = new PrintWriter(connection.getOutputStream());
      System.out.println("helper==="+jsonParams+"===");
      try {
        out.print(jsonParams);
        out.flush();
      } finally {
        if(out!=null) out.close();
      }
      // 处理响应数据
      BufferedReader in = null;
      try {
        in = new BufferedReader(new InputStreamReader(
            connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
          result += line;
        }
      } finally {
        if(in!=null) in.close();
      }
    }catch(Exception e){
      /*log.info(HttpHelper.class.toString());
      log.info("---------------------http请求地址-----------------------");
      log.info(strURL);
      log.info("---------------------http请求内容-----------------------");
      log.info(result);
      StackTraceElement stes[]=e.getStackTrace();
      for(StackTraceElement ste:stes){
        log.error(ste.getFileName()+":"+ste.getLineNumber()+":"+ste.getMethodName());
      }
      e.printStackTrace();
    }*/
      e.printStackTrace();
      System.out.println("Http Post Error");
    }
    
    return result;
    }
  }

 