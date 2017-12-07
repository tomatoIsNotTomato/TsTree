package version1;

public class relatedPerson {
  private String name;
  private String id;
  private String email;
  private String relation;
  private String period;
  private String weight;
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
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
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
  public String getWeight() {
    return weight;
  }
  public void setWeight(String weight) {
    this.weight = weight;
  }
  
  public relatedPerson() {
    name = "";
    id = ""; 
    email = "";
    relation = "";
    period = "";
    weight = "";
  }
  

  
}
