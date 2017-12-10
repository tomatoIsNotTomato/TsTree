package version1;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Merge extends ActionSupport{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String ID1;
  private String ID2;
  private String name;
 
 
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  public String getID1() {
    return ID1;
  }
  public void setID1(String iD1) {
    ID1 = iD1;
  }
  public String getID2() {
    return ID2;
  }
  public void setID2(String iD2) {
    ID2 = iD2;
  }
  
  public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    HttpServletRequest request = ServletActionContext.getRequest();
    ID1 = CookieCtrl.getCookie(request);//request.getSession().getAttribute("userID").toString(); 
    if (conn.Merge(Integer.parseInt(ID1), Integer.parseInt(ID2), name)) return "SUCCESS";
    else return "ERROR";
  }
}
