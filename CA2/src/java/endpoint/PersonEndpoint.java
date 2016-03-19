/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Address;
import entity.CityInfo;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import exception.PersonNotFoundException;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
            p1.addProperty("id", person.getId());
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
        return gson.toJson(result);

    }

    @GET
    @Path("complete/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByID(@PathParam("number") int number) throws PersonNotFoundException {
        
        Person person = fc.getPerson(number);
        
        
        JsonObject out = new JsonObject();
        out.addProperty("id", person.getId());
        out.addProperty("firstname", person.getFirstName());
        out.addProperty("lastname", person.getLastName());
        out.addProperty("email", person.getEmail());
        JsonObject address = new JsonObject();
        address.addProperty("street", person.getAddress().getStreet());
        address.addProperty("additionalinfo", person.getAddress().getAdditionalInfo());

        // out.addProperty("address", person.getAddress().getStreet() + " " + person.getAddress().getAdditionalInfo());
        out.add("address", address);
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
    }

    @DELETE
    @Path("/delete/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePersonByID(@PathParam("number") int number) throws PersonNotFoundException {
        Person p = fc.deletePerson(number);
        JsonObject out = new JsonObject();
        out.addProperty("name", p.getFirstName() + " " + p.getLastName());

        return gson.toJson(out);
    }

    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonsContactInfo() {
        JsonArray result = new JsonArray();
        List<Person> persons = fc.getPersons();
        for (Person person : persons) {
            JsonObject p1 = new JsonObject();
            p1.addProperty("id", person.getId());
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
    }

    @GET
    @Path("contactinfo/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactinfoByID(@PathParam("number") int number) throws PersonNotFoundException {
        Person person = fc.getPerson(number);
        JsonObject out = new JsonObject();
        out.addProperty("id", person.getId());
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
    }

    @POST
    @Path("/complete/poster")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String person) throws PersonNotFoundException {
        JsonObject newPerson = new JsonParser().parse(person).getAsJsonObject();
        Person p = new Person();
        p.setFirstName(newPerson.get("firstname").getAsString());
        p.setLastName(newPerson.get("lastname").getAsString());
        p.setEmail(newPerson.get("email").getAsString());
        Address address = new Address();

        address.setAdditionalInfo(newPerson.getAsJsonObject("address").get("additionalinfo").getAsString());
        address.setStreet(newPerson.getAsJsonObject("address").get("street").getAsString());
//        address.setAdditionalInfo(newPerson.get("additionalinfo").getAsString());
//        address.setStreet(newPerson.get("street").getAsString());

        CityInfo city = new CityInfo();
        city.setCity(newPerson.get("city").getAsString());
        city.setZip(newPerson.get("zip").getAsString());
        address.setCityInfo(city);
        p.setAddress(address);

        JsonArray phonesArr = newPerson.get("phonenumbers").getAsJsonArray();

        for (JsonElement ph : phonesArr) {
            Phone pho = new Phone();
            System.out.println(ph.getAsJsonObject().get("number").getAsString());
            System.out.println(ph.getAsJsonObject().get("description").getAsString());
            pho.setPhoneNumber(ph.getAsJsonObject().get("number").getAsString());
            pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
            pho.setInfoEntity(p);
            p.addPhoneNumber(pho);
        }

        JsonArray hobbArr = newPerson.get("hobbies").getAsJsonArray();

        for (JsonElement hob : hobbArr) {
            Hobby ho = new Hobby();
            ho.setDescription(hob.getAsJsonObject().get("description").getAsString());
            ho.setName(hob.getAsJsonObject().get("name").getAsString());
            p.addHobby(ho);
        }

        p = fc.addPerson(p);

        return getPersonByID(p.getId());

    }

    /**
     * PUT method for updating or creating an instance of PersonEndpoint
     *
     * @param person
     * @param content representation for the resource
     * @return 
     */
    @PUT
    @Path("/editperson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPerson(String person) throws PersonNotFoundException {
        JsonObject newPerson = new JsonParser().parse(person).getAsJsonObject();
        Person p = fc.getPerson(newPerson.get("id").getAsInt());
        p.setFirstName(newPerson.get("firstname").getAsString());
        p.setLastName(newPerson.get("lastname").getAsString());
        p.setEmail(newPerson.get("email").getAsString());
        Address address = p.getAddress();

        address.setAdditionalInfo(newPerson.getAsJsonObject("address").get("additionalinfo").getAsString());
        address.setStreet(newPerson.getAsJsonObject("address").get("street").getAsString());
//        address.setAdditionalInfo(newPerson.get("additionalinfo").getAsString());
//        address.setStreet(newPerson.get("street").getAsString());

        CityInfo city = new CityInfo();
        city.setCity(newPerson.get("city").getAsString());
        city.setZip(newPerson.get("zip").getAsString());
        address.setCityInfo(city);
        p.setAddress(address);

        JsonArray phonesArr = newPerson.get("phonenumbers").getAsJsonArray();
        
        JsonElement ph = phonesArr.get(0);
        Phone pho = p.getPhones().get(0);
        pho.setPhoneNumber(ph.getAsJsonObject().get("number").getAsString());
        pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
        pho.setInfoEntity(p);
        List<Phone> phones = new ArrayList();
        phones.add(pho);
        p.setPhones(phones);
        
//        for (JsonElement ph : phonesArr) {
//            Phone pho = new Phone();
//            
//            pho.setPhoneNumber(ph.getAsJsonObject().get("number").getAsString());
//            pho.setDescription(ph.getAsJsonObject().get("description").getAsString());
//            pho.setInfoEntity(p);
//            p.addPhoneNumber(pho);
//        }

        JsonArray hobbArr = newPerson.get("hobbies").getAsJsonArray();
       // p.setHobbies(null);
        JsonElement hob = hobbArr.get(0);
        Hobby ho = p.getHobbies().get(0);
        ho.setDescription(hob.getAsJsonObject().get("description").getAsString());
        ho.setName(hob.getAsJsonObject().get("name").getAsString());
//        for (JsonElement hob : hobbArr) {
//            Hobby ho = new Hobby();
//            ho.setDescription(hob.getAsJsonObject().get("description").getAsString());
//            ho.setName(hob.getAsJsonObject().get("name").getAsString());
//            p.addHobby(ho);
//        }

        p = fc.editPerson(p);

        return getPersonByID(p.getId());

    }
}
