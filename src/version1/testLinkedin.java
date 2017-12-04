package version1;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

public class testLinkedin extends ActionSupport{
  
  private String clientId = "81j634z5c30h61";
  private String clientSecrte = "Hy6SEzFPBnuqXUhx";
  public String execute() {
  //HttpServletRequest request = ServletActionContext.getRequest();
  String getTokenUrl="https://www.linkedin.com/uas/oauth2/accessToken"; 
 //String getUserInfoUrl="https://api.linkedin.com/v1/people/~";  
  String code = "AQTc1q1GJ4A28k_rJm2bnp1VQB3rONtL4_MCW__oDWrk5OZhIYAP1fR34Ke9NkUkC6UFjI9MpujjcQK5ziYXfFColSlO8WnPT-fyxoFc6WOodcd07MDm3ENfFgeNb8YnWY9T_-QJpteV-7hZxUb0Q-EW2ZgtBg";
  //String basePath = linkedInCtrl.getBaseUrl(request);
  String redirectUri = "http://localhost8080/Ttree/LoginWithLinkedIn";
  JSONObject tokenInfo=linkedInCtrl.getAccessToken(getTokenUrl, clientId, clientSecrte, code, "authorization_code", redirectUri);  

 return "SUCCESS";
              }
}
