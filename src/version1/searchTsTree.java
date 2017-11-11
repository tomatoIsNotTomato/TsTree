package version1;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class searchTsTree extends ActionSupport{
  private String ID;

  public String getID() {
    return ID;
  }


  public void setID(String iD) {
    ID = iD;
  }
  
  
  
   public String execute() throws Exception{
    DBcrud conn = new DBcrud();
    ArrayList<String> list = new ArrayList<>();
   
    
    HttpServletRequest request = ServletActionContext.getRequest();
    ArrayList<Map<String, Object>> info = conn.getBasicInfo(Integer.parseInt(getID()));
    String name = info.get(0).get("name").toString();
    ArrayList<Map<String, Object>> lst = conn.queryTsTree(Integer.parseInt(getID()));
    if (lst != null) {
      for (Map<String, Object> l : lst) {
        if (l.get("relation").equals("teacher")) {
          list.add("{\"source\":\"" + l.get("name").toString() + "\",\"target\":\"" + name + "\",\"type\":\""
              + l.get("relation").toString() + "\",\"ID\":\"" + String.format("%0" + 5+ "d", l.get("ID") )+ "\",\"tel\":\""
              + l.get("tel").toString() + "\",\"period\":\"" + l.get("period").toString() + "\"}");
          
        } else {
          list.add("{\"source\":\"" + name + "\",\"target\":\"" + l.get("name").toString() + "\",\"type\":\""
              + l.get("relation").toString() + "\",\"ID\":\"" +  String.format("%0" + 5+ "d", l.get("ID"))  + "\",\"tel\":\""
              + l.get("tel").toString() + "\",\"period\":\"" + l.get("period").toString() + "\"}");
        }
      }
    }
    
    if (lst.size()!=0){
      int i = 0;
      StringBuilder sb = new StringBuilder();
      sb.append('[');
      sb.append(list.get(0));
      
      for(i = 1; i<list.size();i++) {
        sb.append(',');
        sb.append(list.get(i));
      }
      sb.append(']');
      String jsonString = sb.toString();
      request.setAttribute("tree", jsonString);
      return SUCCESS;
    }
    else return "ERROR";
  }
  
}
