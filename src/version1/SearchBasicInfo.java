package version1;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SearchBasicInfo extends ActionSupport{
  private String id;
  private String firstName;
  private String lastName;

 
  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


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


  public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    ArrayList<Map<String,Object>> lst = new ArrayList<>();
    HttpServletRequest request = ServletActionContext.getRequest();
    if (firstName.equals("")) {
      lst = conn.getBasicInfo(lastName, 1);
    }
    else if (lastName.equals("")) {
      lst = conn.getBasicInfo(firstName, 2);
    }
    else {
      lst = conn.getBasicInfo(lastName, firstName);
    }
     if (lst!=null || lst.size()!=0) {
       for(Map<String,Object> m :lst) {
         for (String key : m.keySet()) {
           if (m.get(key)==null || m.toString().length()==0) {
            
             m.put(key,"To be upplemented");
           }
           System.out.println("key= "+ key + " and value= " + m.get(key));
         }
       }
       if (lst.size()!=0) {
         request.setAttribute("BasicInfo", lst);
         request.setAttribute("ID", lst.get(0).get("id").toString());
         return SUCCESS;
       }
     }
     
     return "ERROR";
    }
  }

