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
  private String NameOrId;

  public String getNameOrId() {
    return NameOrId;
  }

  public void setNameOrId(String nameOrId) {
    NameOrId = nameOrId;
  }
  public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    HttpServletRequest request = ServletActionContext.getRequest();
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(NameOrId);
    if( isNum.matches() && NameOrId.length()==5 ){
      ArrayList<Map<String,Object>> lst = conn.getBasicInfo(Integer.valueOf(NameOrId));
      for(Map<String,Object> m :lst) {
        for (String key : m.keySet()) {
          if (m.get(key)==null || m.toString().length()==0) {
            m.remove(key);
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
      else return "ERROR";
    }
    else {
      String [] name = NameOrId.split(" ");
      ArrayList<Map<String,Object>> lst = conn.getBasicInfo(name[0], name[1]);
      for(Map<String,Object> m :lst) {
        for (String key : m.keySet()) {
          if (m.get(key)==null || m.toString().length()==0) {
            m.remove(key);
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
      else return "ERROR";
    }
  }
}
