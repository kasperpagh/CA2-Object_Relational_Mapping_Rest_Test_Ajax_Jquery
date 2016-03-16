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
import java.util.List;
import java.util.Random;
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
        //  Persistence.generateSchema("bubberPU", null);
        Tester test = new Tester();
        test.CreateUsers();

    }

    //<editor-fold defaultstate="collapsed" desc="all arrays.."> 
//<editor-fold defaultstate="collapsed" desc="firstnames array">
    ArrayList<String> firstnames = new ArrayList<String>()
    {
        {
            add("Line");
            add("Bo");
            add("Peter");
            add("Hans");
            add("Sofie");
            add("Sarah");
            add("Steffan");
            add("Sebastian");
            add("Lars");
            add("Sofus");
            add("Preben");
        }
    };
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="lastnames array">
    ArrayList<String> lastnames = new ArrayList<String>()
    {
        {
            add("Pagh");
            add("Ryberg");
            add("Lefort");
            add("Hansen");
            add("Jensen");
            add("Bech");
            add("Clausdatter");
            add("Fich");
            add("Gudmand");
            add("Haseriis");

        }
    };
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="phone numbers array">
    ArrayList<Integer> numbers = new ArrayList<Integer>()
    {
        {
            add(31302383);
            add(28938572);
            add(29032001);
            add(30001920);
            add(98302211);
            add(12345678);
            add(13371337);
            add(98743212);
            add(88996521);
            add(83729012);
            add(86273291);
        }
    };
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="phone desc array">
    ArrayList<String> PhoneDesc = new ArrayList<String>()
    {
        {
            add("Home Number");
            add("Work Number");
            add("Cell phone number");
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hobby names array">
    ArrayList<String> hobbies = new ArrayList<String>()
    {
        {
            add("Ice skating");
            add("Gaming");
            add("Cocaine");
            add("Parties");
            add("Archery");
            add("Football");
            add("Soccer");
            add("Programming");
            add("Relaxing");
            add("Vacations");
            add("Mountain Climbing");
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Hobby desc array">
    ArrayList<String> hobbiesdesc = new ArrayList<String>()
    {
        {
            add("this is my jam");
            add("just cant do this enough");
            add("I love this!");
            add("Dayum this is the shit");
            add("Because i can, duh?");
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Company names array">
    ArrayList<String> companynames = new ArrayList<String>()
    {
        {
            add("Elgiganten");
            add("Fona");
            add("Microsoft");
            add("Sony");
            add("Haribo");
            add("Harboe");
            add("Rynkeby");
            add("Apple");
            add("Phillips");
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Company desc array">
    ArrayList<String> companydesc = new ArrayList<String>()
    {
        {
            add("Big Company");
            add("Small Company");
            add("Makes Stuff");
            add("Some fancy description");
            add("Nothing to see here, move along.");
            add("We are this damn company.");
            add("Company Description 101");
            add("I used to be a big company but then i took an arrow to the knee.");
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CVR array">
    ArrayList<Integer> cvr = new ArrayList<Integer>()
    {
        {
            add(72837230);
            add(12353213);
            add(53235612);
            add(87842436);
            add(93728396);
            add(97302938);
            add(45280292);
            add(30382010);
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="numEmployees array">
    ArrayList<Integer> numEmployees = new ArrayList<Integer>()
    {
        {
            add(50);
            add(3225);
            add(30827);
            add(3025);
            add(9102);
            add(1520);
            add(502);
            add(302);
            add(502);
            add(302);
            add(71);
            add(132);
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="marketvalue array">
    ArrayList<Double> marketvalue = new ArrayList<Double>()
    {
        {
            add(72837230.00);
            add(507237.00);
            add(302772.00);
            add(24552.00);
            add(582373820.00);
            add(100000000.00);
            add(72837230392.00);
            add(5352352.00);
            add(353235.00);
            add(382375823.00);
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="cities array">
    ArrayList<String> cities = new ArrayList<String>()
    {
        {
            add("lyngby");
            add("Scanning");
            add("Udbetaling");
            add("eBrevsprækken");
            add("Hellerup");

        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="citieszipcodes array">
    ArrayList<Integer> citieszipcodes = new ArrayList<Integer>()
    {
        {
            add(2800);
            add(555);
            add(894);
            add(897);
            add(2900);

        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addresses array">
    ArrayList<String> addresses = new ArrayList<String>()
    {
        {
            add("Nørgårdsvej 30");
            add("Svigerupvej 27");
            add("Sennepsvej 12");
            add("Prinsessevej 22");
            add("Søndersvigervej 27");
            add("Poppelhegnet 22");
            add("Sorgenfrigårdsvej 2");
            add("Herlev Bymidte");
            add("Langgårdsvej 6");
        }
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="addresses desc array">
    ArrayList<String> addressesdesc = new ArrayList<String>()
    {
        {
            add("Her er adressens desc.");
            add("Flotte lejligheder.");
            add("Man skulle tro wc'et virkede.");
            add("visit me tonight xoxo");
            add("Min studiebolig");
            add("når du er sulten for sjov..?");
        }
    };
//</editor-fold>
//</editor-fold>

    Random rand = new Random();

    public void CreateUsers()
    {
        Persistence.generateSchema("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA2-Object_Relational_Mapping_Rest_Test_Ajax_JqueryPU");
        EntityManager em = emf.createEntityManager();

        for (int i = 0; i < 25; i++)
        {

            ///// person 1 
            Person ie = new Person(firstnames.get(rand.nextInt(firstnames.size())), lastnames.get(rand.nextInt(lastnames.size())), null);
            InfoEntity com = new Company(companynames.get(rand.nextInt(companynames.size())), companydesc.get(rand.nextInt(companydesc.size())), cvr.get(rand.nextInt(cvr.size())).toString(), numEmployees.get(rand.nextInt(numEmployees.size())), marketvalue.get(rand.nextInt(marketvalue.size())));

            Phone p = new Phone(numbers.get(rand.nextInt(numbers.size())), PhoneDesc.get(rand.nextInt(PhoneDesc.size())), com, ie);
            ie.addPhoneToInfoEntity(p);

            Hobby hobby1 = new Hobby();
            hobby1.setName(hobbies.get(rand.nextInt(hobbies.size())));
            hobby1.setDescription(hobbiesdesc.get(rand.nextInt(hobbiesdesc.size())));
            Hobby hobby2 = new Hobby();
            hobby2.setName(hobbies.get(rand.nextInt(hobbies.size())));
            hobby2.setDescription(hobbiesdesc.get(rand.nextInt(hobbiesdesc.size())));

            ie.addHobbyToPerson(hobby1);
            ie.addHobbyToPerson(hobby2);
            hobby1.addHobbyToPerson(ie);
            hobby2.addHobbyToPerson(ie);

            em.getTransaction().begin();
            em.persist(ie);
            em.getTransaction().commit();
            // new Company(name, description, cvr, i, i)
            //InfoEntity com = new Company(companynames.get(rand.nextInt(companynames.size())), companydesc.get(rand.nextInt(companydesc.size())), cvr.get(rand.nextInt(cvr.size())).toString(), numEmployees.get(rand.nextInt(numEmployees.size())), marketvalue.get(rand.nextInt(marketvalue.size())));
            Phone p1 = new Phone(numbers.get(rand.nextInt(numbers.size())), PhoneDesc.get(rand.nextInt(PhoneDesc.size())), com, ie);
            com.addPhoneToInfoEntity(p1);

            em.getTransaction().begin();
            em.persist(com);
            em.getTransaction().commit();

            int getpos = rand.nextInt(cities.size()); // so both get the right value..

            CityInfo ci = new CityInfo();
            ci.setCity(cities.get(getpos));
            ci.setZipCode(citieszipcodes.get(getpos));
            Address a = new Address(addresses.get(rand.nextInt(addresses.size())), addressesdesc.get(rand.nextInt(addressesdesc.size())));
            ci.addAddressToCityInfo(a);
            a.addPersonToAddress(ie);

            em.getTransaction().begin();
            em.persist(ci);
            em.getTransaction().commit();

        }

    }

}
