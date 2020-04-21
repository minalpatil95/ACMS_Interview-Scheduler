package com.minal.scheduler.api;

import com.minal.scheduler.model.Employee;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;

@Path("")
public class EmployeeService {
    @Path("interviewerslist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastFive() {
        Collection<Employee> i = Employee.getAllInterviewers();
        final JSONArray j = new JSONArray();
        for (Employee employee : i) {
            j.put(employee.toJSON());
        }
        return Response.status(Response.Status.OK).entity(new JSONObject().put("items", j).toString()).build();
    }

    @Path("filter1")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CompetencyFilter(String req) {
        JSONObject re = new JSONObject(req);
        String Competency = re.getString("competency");
        JSONObject resp = re.getJSONObject("data");
        JSONArray items = resp.getJSONArray("items");
        JSONArray returnItems = new JSONArray();
        if(!Competency.equals("000")){
            for(Object item: items){
                JSONObject jItem  = (JSONObject) item;
                String competency = jItem.getString("competency");
                if(competency.equals(Competency)) {
                    returnItems.put(item);
                }
            }
        }else{
            returnItems = returnItems;
        }
        JSONObject resp2 = new JSONObject();
        resp2.put("items",returnItems);
        return Response.status(Response.Status.OK).entity(resp2.toString()).build();
    }

    @Path("filter2")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response JobLevelFilter(String req) {
        JSONObject re = new JSONObject(req);
        String Joblevel = re.getString("joblevel");
        JSONObject resp = re.getJSONObject("data");
        JSONArray items = resp.getJSONArray("items");
        JSONArray returnItems = new JSONArray();
        if(!Joblevel.equals("000")){
            for(Object item: items){
                JSONObject jItem  = (JSONObject) item;
                String joblevel = jItem.getString("joblevel");
                if(joblevel.equals(Joblevel)) {
                    returnItems.put(item);
                }
            }
        }else{
            returnItems = returnItems;
        }
        JSONObject resp2 = new JSONObject();
        resp2.put("items",returnItems);
        return Response.status(Response.Status.OK).entity(resp2.toString()).build();
    }
}

