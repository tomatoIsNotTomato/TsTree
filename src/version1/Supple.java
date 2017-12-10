package version1;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Supple extends ActionSupport{
  private String firstName;
  private String lastName;
  private String id;
  private String relation;
  private String period;
  private String email;


  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public String getRelation() {
    return relation;
  }


  public void setRelation(String relation) {
    this.relation = relation;
  }


  public String getPeriod() {
    return period;
  }


  public void setPeriod(String period) {
    this.period = period;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String execute() throws Exception{
    try {
        DBcrud conn = new DBcrud();
        HttpServletRequest request = ServletActionContext.getRequest();
        String ID = CookieCtrl.getCookie(request);//request.getSession().getAttribute("userID").toString(); 
      
        int iD = Integer.valueOf(ID);
        int intid;
        if(id .equals("")) {
          intid = -1;
        }
        else {
          intid = Integer.valueOf(id);
        }
        if (conn.add_t_s_info(iD, firstName, lastName, intid, relation, period, email)) return "SUCCESS";
        else return  "ERROR";
    }catch (Exception e) {
      return "ERROR";
   
  }
  }
}
