/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Employee;
import View.Login;
import View.UpdateForm;
import com.mysql.cj.jdbc.Driver;
import java.sql.Statement;
import javax.swing.table.TableModel;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author duy
 */
public class EmployeeDAO extends EmployeeAB {

    public EmployeeDAO() {

    }

    @Override
    public boolean updateEmp(Employee e) {
        Connection conn = JDBCUtil.getConnection();

        try {
            Statement stm = null;
            ResultSet rs = null;

            String sql = "UPDATE employee" + " SET " + "name=?" + ", address=?" + ",gender=?"
                    + ",position=?" + ",email=?" + ",birthday=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, e.getName());
            pst.setString(2, e.getAddress());
            pst.setString(3, e.getGender());
            pst.setString(4, e.getPosition());
            pst.setString(5, e.getemail());
            pst.setString(6, e.getBirthday());
            pst.setString(7, e.getId());

            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    @Override
    public boolean updateSal(float f, String id){
        Connection conn = JDBCUtil.getConnection();

        try {
            Statement stm = null;
            ResultSet rs = null;

            String sql = "UPDATE salary" + " SET " + "salary=?"
                    + " WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, f + "");
            pst.setString(2, id);

            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public boolean insertEmp(Employee e) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into employee(id, name, address, gender, position, email, birthday) values(?,?,?,?,?,?,?)";
        try {
            Statement stm;
            PreparedStatement pst = conn.prepareStatement(sql);
            PreparedStatement pstt = conn.prepareStatement("insert into salary(id) values(?)");

            pst.setString(1, e.getId());
            pstt.setString(1, e.getId());

            pst.setString(2, e.getName());
            pst.setString(3, e.getAddress());
            pst.setString(4, e.getGender());
            pst.setString(5, e.getPosition());
            pst.setString(6, e.getemail());
            pst.setString(7, e.getBirthday());
            pst.executeUpdate();
            pstt.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Opps Something went wrong...");
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public boolean deleteEmp(String id) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from employee where id=?";
        String sql2 = "delete from salary where id=?";

        try {
            Statement stm;
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            PreparedStatement pstt = conn.prepareStatement(sql2);
            pstt.setString(1, id);
            pstt.executeUpdate();
            pst.executeUpdate();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public ArrayList<Employee> getData() {
//        Collect Data from Database Assign to Arraylist
        ArrayList<Employee> ds = new ArrayList<>();
        Connection conn = JDBCUtil.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select * from employee";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ds.add(new Employee(rs.getString("id"), rs.getString("name"), rs.getString("address"),
                        rs.getString("gender"), rs.getString("position"), rs.getString("email"),
                        rs.getString("birthday")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ds;
    }

    @Override
    public Employee findById(String id) {
//        Collect Data from Database Assign to Arraylist
        Employee e = null;
        Connection conn = JDBCUtil.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select employee.id ,name, address, gender, position, email, birthday, salary from employee inner join salary on employee.id = salary.id where employee.id='" + id + "'";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                e = new Employee(rs.getString("id"), rs.getString("name"), rs.getString("address"),
                        rs.getString("gender"), rs.getString("position"), rs.getString("email"),
                        rs.getString("birthday"), rs.getFloat("salary"));
                System.out.println(e.toString());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return e;
    }

    @Override
    public int getNumberOfMale() {
        int result = 0;
        try {
            Statement stm = null;
            ResultSet rs = null;
            Connection conn = JDBCUtil.getConnection();

            String sql = "select count(gender) from employee where gender = \"male\";";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            result = rs.getInt("count(gender)");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getNumberOfFemale() {
         int result = 0;
        try {
            Statement stm = null;
            ResultSet rs = null;
            Connection conn = JDBCUtil.getConnection();

            String sql = "select count(gender) from employee where gender = \"female\";";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            result = rs.getInt("count(gender)");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getNumberOfPM() {
        int result = 0;
        try {
            Statement stm = null;
            ResultSet rs = null;
            Connection conn = JDBCUtil.getConnection();
            
            String sql = "select count(position) from employee where position='PM'";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            result = rs.getInt("count(position)");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getNumberOfTester(){
         int result = 0;
        try {
            Statement stm = null;
            ResultSet rs = null;
            Connection conn = JDBCUtil.getConnection();
            
            String sql = "select count(position) from employee where position='Tester'";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            result = rs.getInt("count(position)");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getNumberOfFront() {
         int result = 0;
        try {
            Statement stm = null;
            ResultSet rs = null;
            Connection conn = JDBCUtil.getConnection();
            
            String sql = "select count(position) from employee where position='Front-end'";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            result = rs.getInt("count(position)");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getNumberOfBack() {
        int result = 0;
        try {
            Statement stm = null;
            ResultSet rs = null;
            Connection conn = JDBCUtil.getConnection();
            
            String sql = "select count(position) from employee where position='Back-end'";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            rs.next();
            result = rs.getInt("count(position)");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

//    
    
}
