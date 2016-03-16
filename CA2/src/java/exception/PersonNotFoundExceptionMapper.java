/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author Magnus
 */
public class PersonNotFoundExceptionMapper implements ExceptionMapper<PersonNotFoundException> {

    @Override
    public Response toResponse(PersonNotFoundException e) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ErrorMessage em = new ErrorMessage("Person not found", 404);
        return Response.status(404).entity(gson.toJson(em)).type(MediaType.APPLICATION_JSON).build();
    }

}
