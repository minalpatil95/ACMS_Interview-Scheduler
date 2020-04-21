package com.minal.scheduler.api;

import com.minal.scheduler.dao.EmployeeDao;
import com.minal.scheduler.dao.EventDao;
import com.minal.scheduler.model.Employee;
import com.minal.scheduler.model.Event;
import com.minal.scheduler.model.SendEmail;
import com.minal.scheduler.utils.TokenAuth;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Path("")
public class EventService {

    @GET
    @Path("/selectint/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@HeaderParam("sellerAuthToken")String token, @PathParam("id") int eventId, String req) {
        Employee employee = TokenAuth.getEmployeeFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        if (employee == null) {
            return Response.status(Response.Status.OK).entity(new JSONObject()
                    .put("status", Response.Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
       return null;
    }

    @Path("/selectint/{id}")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SelectInterviewers(String req, @HeaderParam("authToken") String token) {
        Employee employee = TokenAuth.getEmployeeFromToken(token);
        JSONObject re = new JSONObject(req);
        if (employee == null) {
            return Response.status(Response.Status.OK).entity(new JSONObject()
                    .put("status", Response.Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }

        int eventId = re.getInt("eventId");
        JSONArray checked = re.getJSONArray("checked");
        JSONArray selected = re.getJSONArray("selected");
        Event.selectInterviewers(eventId,checked,selected);
        Event.sendEMailRequest(checked);
        return null;
    }

    @POST
    @Path("/response/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponse(@HeaderParam("authToken")String token, String req) {
        Employee employee = TokenAuth.getEmployeeFromToken(token);
        JSONObject reqJson = new JSONObject(req);
        if (employee == null) {
            return Response.status(Response.Status.OK).entity(new JSONObject()
                    .put("status", Response.Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }

        String checked = reqJson.getString("checked");
        int eventId = reqJson.getInt("eventId");
        int interviewerId = employee.getId();
        Employee.sendResponse(eventId,interviewerId,checked);
        return null;
    }

    @GET
    @Path("/eventdetailsforinterviewer/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EventDetailsInterviewer(@HeaderParam("authToken")String token, @PathParam("id") int eventId) {
        Employee employee = TokenAuth.getEmployeeFromToken(token);
        if (employee == null) {
            return Response.status(Response.Status.OK).entity(new JSONObject()
                    .put("status", Response.Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
        int empId=employee.getId();
        String status=Employee.getInterviewerStatus(eventId,empId);
        JSONObject resp2 = new JSONObject();
        resp2.put("status",status);
        return Response.status(Response.Status.OK).entity(resp2.toString()).build();
    }

    @GET
    @Path("/eventdetailsforrecruiter/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response EventDetailsRecruiter(@HeaderParam("authToken")String token, @PathParam("id") int eventId) {
        Employee employee = TokenAuth.getEmployeeFromToken(token);
        if (employee == null) {
            return Response.status(Response.Status.OK).entity(new JSONObject()
                    .put("status", Response.Status.UNAUTHORIZED.getStatusCode()).toString()).build();
        }
        int empId=employee.getId();
        ArrayList<Employee> interviewers = Event.getEventInterviewers(eventId);
        ArrayList<String> status = Event.getEventInterviewersStatus(eventId);
        System.out.println(status);
        JSONObject resp2 = new JSONObject();
        resp2.put("interviewers",interviewers);
        resp2.put("status",status);
        return Response.status(Response.Status.OK).entity(resp2.toString()).build();
    }



}
