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
    @NamedQuery(name = "CityInfo.findAddressesByCity", query = "SELECT c FROM CityInfo c WHERE c.city = :city")
})
public class CityInfo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int zipCode;
    private String city;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Address> addressList;
    

    public void addAddressToCityInfo(Address a)
    {
        if (addressList == null)
        {
            addressList = new ArrayList();
            addressList.add(a);
        }
        else
        {
            addressList.add(a);
        }
    }

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
