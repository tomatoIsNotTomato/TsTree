
package version1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DBcrud {
  private String jdbcDriver = "com.mysql.jdbc.Driver";
  private String dbusername = "root";
  private String dbpassword = "2218234907";
  private String dbUrl = "jdbc:mysql://localhost:3306/tree?useSSL=false&useUnicode=true&characterEncoding=utf-8";

  public Connection connectDB() {
    try {
      Class.forName(jdbcDriver);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

    try {
      Connection connect = DriverManager.getConnection(dbUrl, dbusername, dbpassword);
      return connect;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String changeToString(HashSet<NameIdPair> set) {
    ArrayList<String> l = new ArrayList<String>();
    for (NameIdPair i : set) {
      l.add(i.getName() + "," + i.getID()+","+i.getEmail());
    }
    String setString = StringUtils.join(l, ";");
    return setString;
  }

  private HashSet<NameIdPair> changeToHashSet(String info) {
    HashSet<NameIdPair> hs = new HashSet<>();
    if (info.length() == 0) return null;
    String[] infoList = info.split(";");
    for (String s : infoList) {
      String[] metaData = s.split(",");
      NameIdPair nip = new NameIdPair(metaData[0], Integer.parseInt(metaData[1]), "");
      if (metaData.length>2)
      {
        nip.setEmail(metaData[2]);
      }
      
      hs.add(nip);
    }
    return hs;
  }

  private ArrayList<Map<String, Object>> extractInfo(ResultSet rs) {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();

    ResultSetMetaData md;
    try {
      md = rs.getMetaData();
      int column = md.getColumnCount();
      while (rs.next()) {
        Map<String, Object> rowData = new HashMap<String, Object>();
        
        for (int i = 1; i <= column; i++) {
          rowData.put(md.getColumnName(i), rs.getObject(i));
          /*if (md.getColumnName(i).equals("sex")){
            if  ((int)rs.getObject(i)==0) {
              rowData.put(md.getColumnName(i), "W");
            }
            else {
              rowData.put(md.getColumnName(i), "M");
            }
          }*/
        }
        lst.add(rowData);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lst;
  }
  
  public int checkExist(String emailOrId, String type) throws SQLException{
    Connection connect = connectDB();
    if (connect == null) {
      return -1;
    }
    String sqlStatement = "select * from userpwd where email = (?)";
    String sqlStatement1 = "select * from userpwd where linkedinId = (?)";
    PreparedStatement ps;
    try {
      if (type.equals("linkedin")) {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      }
      else 
        {
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
        }
      ps.setString(1, emailOrId);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
        return 0;
      else return 1;
    }
    catch(Exception e) {
      e.printStackTrace();
      connect.close();
      return -1;
    }
  }

  public int saveCode(String emailOrId, String pwdOrToken, String type) throws SQLException {
    Connection connect = connectDB();
    int id = -1;
    if (connect == null)
      return -1;
    String sqlStatement = "insert into userpwd (email,pwd) value(?,?)";
    String sqlStatement1 = "insert into userpwd (linkinId,token) value(?,?)";
    PreparedStatement ps;
    try {
      if (type == "linkedin")
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      else ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, emailOrId);
      ps.setString(2, pwdOrToken);
      ps.executeUpdate();
      ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
      if(rs.next()) {
        id = rs.getInt(1);
      }
      connect.close();
      return id;
    }
    catch(Exception e) {
      e.printStackTrace();
      connect.close();
      return -1;
    }
  }
  
  public Boolean insertPeriodInfo(int ID, SchoolInfo info) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return false;
    String sqlStatement = "insert into user_" + info.getPeriod() + " (" + info.getPeriod()
        + "ID, admittionDate, teacher, student, school) value(?,?,?,?,?)";
    String teacher = changeToString(info.getTeacher());
    String student = changeToString(info.getStudent());
    PreparedStatement ps;
    System.out.println(info.getPeriod());
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      ps.setDate(2, info.getAdmittionDate());
      ps.setString(3, teacher);
      ps.setString(4, student);
      ps.setString(5, info.getSchool());
      ps.executeUpdate();
      connect.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return false;
    }
  }

  public Boolean insertBasicInfo(CampusUser user) throws SQLException {
    Connection connect = connectDB();
    int id = Integer.valueOf(user.getID());
    Boolean fail = false;
    if (connect == null)
      return false;
    String sqlStatement1 = "insert into user (id, firstName, lastName, headline, location, industry, email-address, picture-url, public-profile-url) value(?,?,?,?,?,?,?,?,?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      ps.setInt(1, id);
      ps.setString(2, user.getFirstName());
      ps.setString(3, user.getLastName());
      ps.setString(4, user.getHeadline());
      ps.setString(5, user.getLocation());
      ps.setString(6, user.getIndustry());
      ps.setString(7, user.getEmail());
      ps.setString(8, user.getPicture_url());
      ps.setString(9, user.getProfile_url());

      ps.executeUpdate();

      if (!insertPeriodInfo(id, user.getBachelorPeriod()))
        fail = true;
      if (!insertPeriodInfo(id, user.getMasterPeriod()))
        fail = true;
      if (!insertPeriodInfo(id, user.getDoctorPeriod()))
        fail = true;

      connect.close();
      if (fail)
        return false;
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return false;
    }
  }

  public int loginJudge(String email, String pwd) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return 0;
    String sqlStatement = "select id from userpwd where email = (?) and pwd = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, email);
      ps.setString(2, pwd);
      ResultSet rs = ps.executeQuery();
     
      if (rs.next()) {
        connect.close();
        return rs.getInt(1);
      } else {
        connect.close();
        return -1;
      }
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return 0;
    }
  }
  
  public ArrayList<Map<String, Object>> getBasicInfo(int ID) throws SQLException {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    Connection connect = connectDB();
    if (connect == null)
      return null;
    String sqlStatement = "select * from  user where ID = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      ResultSet rs = ps.executeQuery();
      lst = extractInfo(rs);
      connect.close();
      return lst;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return null;
    }
  }
   
  public ArrayList<Map<String, Object>> getBasicInfo(String firstName, String lastName) throws SQLException {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    Connection connect = connectDB();
    if (connect == null)
      return null;
    String sqlStatement = "select * from  user where firstName = (?) and lastName = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, firstName);
      ps.setString(2, lastName);
      ResultSet rs = ps.executeQuery();
      lst = extractInfo(rs);
      connect.close();
      return lst;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return null;
    }
  }

  public HashSet<NameIdPair> queryPeriodInfo(int ID, String period, String relationShip)
      throws SQLException {
    HashSet<NameIdPair> lst = new HashSet<>();
    String info = new String();
    Connection connect = connectDB();
    if (connect == null)
      return null;
    String sqlStatement = "select "+relationShip +" from user_"+period+" where "+period+"ID = ?";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
      info = rs.getString(1);}
      if (changeToHashSet(info)!=null) {
      lst.addAll(changeToHashSet(info));}
      return lst;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return null;
    }
  }
  
  public ArrayList<Map<String, Object>> queryTsTree(int ID) {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    String[] periods = {"bachelor", "master", "doctor"};
    String[] relations = {"teacher", "student"};
    for(String period : periods) {
      for (String relation : relations) {
        HashSet<NameIdPair> hs;
        try {
          hs = queryPeriodInfo(ID, period, relation);
          if (hs==null) return null;
          
          for(NameIdPair pair : hs) {
            Map<String, Object> map = new HashMap<>();
            map.put("relation", relation);
            map.put("period", period);
            map.put("ID",pair.getID());
            map.put("name", pair.getName());
            map.put("email", pair.getEmail());
            lst.add(map);
          }
         }
         catch (SQLException e) {
          e.printStackTrace();
          return null;
        }
      }
    }
    return lst;
  }
  
  public Boolean add_t_s_info(int selfID, String name, int id, String relation, String period, String email) throws SQLException{
    try {
    Connection connect = connectDB();
    PreparedStatement ps;
    ResultSet rs;
    String sqlStatement = "select "+relation+" from user_"+period + " where "+ period+"ID = (?)";
    ps=connect.prepareStatement(sqlStatement);
    ps.setInt(1, selfID);
    
    rs=ps.executeQuery();
    if(rs.next()) { 
    
    HashSet<NameIdPair> hash=changeToHashSet(rs.getString(1));
    if (hash == null) hash = new HashSet<NameIdPair>();
    
    hash.add(new NameIdPair(name,id, email));
        String inf = changeToString(hash);

        ps = connect.prepareStatement("update user_" + period + " set " + relation + "=(?) where " + period + "ID=(?)");

        ps.setString(1, inf);
        ps.setInt(2, selfID);
        ps.executeUpdate();
        connect.close();
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public Boolean Merge(int ID1, int ID2, String name) {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    String[] periods = { "bachelor", "master", "doctor" };
    String[] relations = { "teacher", "student" };
    try {
      for (String period : periods) {
        for (String relation : relations) {
          HashSet<NameIdPair> hs;

          hs = queryPeriodInfo(ID1, period, relation);
          for (NameIdPair pair : hs) {
            if (pair.getName().equals(name)) {
              pair.setID(ID2);
              String inf = changeToString(hs);
              Connection conn = connectDB();
              String sqlStatement = "update user_" + period + " set " + relation + "=(?) where " + period + "ID=(?)";
              PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sqlStatement);
              ps.setString(1, inf);
              ps.setInt(2, ID1);
              ps.executeUpdate();
              conn.close();
            }
          }
        }
      }
      return true;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }
  
  @SuppressWarnings("null")
  public Boolean de_t_s_info(int ID,int id,String name,String period,String relation,String email) 
      throws SQLException{
    try {
    Connection connect = connectDB();
    PreparedStatement ps;
    ResultSet rs;
    String sqlStatement = "select * from user_"+period+ " where "+period+"ID = ?";
    ps=connect.prepareStatement(sqlStatement);
    ps.setInt(1, ID);
    rs=ps.executeQuery();
    
    if(rs.next()) {
      HashSet<NameIdPair> hash=changeToHashSet(rs.getString(relation));
      if (hash == null) hash = new HashSet<NameIdPair>();
      NameIdPair nana=new NameIdPair(name,id,email);
      Iterator iter=hash.iterator();
      while (iter.hasNext()) {
        NameIdPair person = (NameIdPair) iter.next();
      if (person.equals(nana)) {
      iter.remove();
      }
      }
    String str=changeToString(hash);
    ps=connect.prepareStatement("update user_"+period+" set "+relation+"=(?) where "+period+"ID=(?)");
    ps.setString(1, str);
    ps.setInt(2, ID);
    ps.executeUpdate();
    }
    return true;
  }catch (Exception e) {
    e.printStackTrace();
    return false;
  }
  }
  
  public Boolean update_t_s_info(int ID,int id,String name1,String name2,
      String period,String relation,String email_old,String email_new) 
      throws SQLException{
    try {
    Connection connect = connectDB();
    PreparedStatement ps;
    ResultSet rs;
    String sqlStatement = "select * from user_"+period + " where "+period+"ID = ?";
    ps=connect.prepareStatement(sqlStatement);
    ps.setInt(1, ID);
    rs=ps.executeQuery();
    if(rs.next()) {
      HashSet<NameIdPair> hash=changeToHashSet(rs.getString(relation));
      if (hash == null) hash = new HashSet<NameIdPair>();
      NameIdPair nana=new NameIdPair(name1,id,email_old);
      Iterator iter=hash.iterator();
      while (iter.hasNext()) {
        NameIdPair person = (NameIdPair) iter.next();
      if (person.equals(nana)) {
      iter.remove();
      }
      }
    hash.add(new NameIdPair(name2,id,email_new));
    String str2=changeToString(hash);
    System.out.println(str2);
    ps=connect.prepareStatement("update user_"+period+" set "+relation+"=(?) where "+period+"ID=(?)");
    ps.setString(1, str2);
    ps.setInt(2, ID);
    ps.executeUpdate();
    }
    return true;
  }catch (Exception e) {
    e.printStackTrace();
    return false;
  }
  }
  
  public ArrayList<CampusUser> mayKnow(int ID,String period) 
	      throws SQLException{
	    try {
	    ArrayList<CampusUser> a=new ArrayList<CampusUser>();
	    Connection connect = connectDB();
	    PreparedStatement ps;
	    ResultSet rs;
	    String sqlStatement = "select * from user_"+period + " where "+period+"ID = ?";
	    ps=connect.prepareStatement(sqlStatement);
	    ps.setInt(1, ID);
	    rs=ps.executeQuery();
	    String school=null;
	    Date admit=null;
	    if(rs.next()) {
	    	 school=rs.getString(2);
	    	 admit=rs.getDate(5);
	    }
	    sqlStatement = "select * from user_"+period + " where school = ? and admittionDate=?";
	    ps.setString(1, school);
	    ps.setDate(2, admit);
	    rs=ps.executeQuery();
	    while(rs.next()){
	    	int id=rs.getInt(1);
	    	sqlStatement = "select * from user where  ID = ?";
		    ps=connect.prepareStatement(sqlStatement);
		    ps.setInt(1, id);
		    rs=ps.executeQuery();
		    if(rs.next()) {
		    	CampusUser user=new CampusUser(rs.getString(2),rs.getString(3),rs.getString(4),
		    			rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
		    	a.add(user);
		    }
	    }
	    return a;
	  }catch (Exception e) {
	    e.printStackTrace();
	    return null;
	  }
	  }
  
  public ArrayList<CampusUser> mayKnow2(int ID,String str,int row) 
	      throws SQLException{
	    try {
	    ArrayList<CampusUser> a=new ArrayList<CampusUser>();
	    Connection connect = connectDB();
	    PreparedStatement ps;
	    ResultSet rs;
	    String sqlStatement = "select * from user where ID = ?";
	    ps=connect.prepareStatement(sqlStatement);
	    ps.setInt(1, ID);
	    rs=ps.executeQuery();
	    String string=null;
	    if(rs.next()) string=rs.getString(row);
	    
	    sqlStatement = "select * from user where "+str+" = ?";
	    ps.setString(1, string);
	    rs=ps.executeQuery();
	    while(rs.next()){
		    CampusUser user=new CampusUser(rs.getString(2),rs.getString(3),rs.getString(4),
		    			    rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
		    a.add(user);
		}
	    return a;
	  }catch (Exception e) {
	    e.printStackTrace();
	    return null;
	  }
	  }
}




