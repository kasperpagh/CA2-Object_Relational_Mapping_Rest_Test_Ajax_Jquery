/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
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
public class Address implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Steet;
    private String additionalInfo;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private Collection<InfoEntity> infoEntityList;

    public void addPersonToAddress(InfoEntity ie)
    {
        if (infoEntityList == null)
        {
            infoEntityList = new ArrayList();
            infoEntityList.add(ie);
        }
        else
        {
            infoEntityList.add(ie);
        }
    }

    public Address(String Steet, String additionalInfo)
    {
        this.Steet = Steet;
        this.additionalInfo = additionalInfo;
    }

    public Address()
    {
    }

    public String getSteet()
    {
        return Steet;
    }

    public Collection<InfoEntity> getInfoEntityList()
    {
        return infoEntityList;
    }

    public void setInfoEntityList(Collection<InfoEntity> infoEntityList)
    {
        this.infoEntityList = infoEntityList;
    }

    public void setSteet(String Steet)
    {
        this.Steet = Steet;
    }

    public String getAdditionalInfo()
    {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo)
    {
        this.additionalInfo = additionalInfo;
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
