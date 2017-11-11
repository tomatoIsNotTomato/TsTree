package version1;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SearchBasicInfo extends ActionSupport{
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
      if (lst!=null) {
        request.setAttribute("BasicInfo", lst);
        return SUCCESS;
      }
      else return "ERROR";
    }
    else {
      ArrayList<Map<String,Object>> lst = conn.getBasicInfo(NameOrId);
      if (lst!=null) {
        request.setAttribute("BasicInfo", lst);
        return SUCCESS;
      }
      else return "ERROR";
    }
  }
}
