/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Magnus
 */
@Provider
public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {

    @Override
    public Response toResponse(PersonNotFoundException e) {
        JsonObject jo = new JsonObject();
        
       jo.addProperty("code", "404");
       jo.addProperty("message", e.getMessage());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        
        ErrorMessage em = new ErrorMessage(e,"Person not found", 404);
        return Response.status(Response.Status.NOT_FOUND).entity(jo.toString()).build();
    }

}
