package version1;

import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class linkedInCtrl {
  private static String basicInfor = ":(id,first-name,last-name,headline,location,industry,picture-url,public-profile-url,email-address)";

  public static String generateRandomString(int length) {
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";     
    Random random = new Random();     
    StringBuffer sb = new StringBuffer();     
    for (int i = 0; i < length; i++) {     
        int number = random.nextInt(base.length());     
        sb.append(base.charAt(number));     
    }     
    return sb.toString(); 
  }
  
  public static String getBaseUrl( HttpServletRequest request ) {
    int serverPost = request.getServerPort();
    String basePath = null;
    
    if (serverPost == 80) {
      basePath = request.getScheme() + "://" + request.getServerName() + request.getContextPath();
    } else {
      basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + request.getContextPath();
    }
    return basePath;
  }
  
  public static Boolean directToLoginPage(String state, String response_type, String redirectUri, String client_id, String scope, String baseUrl, HttpServletResponse response) {
    try {  
      String redirectUriCode = URLEncoder.encode(redirectUri,"utf-8");//坑1  
      baseUrl+="?client_id="+client_id+"&redirect_uri="+redirectUriCode+"&response_type="+response_type+"&scope="+scope+"&state="+state+"&format=json";  
      response.sendRedirect(baseUrl);  
      return false;
      } catch (Exception e) {  
      System.out.println("URLEncoder重定向地址失败，请求取消");  
      e.printStackTrace();  
      return false;  
    } 
  }
  
  public static JSONObject getAccessToken(String baseUrl,String clientId,String secret,String code,String grantType,String redirectUri){  
    try {  
    String redirectUriCode = URLEncoder.encode(redirectUri,"utf-8"); 
    baseUrl+="?client_id="+clientId+"&client_secret="+secret+"&code="+code+"&grant_type="+grantType+"&redirect_uri="+redirectUriCode+"&format=json";  
    String resultJson=HttpCtrl.doPostForLinkedIn(baseUrl,"");  
    return JSONObject.fromObject(resultJson);  
    } catch (Exception e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
        return null;  
    }     
  }  
  
  public static JSONObject getUserInfo(String baseUrl,String accessToken){  
    String resultJson=HttpCtrl.doGet(baseUrl+basicInfor+"?"+"format=json","Bearer "+accessToken);//坑2，token放到这里  
System.out.println(resultJson);
    return JSONObject.fromObject(resultJson);  
}  
}
