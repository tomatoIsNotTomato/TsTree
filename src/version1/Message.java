package version1;

public class Message {
  private int fromID;
  private int toID;
  private String time;
  private String mess;
  private int lastMes;
  private int mesId;
  public int getFromID() {
    return fromID;
  }
  public void setFromID(int fromID) {
    this.fromID = fromID;
  }
  public int getToID() {
    return toID;
  }
  public void setToID(int toID) {
    this.toID = toID;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public String getMess() {
    return mess;
  }
  public void setMess(String mess) {
    this.mess = mess;
  }
  public int getLastMes() {
    return lastMes;
  }
  public void setLastMes(int lastMes) {
    this.lastMes = lastMes;
  }
  public int getMesId() {
    return mesId;
  }
  public void setMesId(int mesId) {
    this.mesId = mesId;
  }
  
  public Message() {
    fromID = 0;
    toID = 0;
    time = null;
    mess = null;
    lastMes = 0;
    mesId = 0;
  }
}
