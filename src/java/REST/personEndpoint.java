/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Hobby;
import entity.Person;
import facade.Controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Steffen
 */
@Path("person")
public class personEndpoint
{

    Gson gson;
    Controller c = new Controller();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public personEndpoint()
    {
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
   
        List<Person> receivedList = c.getAllPersons();
        JsonArray jA = new JsonArray();
        for (Person per : receivedList)
        {
            JsonObject jO = new JsonObject();
            jO.addProperty("firstName", per.getFirstName());
            jO.addProperty("lastName", per.getLastName());
            JsonArray jaHobby = new JsonArray();
            for (Hobby h : per.getHobbyList())
            {
                JsonObject jo2 = new JsonObject();
                jo2.addProperty("HobbyName", h.getName());
                jaHobby.add(jo2);
            }
            jO.add("HobbyList", jaHobby);
            jA.add(jO);
        }

        return gson.toJson(jA);

    }
        


    @GET
    @Path("/personbyphone/{number}")
    @Produces(MediaType.APPLICATION_JSON)

    public String getPersonByPhone(@PathParam("number") Integer phone) {
        
            JsonObject jO = new JsonObject();
            jO.addProperty("firstName", c.getPersonByPhoneNumber(phone).getFirstName());
            jO.addProperty("lastName", c.getPersonByPhoneNumber(phone).getLastName());
            JsonArray jaHobby = new JsonArray();
            for (Hobby h : c.getPersonByPhoneNumber(phone).getHobbyList())
            {
                JsonObject jO2 = new JsonObject();
                jO2.addProperty("HobbyName", h.getName());
                jaHobby.add(jO2);
            }
            jO.add("HobbyList", jaHobby);
            
        

        return gson.toJson(jO);
    

    }

    @GET
    @Path("/contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPersonsShowContactInfo(){
        return gson.toJson(c.getAllPersonsContactInfo());
    }

    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfo(@PathParam("id") Integer id){
        return gson.toJson(c.getPersonContactInfo(id));
    }

    @GET
    @Path("/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") Integer id)
    {
            JsonObject jO = new JsonObject();
            jO.addProperty("firstName", c.getPersonById(id).getFirstName());
            jO.addProperty("lastName", c.getPersonById(id).getLastName());
            JsonArray jaHobby = new JsonArray();
            for (Hobby h : c.getPersonById(id).getHobbyList())
            {
                JsonObject jO2 = new JsonObject();
                jO2.addProperty("HobbyName", h.getName());
                jaHobby.add(jO2);
            }
            jO.add("HobbyList", jaHobby);
            
        

        return gson.toJson(jO);
    

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(String personJson)
    {
        Person person = gson.fromJson(personJson, Person.class);
        c.createNewPerson(person);
    }

    /**
     * PUT method for updating or creating an instance of ApiResource
     *
     * @param id
     * @param firstname
     * @param lastname
     * @param hobbyList
     * @param content representation for the resource
     */
    @PUT
    @Path("/edit/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editPerson(@PathParam("id") Integer id, String personJson) 
    {        
        
        
        String jSonString = "";
            
        Person personToEdit = null;
        
        if ((personToEdit = c.getPersonById(id)) != null) {
            JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();

            String firstname = jsonIn.get("firstname").getAsString();
            String lastname = jsonIn.get("lastname").getAsString();
            
            Person personIn = new Person(id, firstname, lastname);
            
            JsonArray personHobbies = jsonIn.get("hobbies").getAsJsonArray();
            List<Hobby> HobbyList = new ArrayList();
            for (int i = 0; i < personHobbies.size(); i++) {
                JsonObject currentHobby = (JsonObject) personHobbies.get(i);
 
                personIn.addHobbyToPerson(new Hobby(currentHobby.get("name").getAsString(),
                        currentHobby.get("description").getAsString()));
            }
            
            personToEdit = c.editPerson(personIn);
            
         
            JsonObject jsonOut = new JsonObject();
            jsonOut.addProperty("id", personToEdit.getId());
            jsonOut.addProperty("firstname", personToEdit.getFirstName());
            jsonOut.addProperty("lastname", personToEdit.getLastName());
            jsonOut.add("hobbies", personHobbies);
            
            String finalJson = new Gson().toJson(jsonOut);
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(finalJson);
            jSonString = gson.toJson(je);
 
            return jSonString;
            
         
            
        }
      return jSonString;

   }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("id") String id){
        return c.deleteInfoEntity(id);
    }
    
    
}
