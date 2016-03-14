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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author pagh
 */
@Entity
public class CityInfo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int zipCode;
    private String city;
    
    @OneToMany
    private Collection<Address> addressList;
    


    public CityInfo()
    {
    }

    public CityInfo(int zipCode, String city, Collection<Address> addressList)
    {
        this.zipCode = zipCode;
        this.city = city;
        this.addressList = addressList;
    }
    

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public int getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(int zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Collection<Address> getAddressList()
    {
        return addressList;
    }

    public void setAddressList(Collection<Address> addressList)
    {
        this.addressList = addressList;
    }

    
    
}
