package model;

import java.util.Date;

/**
 * resource representation class for sensor also: POJO
 * 
 * @author Julian-Paul Haslinger (P22080)
 */
public class Sensor {

  private int id;
  private Date createdAt;
  private String friendlyName;
  private int employeeId;

  public Sensor(int id, Date createdAt, String friendlyName, int employeeId) {
    this.id = id;
    this.createdAt = createdAt;
    this.friendlyName = friendlyName;
    this.employeeId = employeeId;
  }

  public Date getCreated_at() {
    return createdAt;
  }

  public int getEmployee_id() {
    return employeeId;
  }

  public String getFriendly_name() {
    return friendlyName;
  }

  public int getId() {
    return id;
  }

  public void setCreated_at(Date createdAt) {
    this.createdAt = createdAt;
  }

  public void setEmployee_id(int employeeId) {
    this.employeeId = employeeId;
  }

  public void setFriendly_name(String friendlyName) {
    this.friendlyName = friendlyName;
  }

  public void setId(int id) {
    this.id = id;
  }
}
