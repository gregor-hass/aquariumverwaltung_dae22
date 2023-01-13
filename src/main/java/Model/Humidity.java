package model;

import java.util.Date;

/**
 * resource representation class for humidity also: POJO
 * 
 * @author Julian-Paul Haslinger (P22080)
 */
public class Humidity {

  private int id;
  private Date timeStamp;
  private Float relativeHumidity;
  private int sensorId;

  public Humidity(int id, Date time, Float rh, int sensorId) {
    this.id = id;
    this.timeStamp = time;
    this.relativeHumidity = rh;
    this.sensorId = sensorId;
  }

  public Humidity() {
  }

  public int getId() {
    return id;
  }

  public Float getRelativeHumidity() {
    return relativeHumidity;
  }

  public int getSensor_id() {
    return sensorId;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setRelativeHumidity(Float relativeHumidity) {
    this.relativeHumidity = relativeHumidity;
  }

  public void setSensor_id(int sensorId) {
    this.sensorId = sensorId;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }
}
