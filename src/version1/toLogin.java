package version1;

import java.io.Serializable;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class toLogin extends ActionSupport implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String clientId = "81j634z5c30h61";
  private String scope = "r_basicprofile%20r_emailaddress";
 
  
  public String redirect() {
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    String response_type = "code";
    String status = linkedInCtrl.generateRandomString(20);
    String baseUrl = "https://www.linkedin.com/uas/oauth2/authorization";
    String basePath = linkedInCtrl.getBaseUrl(request);
    
    //String redirectUri = basePath + "/linkedInProcess";
    String redirectUri = "http://localhost:8080/Ttree/LoginWithLinkedIn";
    //String redirectUri =  "http://tomato.applinzi.com/Ttree/LoginWithLinkedIn";
    HttpSession session = request.getSession();
    session.setAttribute("linkStatus", status);
    linkedInCtrl.directToLoginPage(status, response_type, redirectUri, clientId, scope, baseUrl, response);
    return null;
  }
  
}
