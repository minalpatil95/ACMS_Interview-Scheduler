package com.minal.scheduler.model;

import com.minal.scheduler.dao.EmployeeDao;
import com.minal.scheduler.dao.EventDao;
import org.json.JSONArray;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Event {
    private int id;
    private String eventName;
    private String eventType;
    Date eventDate;
    private String jobLevel;
    private String eventStatus;
    private Collection<Candidate> candidates;
    private Collection<Round> rounds;
    private int numOfRounds;
    private int numOfCandidates;
    private Employee employee;

    public Event() {

    }

    public Event(int id, String eventName, String eventType, Date eventDate, String jobLevel, String eventStatus, int numOfRounds, int numOfCandidates, Employee employee) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.jobLevel = jobLevel;
        this.eventStatus = eventStatus;
        this.numOfRounds = numOfRounds;
        this.numOfCandidates = numOfCandidates;
        this.employee = employee;
    }

    public Event(int id, String eventName, String eventType, Date eventDate, String jobLevel, String eventStatus, Collection<Candidate> candidates, Collection<Round> rounds, int numOfRounds, int numOfCandidates, Employee employee) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.jobLevel = jobLevel;
        this.eventStatus = eventStatus;
        this.candidates = candidates;
        this.rounds = rounds;
        this.numOfRounds = numOfRounds;
        this.numOfCandidates = numOfCandidates;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDate=" + eventDate +
                ", jobLevel='" + jobLevel + '\'' +
                ", eventStatus='" + eventStatus + '\'' +
                ", candidates=" + candidates +
                ", rounds=" + rounds +
                ", numOfRounds=" + numOfRounds +
                ", numOfCandidates=" + numOfCandidates +
                ", employee=" + employee +
                '}';
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateformat.format(eventDate);
        try {
            this.eventDate = dateformat.parse(date);
        }
        catch(ParseException p){
            System.out.println(p);
        }

    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public void setNumOfRounds(int numOfRounds) {
        this.numOfRounds = numOfRounds;
    }

    public int getNumOfCandidates() {
        return numOfCandidates;
    }

    public void setNumOfCandidates(int numOfCandidates) {
        this.numOfCandidates = numOfCandidates;
    }

    public Collection<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Collection<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Collection<Round> getRounds() {
        return rounds;
    }

    public void setRounds(Collection<Round> rounds) {
        this.rounds = rounds;
    }

    public static Collection<Event> getAllRecruitersEvent(int id) {
        return new EventDao().getRecruiterEvent(id);
    }

    public static Event find(int eventId) {
        return new EventDao().getEventbyId(eventId);
    }

    public static Collection<Round> getRoundsOfEvent(int eventId) {
        return new EventDao().getRounds(eventId);
    }

    public static Boolean selectInterviewers(int eventId, JSONArray checked, JSONArray selected) {
        return EventDao.selectInterviewers(eventId,checked,selected);
    }

    public static Collection<Event> getAllInterviewersEvent(int id)  {
        return new EventDao().getInterviewerEvent(id);
    }

    public static Collection<Event> getAllInterviewersPendingEvent(int id) {
        return new EventDao().getInterviewerPendingEvent(id);
    }

    public static void sendEMailRequest(JSONArray checked) {
        ArrayList<Integer> interviewersId = new ArrayList<Integer>();
        for(int i=0;i<checked.length();i++) {
            interviewersId.add(i,checked.getInt(i));
        }

        ArrayList<String> emails = new ArrayList<String>();
        Collection<Employee> employees = new ArrayList<Employee>();
        employees = new EmployeeDao().getEventInterviewers(interviewersId);

        for(Employee e:employees)
            emails.add(e.getEmailId());

        //System.out.println(emails);
        SendEmail se = new SendEmail();
        se.sendEmail(emails);

    }

    public static ArrayList<Employee> getEventInterviewers(int eventId) {
        return new EventDao().getEventInterviewers(eventId);
    }

    public static ArrayList<String> getEventInterviewersStatus(int eventId) {
        EventDao eventDao = new EventDao();
        ArrayList<Employee> employees = eventDao.getEventInterviewers(eventId);
        ArrayList<Integer> empId = new ArrayList<Integer>();
        for(Employee e : employees) {
            int id = e.getId();
            empId.add(id);
        }
        System.out.println(empId);

        return new EventDao().getEventInterviewersStatus(eventId,empId);
    }
}

