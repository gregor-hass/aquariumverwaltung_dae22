package model;

import java.util.Date;

/**
 * Employee model class (often referred to as "value object", too).
 * 
 * @author Julian-Paul Haslinger (P22080)
 */
public class Employee {

  private String firstName;
  private String lastName;
  private String email;
  private Integer employeeId;
  private double salary;
  private Date hireDate;
  private String job;

  public Employee(Integer employeeId, String firstName, String lastName, double salary, Date hireDate, String job,
      String email) {
    this.setEmployeeId(employeeId);
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setSalary(salary);
    this.setHireDate(hireDate);
    this.setJob(job);
    this.setEmail(email);

  }

  public String getEmail() {
    return email;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public String getJob() {
    return job;
  }

  public String getLastName() {
    return lastName;
  }

  public double getSalary() {
    return salary;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  /**
   * override the basic toString here, so we can just print each employee
   *
   * @return the formatted string for the output of an employee
   */
  @Override
  public String toString() {
    return String.format("Employee [" + getEmployeeId() + "]: " + getFirstName() + ", " + getLastName() + " ("
        + getJob() + "), " + this.email, ", " + this.salary, ", hired: " + this.hireDate);
  }
}
