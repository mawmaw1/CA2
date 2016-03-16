/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import exception.ErrorMessage;

/**
 *
 * @author Magnus
 */
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Response out = Response.status(500).entity(gson.toJson(new ErrorMessage("Internal server error", 500))).build();
        return out;
    }
}
