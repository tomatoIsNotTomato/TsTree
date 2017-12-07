package version1;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class ShowMessageList {
  private ArrayList<Map<String, String>> messages;
  public ArrayList<Map<String, String>> getMessages() {
    return messages;
  }
  public void setMessages(ArrayList<Map<String, String>> messages) {
    this.messages = messages;
  }
  public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    HttpServletRequest request = ServletActionContext.getRequest();
    String id = CookieCtrl.getCookie(request);//request.getSession().getAttribute("userID").toString(); 
    messages = conn.getMessList(Integer.parseInt(id));
    
    if (messages!=null && messages.size()!=0) {
      for( Map<String, String> m:messages) {
        conn.alreadyRead(Integer.parseInt(m.get("mesID")));
      }
      return "SUCCESS";
    }
    else return "ERROR";
  }
}
