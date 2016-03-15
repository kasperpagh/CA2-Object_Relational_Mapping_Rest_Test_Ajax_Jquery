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

    public void createNewInfoEntity(InfoEntity ie)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(ie);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }
    
    public List<Hobby> getAllHobbies()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("Hobby.findAll", Hobby.class);
            List<Hobby> hobList = query.getResultList();
            for (Hobby c : hobList)
            {
                hobList.remove(c);
            }
            return hobList;
        }
        finally
        {
            em.close();
        }
        
    }

    public List<Company> getCompaniesWithMoreThanNEmployees(int n)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("Company.findAll", Company.class);
            List<Company> comList = query.getResultList();
            for (Company c : comList)
            {
                comList.remove(c);
            }
            return comList;
        }
        finally
        {
            em.close();
        }
    
    }

    //Find det objekt du ønsker at redigere, og pass det da til denne method!
    public void editInfoEntity(InfoEntity ie)
    {
        EntityManager em = emf.createEntityManager();
        try
        {

            em.getTransaction().begin();
            InfoEntity ie2 = em.find(InfoEntity.class, ie.getId());
            ie2 = ie;
            em.persist(ie2);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public void deleteInfoEntity(InfoEntity ie)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.remove(ie);
            em.getTransaction().commit();
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

    public List<Integer> getListOfAllZipCodes()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            Query query = em.createNamedQuery("CityInfo.findAll", CityInfo.class);
            List<CityInfo> cl = query.getResultList();
            List<Integer> zipCodeList = new ArrayList();
            for (CityInfo ci : cl)
            {
                zipCodeList.add(ci.getZipCode());
            }
            return zipCodeList;
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

    public int getCountOfHobbyPractitioners(Hobby h)
    {
        return getAllHobbyPractitioners(h).size();
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
