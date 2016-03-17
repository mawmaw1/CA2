/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Company;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author kristoffernoga
 */
@Path("company")
public class CompanyEndpoint {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    Facade fc = new Facade(emf);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyEndpoint
     */
    public CompanyEndpoint() {
    }

    /**
     * Retrieves representation of an instance of endpoint.CompanyEndpoint
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        JsonArray result = new JsonArray();
        List<Company> companies = fc.getCompanies();
        for (Company c : companies) {
            JsonObject c1 = new JsonObject();
            c1.addProperty("name", c.getName());
            c1.addProperty("email", c.getEmail());
            c1.addProperty("description", c.getDescription());
            c1.addProperty("cvr", c.getCvr());
            c1.addProperty("NumEmployees", c.getNumEmployees());
            c1.addProperty("marketValue", c.getMarketValue());
            c1.addProperty("address", c.getAddress().getStreet() + " " + c.getAddress().getAdditionalInfo());
            c1.addProperty("zip", c.getAddress().getCityInfo().getZip());
            c1.addProperty("city", c.getAddress().getCityInfo().getCity());

            JsonArray phone = new JsonArray();
            List<Phone> phones = c.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getPhoneNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            c1.add("phonenumbers", phone);

            result.add(c1);
        }

        return gson.toJson(result);
    }

    /**
     * PUT method for updating or creating an instance of CompanyEndpoint
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
