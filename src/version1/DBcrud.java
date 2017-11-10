package version1;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DBcrud {
  private String jdbcDriver = "com.mysql.jdbc.Driver";
  private String dbusername = "root";
  private String dbpassword = "2218234907";
  private String dbUrl = "jdbc:mysql://localhost:3306/tstree?useSSL=false";

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
      l.add(i.getName() + "," + i.getID());
    }
    String setString = StringUtils.join(l, ";");
    return setString;
  }

  private HashSet<NameIdPair> changeToHashSet(String info) {
    HashSet<NameIdPair> hs = new HashSet<>();
    String[] infoList = info.split(";");
    for (String s : infoList) {
      String[] metaData = s.split(",");
      NameIdPair nip = new NameIdPair(metaData[0], Integer.parseInt(metaData[1]), metaData[2]);
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
        }
        lst.add(rowData);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lst;
  }

  public int saveCode(String name, String pwd) throws SQLException {
    Connection connect = connectDB();
    int id = -1;
    if (connect == null)
      return -1;
    String sqlStatement = "insert into userpwd (name, pwd) value(?,?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, name);
      ps.setString(2, pwd);
      ps.executeUpdate();

      ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
      if (rs.next())
        id = rs.getInt(1);
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
    String sqlStatement1 = "insert into user (id, name, birthDay, sex, place, phoneNumber, job) value(?,?,?,?,?,?,?)";

    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      ps.setInt(1, id);
      ps.setString(2, user.getName());
      ps.setDate(3, user.getBirthDay());
      ps.setBoolean(4, user.getSex());
      ps.setString(5, user.getPlace());
      ps.setString(6, user.getPhoneNumber());
      ps.setString(7, user.getJob());

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

  public Boolean loginJudge(int ID, String pwd) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return false;
    String sqlStatement = "select * from  userpwd where id = (?) and pwd = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      ps.setString(2, pwd);
      ResultSet rs = ps.executeQuery();
     
      if (rs.next()) {
        connect.close();
        return true;
      } else {
        connect.close();
        return false;
      }
        

    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return false;
    }
  }
}



 