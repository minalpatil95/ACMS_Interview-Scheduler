package com.minal.scheduler.dao;

import com.minal.scheduler.model.Employee;
import com.minal.scheduler.model.Event;
import com.minal.scheduler.utils.Constants;
import com.minal.scheduler.utils.Database;
import com.minal.scheduler.utils.TokenAuth;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeDao {

    public JSONObject validateLogin(final String email, String password) {
        try {
            final JSONObject status = new JSONObject();
            final Connection con = Database.getConnection();
            final PreparedStatement ps = con.prepareStatement("SELECT  * FROM  employee WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    if(rs.getBoolean("isEnabled")) {
                        final Employee employee = getEmployee(email);
                        System.out.println(employee);
                        status.put("status", Response.Status.OK.getStatusCode());
                        status.put("user", employee.toJSON());
                        status.put("token", TokenAuth.generateToken(employee));
                        status.put("errors", "");
                    }else{
                        status.put("status", Response.Status.BAD_REQUEST.getStatusCode());
                        final JSONObject errors = new JSONObject();
                        errors.put("verification","Email not verified. Please verify it" );
                        status.put("errors", errors);
                    }
                } else {
                    status.put("status", Response.Status.BAD_REQUEST.getStatusCode());
                    final JSONObject errors = new JSONObject();
                    errors.put("psword", Constants.ERROR_WRONG_PASSWD);
                    status.put("errors", errors);
                }
            } else {
                status.put("status", Response.Status.BAD_REQUEST.getStatusCode());
                final JSONObject errors = new JSONObject();
                errors.put("email", Constants.ERROR_NO_USER);
                status.put("errors", errors);
            }
            con.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public Employee getEmployee(String email) {
        try {
            Employee e = null;
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e=new Employee(rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("emp_type"),
                        rs.getString("phone_num"),
                        rs.getString("job_level"),
                        rs.getBoolean("isEnabled"),
                        rs.getString("competency"));
            }
            con.close();
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployee(int userId) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE emp_id=?");
            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            Employee e = null;
            if (rs.next()) {
                e=new Employee(rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("emp_type"),
                        rs.getString("phone_num"),
                        rs.getString("job_level"),
                        rs.getBoolean("isEnabled"),
                        rs.getString("competency"));
            }
            con.close();
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Employee> getAllInterviewers() {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE emp_type=?");
            ps.setString(1,"interviewer");
            ResultSet rs = ps.executeQuery();
            Collection<Employee> employees = new ArrayList<Employee>();
            while(rs.next()){
                employees.add(employeeBuilder(rs));
            }
            con.close();
            return employees;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Employee employeeBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int empId = rs.getInt("emp_id");
        final String empName = rs.getString("emp_name");
        final String email = rs.getString("email");
        final String password = rs.getString("password");
        final String empType = rs.getString("emp_type");
        final String phnNo = rs.getString("phone_num");
        final String jobLevel = rs.getString("job_level");
        final boolean isEnabled = rs.getBoolean("isEnabled");
        final String competency = rs.getString("competency");
        return new Employee(empId,empName,email,password,empType,phnNo,jobLevel,isEnabled,competency);

    }

    public Collection<Employee> getEventInterviewers(ArrayList<Integer> empId) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = null;
            for(int i=0;i<empId.size();i++) {

                ps = con.prepareStatement("SELECT * FROM employee WHERE emp_id=?");
                ps.setInt(1, empId.get(i));
            }

            ResultSet rs = ps.executeQuery();
            Collection<Employee> employees = new ArrayList<Employee>();
            while(rs.next()){
                employees.add(employeeBuilder(rs));
            }
            con.close();
            return employees;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean updateResponse(int eventId, int interviewerId, String response) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE round_inter_rel SET request_status=? WHERE event_id=? AND emp_id=?");
            ps.setString(1,response);
            ps.setInt(2,eventId);
            ps.setInt(3,interviewerId);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getInterviewerStatus(int eventId, int empId) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT request_status FROM round_inter_rel WHERE event_id= ? AND emp_id= ?");
            ps.setInt(1,eventId );
            ps.setInt(2,empId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String status = rs.getString("request_status");
                return status;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
