/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author pagh
 */
@Entity
@NamedQueries(
{
    @NamedQuery(name = "Phone.findByNumber", query = "SELECT p FROM Phone p WHERE p.number = :number")
})
public class Phone implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int number;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private InfoEntity infoEntity;
    private Person person;

    public Phone()
    {
    }

    public Phone(int number, String description, InfoEntity infoEntity, Person person)
    {
        this.number = number;
        this.description = description;
        this.infoEntity = infoEntity;
        this.person = person;
    }
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {

        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public InfoEntity getInfoEntity()
    {
        return infoEntity;
    }

    public void setInfoEntity(InfoEntity infoEntity)
    {
        this.infoEntity = infoEntity;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {

        this.person = person;
    }

  

    

}
