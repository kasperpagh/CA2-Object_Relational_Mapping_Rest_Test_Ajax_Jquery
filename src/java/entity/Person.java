/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author pagh
 */
@Entity
public class Person extends InfoEntity implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @ManyToMany
    private Collection<Hobby> hobbyList;

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

    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }



    
}
