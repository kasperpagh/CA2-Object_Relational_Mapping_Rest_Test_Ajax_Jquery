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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author pagh
 */
@Entity
@NamedQueries(
{
    @NamedQuery(name = "Hobby.findAll", query = "SELECT h FROM Hobby h")
})
public class Hobby implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(mappedBy = "hobbyList", cascade = CascadeType.ALL)
    private Collection<Person> personList;

 

    public void addHobbyToPerson(Person p)
    {
        if (personList == null)
        {
            personList = new ArrayList();
            personList.add(p);
        }
        else
        {
            personList.add(p);
        }
    }

    public Hobby()
    {
    }

    public Hobby(String name, String description, List<Person> personList)
    {
        this.name = name;
        this.description = description;
        this.personList = personList;
    }
    
    public Hobby(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Collection<Person> getPersonList()
    {
        return personList;
    }

    public void setPersonList(Collection<Person> personList)
    {
        this.personList = personList;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

}
