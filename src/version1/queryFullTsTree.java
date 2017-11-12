package version1;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class queryFullTsTree extends ActionSupport{
  private String ID;
  private int count;

  public int getCount() {
    return count;
  }


  public void setCount(int count) {
    this.count = count;
  }


  public String getID() {
    return ID;
  }
  

  public void setID(String iD) {
    ID = iD;
  }
  
  private void searchTree(ArrayList<Map<String, Object>> lst, String name, int ID, int cnt){
    if (cnt>=count) {
      return;
    }
    DBcrud conn = new DBcrud();
    ArrayList<Map<String, Object>> l = conn.queryTsTree(ID);
    for(Map<String, Object> al : l) {
      al.put("self", name);
    }
    lst.addAll(l);
    /*map.put("relation", relation);
            map.put("period", period);
            map.put("ID",pair.getID());
            map.put("name", pair.getName());
            map.put("tel", pair.getTel());
            */
    for(Map<String, Object> info:l) {
      if (!info.get("ID").equals(("-1"))) {
        searchTree(lst, info.get("name").toString(), Integer.valueOf(info.get("ID").toString()), ++cnt);
      }
    }
    return;
  }
  
  public String execute() throws Exception{
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    DBcrud conn = new DBcrud();
    HttpServletRequest request = ServletActionContext.getRequest();
    ArrayList<Map<String, Object>> info = conn.getBasicInfo(Integer.parseInt(getID()));
    String name = info.get(0).get("name").toString();
    searchTree(lst, name, Integer.valueOf(ID), 0);
    if (lst != null) {
      for (Map<String, Object> l : lst) {
        if (l.get("relation").equals("teacher")) {
          list.add("{\"source\":\"" + l.get("name").toString() + "\",\"target\":\"" + l.get("self").toString() + "\",\"type\":\""
              + l.get("relation").toString() + "\",\"ID\":\"" + String.format("%0" + 5+ "d", l.get("ID") )+ "\",\"tel\":\""
              + l.get("tel").toString() + "\",\"period\":\"" + l.get("period").toString() + "\"}");
          
        } else {
          list.add("{\"source\":\"" + l.get("self").toString() + "\",\"target\":\"" + l.get("name").toString() + "\",\"type\":\""
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
      request.setAttribute("ID", ID);
      request.setAttribute("name", name);
    return SUCCESS;
  }
    else return "ERROR";
}
}
