/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Employee;
import java.util.ArrayList;

/**
 *
 * @author duy
 */
public abstract class EmployeeAB {

    public abstract boolean updateEmp(Employee e);

    public abstract boolean updateSal(float Sal, String e);

    public abstract boolean insertEmp(Employee e);

    public abstract boolean deleteEmp(String id);

    public abstract ArrayList<Employee> getData();

    public abstract Employee findById(String e);

    public abstract int getNumberOfMale();

    public abstract int getNumberOfFemale();

    public abstract int getNumberOfPM();

    public abstract int getNumberOfTester();

    public abstract int getNumberOfFront();

    public abstract int getNumberOfBack();

}
