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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author pagh
 */
@Entity
@NamedQueries(
{
    @NamedQuery(name = "InfoEntity.findAll", query = "SELECT i FROM InfoEntity i")
})
@Inheritance(strategy = InheritanceType.JOINED)

@Table(name = "InfoEntity")
public class InfoEntity implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    private String email;

    @OneToMany(mappedBy = "infoEntity", cascade = CascadeType.ALL)
    private Collection<Phone> phoneList;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    public void addPhoneToInfoEntity(Phone pho)
    {
        if(phoneList == null)
        {
            phoneList = new ArrayList();
            phoneList.add(pho);
        }
        else
        {
            phoneList.add(pho);
        }            
    }

    public InfoEntity()
    {
    }

    public InfoEntity(String email, Collection<Phone> phoneList, Address address)
    {
        this.email = email;
        this.phoneList = phoneList;
        this.address = address;
    }

    public Collection<Phone> getPhoneList()
    {
        return phoneList;
    }

    public void setPhoneList(Collection<Phone> phoneList)
    {
        this.phoneList = phoneList;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
