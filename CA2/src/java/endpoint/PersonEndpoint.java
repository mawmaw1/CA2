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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author kristoffernoga
 */
@Path("person")
public class PersonEndpoint {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    Facade fc = new Facade(emf);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonEndpoint
     */
    public PersonEndpoint() {
    }

    /**
     * Retrieves representation of an instance of endpoint.PersonEndpoint
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {
        JsonArray result = new JsonArray();
        List<Person> persons = fc.getPersons();
        for (Person person : persons) {
            JsonObject p1 = new JsonObject();
            p1.addProperty("firstname", person.getFirstName());
            p1.addProperty("lastname", person.getLastName());
            p1.addProperty("email", person.getEmail());
            p1.addProperty("address", person.getAddress().getStreet() + " " + person.getAddress().getAdditionalInfo());
            p1.addProperty("zip", person.getAddress().getCityInfo().getZip());
            p1.addProperty("city", person.getAddress().getCityInfo().getCity());

            JsonArray phone = new JsonArray();
            List<Phone> phones = person.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getPhoneNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            p1.add("phonenumbers", phone);

            JsonArray hobby = new JsonArray();
            List<Hobby> hobbies = person.getHobbies();
            for (Hobby h : hobbies) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("name", h.getName());
                p2.addProperty("description", h.getDescription());
                hobby.add(p2);
            }
            p1.add("hobbies", hobby);

            result.add(p1);
        }
        String endresult = gson.toJson(result);
        return result.toString();
    }

    @GET
    @Path("complete/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByID(@PathParam("number") int number) {
        Person person = fc.getPerson(number);
        JsonObject out = new JsonObject();
        out.addProperty("firstname", person.getFirstName());
            out.addProperty("lastname", person.getLastName());
            out.addProperty("email", person.getEmail());
            out.addProperty("address", person.getAddress().getStreet() + " " + person.getAddress().getAdditionalInfo());
            out.addProperty("zip", person.getAddress().getCityInfo().getZip());
            out.addProperty("city", person.getAddress().getCityInfo().getCity());

            JsonArray phone = new JsonArray();
            List<Phone> phones = person.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getPhoneNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            out.add("phonenumbers", phone);

            JsonArray hobby = new JsonArray();
            List<Hobby> hobbies = person.getHobbies();
            for (Hobby h : hobbies) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("name", h.getName());
                p2.addProperty("description", h.getDescription());
                hobby.add(p2);
            }
            out.add("hobbies", hobby);
        
        return gson.toJson(out);
        // return out.toString();
    }
    
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactInfo() {
        JsonArray result = new JsonArray();
        List<Person> persons = fc.getPersons();
        for (Person person : persons) {
            JsonObject p1 = new JsonObject();
            p1.addProperty("firstname", person.getFirstName());
            p1.addProperty("lastname", person.getLastName());
            p1.addProperty("email", person.getEmail());

            JsonArray phone = new JsonArray();
            List<Phone> phones = person.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getPhoneNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            p1.add("phonenumbers", phone);

            result.add(p1);
        }
        return gson.toJson(result);
        //return result.toString();
    }
    
    @GET
    @Path("contactinfo/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactinfoByID(@PathParam("number") int number) {
        Person person = fc.getPerson(number);
        JsonObject out = new JsonObject();
        out.addProperty("firstname", person.getFirstName());
            out.addProperty("lastname", person.getLastName());
            out.addProperty("email", person.getEmail());

            JsonArray phone = new JsonArray();
            List<Phone> phones = person.getPhones();
            for (Phone p : phones) {
                JsonObject p2 = new JsonObject();
                p2.addProperty("number", p.getPhoneNumber());
                p2.addProperty("description", p.getDescription());
                phone.add(p2);
            }
            out.add("phonenumbers", phone);
        
        return gson.toJson(out);
        // return out.toString();
    }

    /**
     * PUT method for updating or creating an instance of PersonEndpoint
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
