/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author pagh
 */
@Entity
@NamedQueries(
{
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
})
public class Person extends InfoEntity implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Hobby> hobbyList;

    public void addHobbyToPerson(Hobby h)
    {
        if(hobbyList == null)
        {
            hobbyList = new ArrayList();
            hobbyList.add(h);
        }
        else
        {
            hobbyList.add(h);
        }
    }
    public Person()
    {
    }

    public Person(String firstName, String lastName, Collection<Hobby> hobbyList)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbyList = hobbyList;
    }

    public Collection<Hobby> getHobbyList()
    {
        return hobbyList;
    }

    public void setHobbyList(List<Hobby> hobbyList)
    {
        this.hobbyList = hobbyList;
    }
    

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(Integer id)
    {
        this.id = id;
    }



    
}
