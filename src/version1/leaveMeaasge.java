package version1;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class leaveMeaasge extends ActionSupport{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String id;
  private String message;
  private int history;
  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public int getHistory() {
    return history;
  }
  public void setHistory(int history) {
    this.history = history;
  }
  
  public String execute() throws SQLException {
    try {
      DBcrud conn = new DBcrud();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
      String time = df.format(new Date());
      System.out.println(time);
      HttpServletRequest request = ServletActionContext.getRequest();
      String ID1 = CookieCtrl.getCookie(request);
      if (conn.leaveMes(Integer.parseInt(ID1), Integer.parseInt(id), message, time, history)){
        return "SUCCESS";
      }
      else return "ERROR";
    } catch (Exception e) {
      return "ERROR";
    }
  }
}
