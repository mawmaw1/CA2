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

            String hobby = "";
            List<Hobby> hobbies = person.getHobbies();
            for (Hobby h : hobbies) {
                if (hobbies.size() == 1) {
                    hobby += h.getName();
                } else {
                    hobby += h.getName() + ",";
                }
            }
            p1.addProperty("hobbies", hobby);

            result.add(p1);
        }

        return gson.toJson(result);
    }

//    @GET
//    @Path("/{number}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getPersonByID(@PathParam("number") int number) {
//        return gson.toJson(countryfacade.getAllCountriesByPopulation(number));
//    }

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
