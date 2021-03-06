package JUnit;

import deploy.DeploymentConfiguration;
import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import facade.Controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaspe Databasen skal være tom når man kører testen
 */
public class TestFacade {

    EntityManager em;
    Person ie;
    Phone p;
    Hobby h;
    Phone p1;
    CityInfo ci;
    Address a;
    InfoEntity com;

    public TestFacade() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        Persistence.generateSchema("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
        em = emf.createEntityManager();

        ie = new Person("kasper", "pagh", null);

        p = new Phone(1, "tester", ie);
        ie.addPhoneToInfoEntity(p);
        h = new Hobby();
        ie.addHobbyToPerson(h);
        h.addHobbyToPerson(ie);
        em.getTransaction().begin();
        em.persist(ie);
        em.getTransaction().commit();

        com = new Company("hat", "pretty hat", "bubber", 0, 0);
        p1 = new Phone(2, "compTest", com);
        com.addPhoneToInfoEntity(p1);

        em.getTransaction().begin();
        em.persist(com);
        em.getTransaction().commit();

        ci = new CityInfo();
        ci.setCity("lyngby");
        ci.setZipCode(2800);
        a = new Address("Nørgårdsvej 30", "det er her skolen ligger!");
        ci.addAddressToCityInfo(a);
        a.addPersonToAddress(ie);

        em.getTransaction().begin();
        em.persist(ci);
        em.getTransaction().commit();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void facadeTest() {
        Controller c = new Controller();

        Person pers = (Person) c.getPersonByPhoneNumber(1);
        int id = pers.getId();
        assertEquals(ie.getFirstName(), pers.getFirstName());
        pers = c.getPersonById(id);
        assertEquals(pers.getFirstName(), ie.getFirstName());
        assertTrue(c.getPersonById(id) != null);
        c.deletePerson(pers);
        assertTrue(c.getPersonById(id) == null);
    }
}
