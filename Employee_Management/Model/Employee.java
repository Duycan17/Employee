/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author duy;
 */
public class Employee {
    private String id;
    private String name;
    private String address;
    private String gender;
    private String position;
    private String email;
    private String birthday;
    private float salary;

    public Employee(String id, String name, String address, String gender, String position, String email, String birthday) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.position = position;
        this.email = email;
        this.birthday = birthday;
    }


    public Employee(String id, String name, String address, String gender, String position, String email, String birthday, float salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.position = position;
        this.email = email;
        this.birthday = birthday;
        this.salary = salary;
    }
    
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", address=" + address + ", gender=" + gender + ", position=" + position + ", email=" + email + ", birthday=" + birthday + '}';
    }

    
    
}
