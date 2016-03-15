/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import facade.Controller;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pagh
 */
public class Tester
{

    public static void main(String[] args)
    {
        Persistence.generateSchema("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU");
        EntityManager em = emf.createEntityManager();

        Person ie = new Person("kasper", "pagh", null);
        
        Phone p = new Phone(1, "tester", ie);
        ie.addPhoneToInfoEntity(p);
        Hobby h = new Hobby();
        ie.addHobbyToPerson(h);
        h.addHobbyToPerson(ie);
        em.getTransaction().begin();
        em.persist(ie);
        em.getTransaction().commit();

        InfoEntity com = new Company("hat", "pretty hat", "bubber", 0, 0);
        Phone p1 = new Phone(2, "compTest", com);
        com.addPhoneToInfoEntity(p1);

        em.getTransaction().begin();
        em.persist(com);
        em.getTransaction().commit();
        
        
        CityInfo ci = new CityInfo();
        ci.setCity("lyngby");
        ci.setZipCode(2800);
        Address a = new Address("Nørgårdsvej 30", "det er her skolen ligger!");
        ci.addAddressToCityInfo(a);
        a.addPersonToAddress(ie);
        
        em.getTransaction().begin();
        em.persist(ci);
        em.getTransaction().commit();

        Controller c = new Controller();

        System.out.println("her er test af getPersonByPhoneNumber() " + c.getPersonByPhoneNumber(1));

        System.out.println("her er test af getCompanyByPhoneNumber()" + c.getCompanyByPhoneNumber(2));

        System.out.println("her er test af getCompanyByCvr()" + c.getCompanyByCvr("bubber"));

        System.out.println("her er test af getAllHobbyPractitioners()" + c.getAllHobbyPractitioners(h));
        
        System.out.println("Her er test af getCountOfHobbyPractitioners: " + c.getCountOfHobbyPractitioners(h));
        
        System.out.println("her er test af getPersonsByCity()" + c.getPersonsByCity(ci));

        em.close();

    }

}
