package com.minal.scheduler.model;

import com.minal.scheduler.dao.EmployeeDao;
import org.json.JSONObject;

import java.util.Collection;

public class Employee {
    private int id;
    private String userName;
    private String emailId;
    private String password;
    private String type;
    private String phnNumber;
    private String jobLevel;
    private boolean isEnabled;
    private String competency;

    public String getPhnNumber() {
        return phnNumber;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public Employee(int id, String userName, String emailId, String password, String type, String phnNumber, String jobLevel, boolean isEnabled, String competency) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.type = type;
        this.phnNumber = phnNumber;
        this.jobLevel = jobLevel;
        this.isEnabled = isEnabled;
        this.competency = competency;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", phnNumber='" + phnNumber + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", isEnabled=" + isEnabled +
                ", competency='" + competency + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhnNumber(String phnNumber) {
        this.phnNumber = phnNumber;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getCompetency() {
        return competency;
    }

    public void setCompetency(String competency) {
        this.competency = competency;
    }

    public JSONObject toJSON() {
        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("id", id);
        userJsonObject.put("email", emailId);
        userJsonObject.put("username", userName);
        userJsonObject.put("type", type);
        userJsonObject.put("joblevel", jobLevel);
        userJsonObject.put("competency", competency);
        userJsonObject.put("phnnumber", phnNumber);
        return userJsonObject;
    }

    public static Collection<Employee> getAllInterviewers(){
        return new EmployeeDao().getAllInterviewers();
    }

    public static Boolean  sendResponse(int eventId, int interviewerId, String response) {
        return EmployeeDao.updateResponse(eventId,interviewerId,response);
    }

    public static String getInterviewerStatus(int eventId, int empId) {
        return EmployeeDao.getInterviewerStatus(eventId,empId);
    }
}
