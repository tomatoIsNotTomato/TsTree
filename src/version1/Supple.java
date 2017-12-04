package version1;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

import com.opensymphony.xwork2.ActionSupport;

public class Supple extends ActionSupport{
  private String ID;
  private String name;
  private String id;
  private String relation;
  private String period;
  private String tel;
 
  
  public String getID() {
    return ID;
  }


  public void setID(String iD) {
    ID = iD;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
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


  public String getTel() {
    return tel;
  }


  public void setTel(String tel) {
    this.tel = tel;
  }


  public String execute() throws Exception{
    try {
        DBcrud conn = new DBcrud();
        ID = ID.substring(0, ID.length()-1);
        int iD = Integer.valueOf(ID);
        int intid;
        if(id .equals("")) {
          intid = -1;
        }
        else {
          intid = Integer.valueOf(id);
        }
        if (conn.add_t_s_info(iD, name, intid, relation, period, tel)) return "SUCCESS";
        else return  "ERROR";
    }catch (Exception e) {
      return "ERROR";
   
  }
  }
}
