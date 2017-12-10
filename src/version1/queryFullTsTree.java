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
  
  
  private boolean checkRed(ArrayList<relatedPerson> node, String name) {
    for (relatedPerson l : node) {
      if (l.getName().equals(name)) {
       return true;
      }
    }
    return false;
  }
  
  private int find(ArrayList<relatedPerson> node, String name, String email) {
    for (int i = 0; i<node.size(); i++) {
      relatedPerson l = node.get(i);
      if (l.getName().equals(name)) {
        if (email.equals("") || email.equals(l.getEmail())) {
          return i;
        }
      }
    }
    return -1;
  }
  
  private void searchTree(ArrayList<Map<String, Object>> lst, String name, int ID, int cnt){
    if (cnt>=count) {
      return;
    }
    DBcrud conn = new DBcrud();
    ArrayList<Map<String, Object>> l = conn.queryTsTree(ID);
    for(Map<String, Object> al : l) {
      al.put("count", cnt);
      al.put("patName", name);
    }
    lst.addAll(l);
    cnt++;
    for(Map<String, Object> info:l) {
      if (!info.get("ID").toString().equals(("-1"))) {
        searchTree(lst, info.get("name").toString(), Integer.valueOf(info.get("ID").toString()), cnt);
      }
    }
   
    return;
  }
  
  public String execute() throws Exception{
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();
    ArrayList<relatedPerson> node = new ArrayList<>();
    DBcrud conn = new DBcrud();
    HttpServletRequest request = ServletActionContext.getRequest();
    Map<String, Object> info = conn.getBasicInfo(Integer.parseInt(getID())).get(0);
    String name = info.get("lastName").toString()+" "+info.get("firstName").toString();
    searchTree(lst, name, Integer.valueOf(ID), 0);
    
    relatedPerson sel = new relatedPerson();
    sel.setName(name);
    sel.setId(String.format("%0" + 5 + "d", info.get("id")));
    if (info.get("emailAddress")!=null) {
      sel.setEmail(info.get("emailAddress").toString());
    }
    // sel.setPic(info.get("pictureUrl").toString());;
    sel.setWeight("10");
    sel.setPic(info.get("pictureUrl").toString());
    node.add(sel);
    
    
    if (lst != null) {
      for (Map<String, Object> l : lst) {
        relatedPerson person = new relatedPerson();
        person.setName(l.get("name").toString());
        person.setId(String.format("%0" + 5+ "d", l.get("ID") ));
        person.setEmail(l.get("email").toString());
        person.setPeriod(l.get("period").toString());
        person.setRelation(l.get("relation").toString());
        person.setWeight(Double.toString(10.0/(2+Integer.parseInt(l.get("count").toString()))));
        person.setPic(conn.getPicUrl(Integer.parseInt(l.get("ID").toString())));
        
        if(!checkRed(node, person.getName())) {
          node.add(person);
        if (l.get("relation").equals("teacher")) {
          list.add("{source:"+find(node, l.get("patName").toString(), "")+",target:" +find(node, l.get("name").toString(),  l.get("email").toString())+ ",relation:\"teacher\"}");
          
        } else {
          list.add("{source:" + find(node, l.get("name").toString(),  l.get("email").toString()) + ",target:"+find(node, l.get("patName").toString(), "")+",relation:\"student\"}");
        }
        
        }
      }
    }
    
    StringBuilder sp = new StringBuilder();
    sp.append('[');
    relatedPerson r = node.get(0);
    sp.append("{name:\""+r.getName()+"\",email:\""+r.getEmail()+"\",ID:\""+r.getId()+"\",wei:"+Integer.parseInt(r.getWeight())+",\"pic\":\""+r.getPic()+"\"}");
    for(int i = 1; i<node.size(); i++) {
      r = node.get(i);
      sp.append(',');
      sp.append("{name:\""+r.getName()+"\",email:\""+r.getEmail()+"\",ID:\""+r.getId()+"\",period:\""+r.getPeriod()+"\",relation:\""+r.getRelation()+"\",wei:"+Double.parseDouble(r.getWeight())+",\"pic\":\""+r.getPic()+"\"}");
    }
    sp.append(']');
    
    if (lst != null && lst.size()!=0){
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
      request.setAttribute("node", sp.toString());
      request.setAttribute("ID", String.format("%0" +5 + "d", Integer.parseInt(ID) ));
      request.setAttribute("name", name);
    return SUCCESS;
  }
    else return "ERROR";
}
}
