/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import data.DataGen;
import entity.Company;
import entity.Person;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Magnus
 */
public class FacadeTest {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TESTPU");
    private Facade cf = new Facade(emf);
    private static DataGen df = new DataGen(emf);



    public FacadeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Persistence.generateSchema("TESTPU", null);
        
        df.createCompany();
        df.createPerson();

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testGetPersons() {
        List<Person> out = cf.getPersons();
        for (Person p : out) {
            System.out.println(p.getFirstName());
        }

    }

    @Test
    public void testGetZip() {
        List<String> out = cf.getAllZip();
        for (String s : out) {
            System.out.println(s);
        }
    }

    @Test
    public void testGetCompany() {
        List<Company> out = cf.getCompanyListWithMoreEmployees(20000);
        for (Company c : out) {
            System.out.println("Company CVR: " + c.getCvr());
        }
    }
}
