package com.minal.scheduler.api;

import com.minal.scheduler.dao.EmployeeDao;
import com.minal.scheduler.model.Employee;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class LoginService {
    @POST
    @Path("/login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateLogin(String req) {
        JSONObject reqJson = new JSONObject(req);
        final String email = reqJson.getString("email");
        final String password = reqJson.getString("psword");
        final JSONObject jsonObject;
        final EmployeeDao employeeDao = new EmployeeDao();
        //TODO Change validate login to user Model
        jsonObject = employeeDao.validateLogin(email, password);
        return Response.status(Response.Status.OK).entity(jsonObject.toString()).build();
    }
}
