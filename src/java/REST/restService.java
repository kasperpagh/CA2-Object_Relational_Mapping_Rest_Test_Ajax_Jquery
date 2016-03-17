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
import entity.InfoEntity;
import entity.Person;
import exceptions.PersonNotFoundException;
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
public class restService
{

    Gson gson;
    Controller c = new Controller();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ApiResource
     */
    public restService()
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

    public String getAllPersons()
    {

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

    public String getPersonByPhone(@PathParam("number") Integer phone) throws PersonNotFoundException
    {

        JsonObject jO = new JsonObject();
        if (c.getPersonByPhoneNumber(phone) == null)
        {
            throw new PersonNotFoundException("No person with the given phone exsists!");
        }
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
    public String getAllPersonsShowContactInfo()
    {
        return gson.toJson(c.getAllPersonsContactInfo());
    }

    @GET
    @Path("/contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonContactInfo(@PathParam("id") Integer id) throws PersonNotFoundException
    {
        if (c.getPersonContactInfo(id) == null)
        {
            throw new PersonNotFoundException("No person with the given id exsists!");
        }
        return gson.toJson(c.getPersonContactInfo(id));
    }

    @GET
    @Path("/complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") Integer id) throws PersonNotFoundException, Throwable
    {
        if (c.getPersonById(id) == null)
        {
            throw new PersonNotFoundException("No person with the given id exsists!");
        }
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

        JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();

        String firstname = jsonIn.get("firstName").getAsString();
        String lastname = jsonIn.get("lastName").getAsString();

        JsonArray personHobbies = jsonIn.get("HobbyList").getAsJsonArray();
        List<Hobby> hobbyList = new ArrayList();
        for (int i = 0; i < personHobbies.size(); i++)
        {
            JsonObject currentHobby = (JsonObject) personHobbies.get(i);

            hobbyList.add(new Hobby(currentHobby.get("HobbyName").getAsString()));
        }

        Person newPerson = new Person(firstname, lastname, hobbyList);

        c.createNewPerson(newPerson);
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
    public void editPerson(@PathParam("id") Integer id, String personJson) throws PersonNotFoundException
    {

        Person personToEdit = null;

        if ((personToEdit = c.getPersonById(id)) != null)
        {
            JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();

            String firstname = jsonIn.get("firstName").getAsString();
            String lastname = jsonIn.get("lastName").getAsString();

            Person personIn = new Person(id, firstname, lastname);

            JsonArray personHobbies = jsonIn.get("HobbyList").getAsJsonArray();
            List<Hobby> HobbyList = new ArrayList();
            for (int i = 0; i < personHobbies.size(); i++)
            {
                JsonObject currentHobby = (JsonObject) personHobbies.get(i);

                personIn.addHobbyToPerson(new Hobby(currentHobby.get("HobbyName").getAsString()));
            }

            personToEdit = c.editPerson(personIn);

        }
        else
        {
            throw new PersonNotFoundException("No person with the given id exsists!");
        }

    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("id") Integer id) throws PersonNotFoundException
    {
        if(c.getPersonById(id) == null)
        {
            throw new PersonNotFoundException("No person with the given id exsists!");
        }
        c.deletePerson(c.getPersonById(id));
    }

}
