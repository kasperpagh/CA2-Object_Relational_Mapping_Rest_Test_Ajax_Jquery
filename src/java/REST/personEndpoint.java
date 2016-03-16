/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import entity.Hobby;
import entity.Person;
import facade.Controller;
import java.sql.ResultSet;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import test.Tester;

/**
 * REST Web Service
 *
 * @author Steffen
 */
@Path("person")
public class personEndpoint {

    Gson gson;
    Controller c = new Controller();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public personEndpoint() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Retrieves representation of an instance of REST.ApiResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersons() {
        
        Tester t = new Tester();
        t.CreateUsers();

        

        
        List<Person> john = c.getAllPersons();
        JsonArray ja = new JsonArray();

        
        for (Person per : john)
        {
            JsonObject jo = new JsonObject();
            jo.addProperty("firstName", per.getFirstName());
            jo.addProperty("lastName", per.getLastName());
            JsonArray jaHobby = new JsonArray();
            for (Hobby h : per.getHobbyList())
            {
                JsonObject jo2 = new JsonObject();
                jo2.addProperty("HobbyName", h.getName());
                jaHobby.add(jo2);
            }
            jo.add("HobbyList", jaHobby);
            ja.add(jo);
        }

        return gson.toJson(ja);
    }

    @GET
    @Path("/personbyphone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByPhone(@PathParam("number") Integer phone) {
        return gson.toJson(c.getPersonByPhoneNumber(phone));
    }
    
    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsShowContactInfo() {
        return gson.toJson(c.getAllPersonsContactInfo());
    }
    
    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfo(@PathParam("id") Integer id) {
        return gson.toJson(c.getPersonContactInfo(id));
    }

    @GET
    @Path("/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") Integer id) {
       return c.getPersonById(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(String personJson) {
        Person person = gson.fromJson(personJson, Person.class);
        c.createNewInfoEntity(person);
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void put(String content) {
    }
}
