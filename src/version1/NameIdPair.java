package version1;


  public class NameIdPair {
    private String name;
    private int ID;
    private String tel;

    public String getTel() {
      return tel;
    }

    public void setTel(String tel) {
      this.tel = tel;
    }

    public NameIdPair(String name) {
      this.name = name;
      this.ID = -1; // Î´ºÏ²¢
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getID() {
      return ID;
    }

    public void setID(int iD) {
      ID = iD;
    }

    public NameIdPair(String name, int ID, String tel) {
      this.name = name;
      this.ID = ID;
      this.tel = tel;
    }
    
    public int hashCode() {
      return this.name.hashCode();
    }
    @SuppressWarnings("unused")
    public boolean equals(Object obj){

      if(obj instanceof NameIdPair){
        if (obj == null) {
          return false;
        }

        NameIdPair temp = (NameIdPair)obj;

        if ((this.ID == temp.ID)&&(this.name.equals(temp.name))
            &&(this.tel.equals(temp.tel))) {
            return true;
        }
       
    }
       return false;
    }
  }

