package com.minal.scheduler.dao;

import com.minal.scheduler.model.Candidate;
import com.minal.scheduler.model.Employee;
import com.minal.scheduler.model.Event;
import com.minal.scheduler.model.Round;
import com.minal.scheduler.utils.Database;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class EventDao {
    /*public boolean createEvent(Event event) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO event(event_name,event_type,num_of_rounds," +
                            "date,event_status,job_level,num_of_rounds,recruiter_id) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, event.getEventName());
            ps.setString(2, event.getEventType());
            ps.setInt(3, event.getNumOfRounds());
            ps.setDate(4,new java.sql.Date(event.getEventDate().getTime()));
            ps.setString(5, event.getEventStatus());
            ps.setString(6, event.getJobLevel());
            *//*if(event.getEventType() == "2")
                ps.setInt(7,1);
            else
                ps.setInt(7,event.getNumOfCandidates());*//*
            ps.setInt(7,event.getNumOfCandidates());
            ps.setInt(8,event.getEmployee().getId());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/

    public boolean createEvent(String eventName, String eventType, Date eventDate, String jobLevel,int numOfRounds, Employee employee) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO event(event_name,event_type,num_of_rounds," +
                            "date,job_level,recruiter_id) VALUES (?,?,?,?,?,?)");
            ps.setString(1, eventName);
            ps.setString(2, eventType);
            ps.setInt(3, numOfRounds);
            ps.setDate(4,new java.sql.Date(eventDate.getTime()));
            ps.setString(5, jobLevel);
            ps.setInt(6,employee.getId());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createRound(int eventId, ArrayList<String> comp) {
        try {
            Connection con = Database.getConnection();
            for(int i=0;i<comp.size();i++) {

                PreparedStatement ps = con
                        .prepareStatement("INSERT INTO round(event_id,round_id,competency) VALUES (?,?,?)");
                ps.setInt(1, eventId);
                ps.setInt(2, i + 1);
                ps.setString(3, comp.get(i));

                ps.executeUpdate();
            }
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Event getEventByEventName(String ename) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM event WHERE event_name= ?");
            ps.setString(1,ename );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Event event = eventBuilder(rs);
                con.close();
                return event;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static Event eventBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int eventId = rs.getInt("event_id");
        final String eventName = rs.getString("event_name");
        final String eventType = rs.getString("event_type");
        final Date date = rs.getDate("date");
        final String jobLevel = rs.getString("job_level");
        final String eventStatus = rs.getString("event_status");
        final int numOfRounds = rs.getInt("num_of_rounds");
        final int numOfCandidates = rs.getInt("num_of_candidate");
        final int reqId = rs.getInt("recruiter_id");
        EmployeeDao employeeDao = new EmployeeDao();
        Employee employee = employeeDao.getEmployee(reqId);
        return new Event(eventId,eventName,eventType,date,jobLevel,eventStatus,numOfRounds,numOfCandidates,employee);
    }

    public Collection<Event> getRecruiterEvent(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM event WHERE recruiter_id=?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            Collection<Event> e = new ArrayList<Event>();
            while(rs.next()){
                e.add(eventBuilder(rs));
            }
            con.close();
            return e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Event getEventbyId(int eventId) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM event WHERE event_id= ?");
            ps.setInt(1,eventId );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Event event = eventBuilder(rs);
                con.close();
                return event;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Round> getRounds(int eventId) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM round WHERE event_id=?");
            ps.setInt(1,eventId);
            ResultSet rs = ps.executeQuery();
            Collection<Round> r = new ArrayList<Round>();
            while(rs.next()){
                r.add(roundBuilder(rs));
            }
            con.close();
            return r;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Round roundBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int roundId = rs.getInt("round_id");
        final String competency = rs.getString("competency");
        return new Round(roundId,competency);

    }

    public static Boolean selectInterviewers(int eventId, JSONArray checked, JSONArray selected) {
        try {
            Connection con = Database.getConnection();
            for(int i=0;i<checked.length();i++) {

                PreparedStatement ps = con
                        .prepareStatement("INSERT INTO round_inter_rel(round_id,event_id,emp_id) VALUES (?,?,?)");
                ps.setInt(1, selected.getInt(i));
                ps.setInt(2, eventId);
                ps.setInt(3, checked.getInt(i));

                ps.executeUpdate();
            }
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Collection<Event> getInterviewerEvent(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM round_inter_rel WHERE emp_id=? AND request_status=? OR request_status=?");
            ps.setInt(1,id);
            ps.setString(2,"accept");
            ps.setString(3,"reject");
            ResultSet rs = ps.executeQuery();
            //ArrayList<Integer> eventId = new ArrayList<Integer>();
            Collection<Event> e = new ArrayList<Event>();
            while(rs.next()){
               // eventId.add(rs.getInt("eventId"));
                e.add(getAllInterviewerEvents(rs.getInt("event_id")));
            }
            con.close();
            return e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Event getAllInterviewerEvents(int eventId) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM event WHERE event_id= ?");
            ps.setInt(1,eventId );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Event event = eventBuilder(rs);
                con.close();
                return event;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Event> getInterviewerPendingEvent(int id) {
        try {
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM round_inter_rel WHERE emp_id=? and request_status=?");
            ps.setInt(1,id);
            ps.setString(2,"pending");
            ResultSet rs = ps.executeQuery();
            //ArrayList<Integer> eventId = new ArrayList<Integer>();
            Collection<Event> e = new ArrayList<Event>();
            while(rs.next()){
                // eventId.add(rs.getInt("eventId"));
                e.add(getAllInterviewerPendingEvents(rs.getInt("event_id")));
            }
            con.close();
            return e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Event getAllInterviewerPendingEvents(int eventId) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM event WHERE event_id= ?");
            ps.setInt(1,eventId );
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Event event = eventBuilder(rs);
                con.close();
                return event;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean findEvent(int eventId) {
        Connection con = null;
        try {
            con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM round_inter_rel WHERE event_id= ?");
            ps.setInt(1,eventId );
            ResultSet rs = ps.executeQuery();
            if(!rs.next()){
                con.close();
                return false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<Employee> getEventInterviewers(int eventId) {
        try {
            ArrayList<Employee> employees= new ArrayList<Employee>();
            Connection con = Database.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT emp_id FROM round_inter_rel WHERE event_id=?");
            ps.setInt(1,eventId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Integer> empId = new ArrayList<Integer>();
            while(rs.next()){
               empId.add(rs.getInt("emp_id"));
            }
            EmployeeDao employeeDao = new EmployeeDao();
            for(int i=0;i<empId.size();i++)
            {
                employees.add(i,employeeDao.getEmployee(empId.get(i)));
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

    public ArrayList<String> getEventInterviewersStatus(int eventId,ArrayList<Integer> empId) {
        try {
            Connection con = Database.getConnection();
            ResultSet rs = null;
            ArrayList<String> status = new ArrayList<String>();
            for(int i=0;i<empId.size();i++) {

                PreparedStatement ps = con
                        .prepareStatement("SELECT request_status FROM round_inter_rel WHERE event_id=? AND emp_id=?");
                ps.setInt(1, eventId);
                ps.setInt(2, (empId.get(i)));
                rs = ps.executeQuery();


                while (rs.next()) {
                    status.add(rs.getString("request_status"));
                }
            }
            con.close();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
