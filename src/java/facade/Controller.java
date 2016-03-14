/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author kaspe
 */
public class Controller
{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU");
//    EntityManager em = emf.createEntityManager();

    //This one is twerking!
    public InfoEntity getPersonByPhoneNumber(int number)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("Phone.findByNumber", Phone.class);

            Phone p = (Phone) query.setParameter("number", number).getSingleResult();
            return p.getInfoEntity();
        }
        finally
        {
            em.close();
        }
    }

    public InfoEntity getCompanyByPhoneNumber(int number)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("Phone.findByNumber", Phone.class);

            Phone p = (Phone) query.setParameter("number", number).getSingleResult();
            return p.getInfoEntity();
        }
        finally
        {
            em.close();
        }
    }

    public InfoEntity getCompanyByCvr(String cvr)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("Company.findByCvr", Company.class);

            Company com = (Company) query.setParameter("cvr", cvr).getSingleResult();
            return com;
        }
        finally
        {
            em.close();
        }
    }

    public List<Person> getAllHobbyPractitioners(Hobby h)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            return new ArrayList(h.getPersonList());

        }
        finally
        {
            em.close();
        }
    }

    public List<InfoEntity> getPersonsByCity(CityInfo ci)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("CityInfo.findAddressesByCity", CityInfo.class);
            CityInfo c = (CityInfo) query.setParameter("city", ci.getCity()).getSingleResult();

            List<Address> AddressList = new ArrayList(c.getAddressList());

            List<InfoEntity> persList = new ArrayList();

            for (Address a : AddressList)
            {
                for (InfoEntity ie : a.getInfoEntityList())
                {
                    persList.add(ie);
                }
            }
            return persList;
        }
        finally
        {
            em.close();
        }
    }

}
