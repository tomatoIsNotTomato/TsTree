package version1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.lang3.StringUtils;

public class DBcrud {
  private String jdbcDriver = "com.mysql.jdbc.Driver";
 /* private String dbusername = "root";
  private String dbpassword = "2218234907";
  private String dbUrl = "jdbc:mysql://localhost:3306/tree?useSSL=false&useUnicode=true&characterEncoding=utf-8";

*/
  String dbusername = System.getenv("ACCESSKEY");
  String dbpassword = System.getenv("SECRETKEY");
  //System.getenv("MYSQL_HOST_S"); 为从库，只读
  String dbUrl = String.format("jdbc:mysql://%s:%s/%s", System.getenv("MYSQL_HOST"), System.getenv("MYSQL_PORT"), System.getenv("MYSQL_DB"));
  
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
      l.add(i.getName() + "," + i.getID() + "," + i.getEmail());
    }
    String setString = StringUtils.join(l, ";");
    return setString;
  }

  private HashSet<NameIdPair> changeToHashSet(String info) {
    HashSet<NameIdPair> hs = new HashSet<>();
    if (info.length() == 0)
      return null;
    String[] infoList = info.split(";");
    for (String s : infoList) {
      String[] metaData = s.split(",");
      NameIdPair nip = new NameIdPair(metaData[0], Integer.parseInt(metaData[1]), "");
      if (metaData.length > 2) {
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
        }
        lst.add(rowData);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lst;
  }

  public int checkExist(String emailOrId, String type) throws SQLException {
    Connection connect = connectDB();
    if (connect == null) {
      return -2;
    }
    String sqlStatement = "select id from userpwd where email = (?)";
    String sqlStatement1 = "select id from userpwd where linkedinId = (?)";
    PreparedStatement ps;
    try {
      if (type.equals("linkedin")) {
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      } else {
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      }
      ps.setString(1, emailOrId);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
        return rs.getInt(1);
      else
        return -1;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return -2;
    }
  }

  public int saveCode(String emailOrId, String pwdOrToken, String type) throws SQLException {
    Connection connect = connectDB();
    int id = -1;
    if (connect == null)
      return -1;
    String sqlStatement = "insert into userpwd (email,pwd) value(?,?)";
    String sqlStatement1 = "insert into userpwd (linkedinId,token) value(?,?)";
    PreparedStatement ps;
    try {
      if (type == "linkedin")
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      else
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, emailOrId);
      ps.setString(2, pwdOrToken);
      ps.executeUpdate();
      ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
      if (rs.next()) {
        id = rs.getInt(1);
      }
      connect.close();
      return id;
    } catch (Exception e) {
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
    String sqlStatement1 = "insert into user (id, firstName, lastName, headline, location, industry, emailAddress, pictureUrl, publicProfileUrl) value(?,?,?,?,?,?,?,?,?)";
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
      int id = -1;
      if (rs.next()) {
        id = rs.getInt(1);
      }

      connect.close();
      return id;
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

  public ArrayList<Map<String, Object>> getBasicInfo(String lastName, String firstName) throws SQLException {
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
  
  public ArrayList<Map<String, Object>> getBasicInfo(String name, int i) throws SQLException {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    Connection connect = connectDB();
    if (connect == null)
      return null;
    String sqlStatement1 = "select * from  user where lastName = ? ";
    String sqlStatement2 = "select * from  user where firstName = ? ";
    PreparedStatement ps;
    try {
      if (i ==1 ) {
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement1);
      }
      else {
        ps = (PreparedStatement) connect.prepareStatement(sqlStatement2);
      }
      ps.setString(1, name);
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
  

  public HashSet<NameIdPair> queryPeriodInfo(int ID, String period, String relationShip) throws SQLException {
    HashSet<NameIdPair> lst = new HashSet<>();
    String info = new String();
    Connection connect = connectDB();
    if (connect == null)
      return null;
    String sqlStatement = "select " + relationShip + " from user_" + period + " where " + period + "ID = ?";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        info = rs.getString(1);
      }
      if (changeToHashSet(info) != null) {
        lst.addAll(changeToHashSet(info));
      }
      return lst;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return null;
    }
  }

  public ArrayList<Map<String, Object>> queryTsTree(int ID) {
    ArrayList<Map<String, Object>> lst = new ArrayList<>();
    String[] periods = { "bachelor", "master", "doctor" };
    String[] relations = { "teacher", "student" };
    for (String period : periods) {
      for (String relation : relations) {
        HashSet<NameIdPair> hs;
        try {
          hs = queryPeriodInfo(ID, period, relation);
          if (hs == null)
            return null;

          for (NameIdPair pair : hs) {
            Map<String, Object> map = new HashMap<>();
            map.put("relation", relation);
            map.put("period", period);
            map.put("ID", pair.getID());
            map.put("name", pair.getName());
            map.put("email", pair.getEmail());
            lst.add(map);
          }
        } catch (SQLException e) {
          e.printStackTrace();
          return null;
        }
      }
    }
    return lst;
  }

  public String getPicUrl(int ID) throws SQLException {
    String defaultPic = "http://tomato.applinzi.com/userImage/default.jpg";
    Connection connect = connectDB();
    if (connect == null || ID == -1) {
      return defaultPic;
    }
    String sqlStatement = "select pictureUrl from user where ID = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        String pic = rs.getString(1);
        connect.close();
        System.out.println("testPic");
        if(pic==null || pic.length()==0 || pic.contains("tomato")) {
          pic = defaultPic;
          System.out.println("1");
        }
        System.out.println(pic);
        return pic;
      } else {
        return defaultPic;
      }
    } catch (Exception e) {

      e.printStackTrace();
      connect.close();
      return defaultPic;
    }
  }

  public Boolean add_t_s_info(int selfID, String firstName, String lastName,  int id, String relation, String period, String email)
      throws SQLException {
    try {
      Connection connect = connectDB();
      PreparedStatement ps;
      ResultSet rs;
      String sqlStatement = "select " + relation + " from user_" + period + " where " + period + "ID = (?)";
      ps = connect.prepareStatement(sqlStatement);
      ps.setInt(1, selfID);

      rs = ps.executeQuery();
      if (rs.next()) {

        HashSet<NameIdPair> hash = changeToHashSet(rs.getString(1));
        if (hash == null)
          hash = new HashSet<NameIdPair>();

        hash.add(new NameIdPair(lastName+" "+firstName, id, email));
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
  public Boolean de_t_s_info(int ID, int id, String name, String period, String relation, String email)
      throws SQLException {
    try {
      Connection connect = connectDB();
      PreparedStatement ps;
      ResultSet rs;
      String sqlStatement = "select * from user_" + period + " where " + period + "ID = ?";
      ps = connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      rs = ps.executeQuery();

      if (rs.next()) {
        HashSet<NameIdPair> hash = changeToHashSet(rs.getString(relation));
        if (hash == null)
          hash = new HashSet<NameIdPair>();
        NameIdPair nana = new NameIdPair(name, id, email);
        Iterator iter = hash.iterator();
        while (iter.hasNext()) {
          NameIdPair person = (NameIdPair) iter.next();
          if (person.equals(nana)) {
            iter.remove();
          }
        }
        String str = changeToString(hash);
        ps = connect.prepareStatement("update user_" + period + " set " + relation + "=(?) where " + period + "ID=(?)");
        ps.setString(1, str);
        ps.setInt(2, ID);
        ps.executeUpdate();
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  @SuppressWarnings("null")
  public Boolean update_t_s_info(int ID, int id, String name1, String name2, String period, String relation,
      String email_old, String email_new) throws SQLException {
    try {
      Connection connect = connectDB();
      PreparedStatement ps;
      ResultSet rs;
      String sqlStatement = "select * from user_" + period + " where " + period + "ID = ?";
      ps = connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID);
      rs = ps.executeQuery();
      if (rs.next()) {
        HashSet<NameIdPair> hash = changeToHashSet(rs.getString(relation));
        if (hash == null)
          hash = new HashSet<NameIdPair>();
        NameIdPair nana = new NameIdPair(name1, id, email_old);
        Iterator iter = hash.iterator();
        while (iter.hasNext()) {
          NameIdPair person = (NameIdPair) iter.next();
          if (person.equals(nana)) {
            iter.remove();
          }
        }
        hash.add(new NameIdPair(name2, id, email_new));
        String str2 = changeToString(hash);
        System.out.println(str2);
        ps = connect.prepareStatement("update user_" + period + " set " + relation + "=(?) where " + period + "ID=(?)");
        ps.setString(1, str2);
        ps.setInt(2, ID);
        ps.executeUpdate();
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean leaveMes(int ID1, int ID2, String message, String time, int history) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return false;
    String sqlStatement = "insert into message (fromID, toID, sendTime, mess, lastMes, status) value(?,?,?,?,?,?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, ID1);
      ps.setInt(2, ID2);
      ps.setString(3, time);
      ps.setBlob(4, new SerialBlob(message.getBytes("utf-8")));
      ps.setInt(5, history);
      ps.setInt(6, 0);
      ps.executeUpdate();
      connect.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return false;
    }
  }

  public static String BlobToString(Blob blob) throws SQLException, IOException {

    String reString = "";
    InputStream is =  blob.getBinaryStream();

    ByteArrayInputStream bais = (ByteArrayInputStream)is;
    byte[] byte_data = new byte[bais.available()]; //bais.available()返回此输入流的字节数
    bais.read(byte_data, 0,byte_data.length);//将输入流中的内容读到指定的数组
    reString = new String(byte_data,"utf-8"); //再转为String，并使用指定的编码方式
    is.close();

    return reString;
}
  
  private ArrayList<Map<String, String>> extractMsgInfo(ResultSet rs) throws IOException {
    ArrayList<Map<String, String>> lst = new ArrayList<>();
    ResultSetMetaData md;
    try {
      md = rs.getMetaData();
      int column = md.getColumnCount();
      while (rs.next()) {
        Map<String, String> rowData = new HashMap<>();
        for (int i = 1; i <= column; i++) {
          if (i == 4) {
            rowData.put(md.getColumnName(i),
                BlobToString(rs.getBlob(i)));
          }
          else
          rowData.put(md.getColumnName(i), rs.getObject(i).toString());
        }
        int id = rs.getInt(1);
        ArrayList<Map<String, Object>> user = getBasicInfo(id);
        rowData.put("name", user.get(0).get("lastName")+" "+user.get(0).get("firstName"));
        rowData.put("pictureUrl", user.get(0).get("pictureUrl").toString());
        
        lst.add(rowData);
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lst;
  }

  public ArrayList<Map<String, String>> getMessList(int id) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return null;
    String sqlStatement = "select * from message where toID = ?";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      ArrayList<Map<String, String>> lst = extractMsgInfo(rs);
      connect.close();
      return lst;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return null;
    }
  }

  public boolean alreadyRead(int msgId) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return false;
    String sqlStatement = "update message set status=1 where mesID=?";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, msgId);
      ps.executeUpdate();
      connect.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return false;
    }
  }

  public int getMsgCount(int id) throws SQLException {
    Connection connect = connectDB();
    if (connect == null)
      return -1;
    String sqlStatement = "select * from message where toID = ? and status=0";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, id);
      int count = 0;
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        count++;
      }
      connect.close();
      return count;
    } catch (Exception e) {
      e.printStackTrace();
      connect.close();
      return -1;
    }
  }

  public ArrayList<Integer> sameLocation(int id) throws SQLException {
    Connection conn = connectDB();
    ArrayList<Integer> lst = new ArrayList<>();
    if (conn == null) {
      return null;
    }
    String location = "";
    PreparedStatement ps;
    String sqlStatement1 = "select location from user where id = ?";
    String sqlStatement2 = "select id from user where location = ?";
    try {
      ps = (PreparedStatement) conn.prepareStatement(sqlStatement1);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        location = rs.getString(1);
      }

      if (!location.equals("")) {
        ps = (PreparedStatement) conn.prepareStatement(sqlStatement2);
        ps.setString(1, location);
        rs = ps.executeQuery();
        while (rs.next()) {
          lst.add(rs.getInt(1));
        }
        conn.close();
        return lst;
      }
      return null;
    } catch (Exception e) {
      e.printStackTrace();
      conn.close();
      return null;
    }
  }

  private ArrayList<Map<Integer,String>> SameName(String name, ArrayList<Integer> lst, String sqlStatement) throws SQLException {
    Connection conn = connectDB();
    if (conn == null) {
      return null;
    }
    PreparedStatement ps;
    ArrayList<Map<Integer,String>> idlst = new ArrayList<>();
    for (int i : lst) {
      try {
        ps = (PreparedStatement) conn.prepareStatement(sqlStatement);
        ps.setInt(1, i);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          HashSet<NameIdPair> hs = changeToHashSet(rs.getString(1));
          if(hs!=null){
            for (NameIdPair r : hs) {
              if (r.getName().equals(name)) {
                Map<Integer,String> m = new HashMap<>();
                m.put(i, "teacher");
                System.out.println(r.getName());
                System.out.println(name); 
                  idlst.add(m);   
              }
            }
          }
         
          hs = changeToHashSet(rs.getString(2));
          if(hs!=null){
            for (NameIdPair r : hs) {
              
              if (r.getName().equals(name)) {
                Map<Integer,String> m = new HashMap<>();
                m.put(i, "student");
                System.out.println(r.getName());
                System.out.println(name); 
                idlst.add(m);   
              }
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        conn.close();
        return null;
      }
    }
    return idlst;
  }

  private boolean existTeacherOrStu(ArrayList<Map<String, Object>> existTs, int id) {
    for (Map<String, Object> r :existTs) {
      if (r.get("ID").equals(id)) {
        return true;
      }
    }
    return false;
  }
  
  public ArrayList<Map<String, String>> peopleMayKnow(int id, String name) throws SQLException {
    Connection conn = connectDB();
    ArrayList<Map<String, String>> pmn = new ArrayList<>();
    if (conn == null) {
      return null;
    }
    PreparedStatement ps;
    ArrayList<Integer> lst = sameLocation(id);
    ArrayList<Map<String, Object>> existTs = queryTsTree(id);
    String sqlStatement1 = "select teacher , student from user_bachelor where bachelorID = ?";
    String sqlStatement2 = "select teacher , student from user_master where masterID = ?";
    String sqlStatement3 = "select teacher , student from user_doctor where doctorID = ?";
    String sqlStatement4 = "select firstName, lastName, pictureUrl, emailAddress from user where id = ?";
    ArrayList<Integer> idlst = new ArrayList<>();
    ArrayList<String> period = new ArrayList<>();
    ArrayList<String> relation = new ArrayList<>();
    ArrayList<Map<Integer, String>> l = SameName(name, lst, sqlStatement1);
    if (l != null) {
      for (Map<Integer, String> m:l) {
        idlst.addAll(m.keySet());
        relation.addAll(m.values());
        period.add("bachelor");
      }
    }
    l = SameName(name, lst, sqlStatement2);
    if (l != null)
      for (Map<Integer, String> m:l) {
        idlst.addAll(m.keySet());
        relation.addAll(m.values());
        period.add("master");
      }
    l = SameName(name, lst, sqlStatement3);
    if (l != null)
      for (Map<Integer, String> m:l) {
        idlst.addAll(m.keySet());
        relation.addAll(m.values());
        period.add("doctor");
      }
    
    int count = 0;
    for (int j = 0; j < idlst.size(); j++) {
      if (count >= 6)
        break;
      try {
        ps = (PreparedStatement) conn.prepareStatement(sqlStatement4);
        ps.setInt(1, idlst.get(j));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
          if (existTs!=null && existTeacherOrStu(existTs, idlst.get(j))) {
            continue;
          }
          Map<String, String> map = new HashMap<>();
          map.put("lastName", rs.getString(2));
          map.put("firstName", rs.getString(1));
          map.put("id", String.format("%0" + 5 + "d", Integer.parseInt(idlst.get(j).toString()) ));
          map.put("pictureUrl", rs.getString(3));
          map.put("relation", relation.get(j));
          map.put("period", period.get(j));
          map.put("email", rs.getString(4));
          if (relation.get(j).equals("teacher")) {
            map.put("newRelation", "student");
          }
          else {
            map.put("newRelation", "teacher");
          }
          count++;
          pmn.add(map);
        }

      } catch (Exception e) {
        e.printStackTrace();

        return null;
      }
    }
    return pmn;
  }
  
  public int active(String uuid) throws SQLException {
    Connection connect = connectDB();
    if (connect == null) {
      return 0;
    }
    int id = 0;
    String sqlStatement0 = "select id from user where uuid = (?)";
    String sqlStatement = "update user set email_verified=1, uuid = null where uuid = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement0);
      ps.setString(1, uuid);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        id = rs.getInt(1);
      }
      
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, uuid);
      ps.executeUpdate();
      connect.close();
      return id;
    }
    catch(Exception e) {
      e.printStackTrace();
      connect.close();
      return id;
    }
  }
  
  public Boolean updateUUid(String email, String uuid) throws SQLException {
    Connection connect = connectDB();
    String sqlStatement = "update user set uuid = (?) where emailAddress = (?)";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setString(1, uuid);
      ps.setString(2, email);
      ps.executeUpdate();
      connect.close();
      return true;  
    }
    catch(Exception e) {
      e.printStackTrace();
      connect.close();
      return false;
    }
  }
  
  public int checkActive(int id) throws SQLException{
    Connection connect = connectDB();
    String sqlStatement = "select email_verified from user where id=?";
    PreparedStatement ps;
    try {
      ps = (PreparedStatement) connect.prepareStatement(sqlStatement);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        if (rs.getInt(1)==1) {
          connect.close();
          return 1;
        }
          else {
            connect.close();
            return 0;
          }  
        }
        connect.close();
        return 1;
      }
      
    
    catch(Exception e) {
      e.printStackTrace();
      connect.close();
      return -1;
    }
  }
}
