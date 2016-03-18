/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import data.DataGen;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import exception.PersonNotFoundException;
import facade.Facade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Magnus
 */
public class Tester {

    public static void main(String[] args) {
            //Persistence.generateSchema("PU", null);
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        Facade cf = new Facade(emf);
        DataGen df = new DataGen(emf);
//
        df.createPerson();
        df.createCompany();
//     //   Company c = cf.getCompany("99847752");
        
//        List<Person> persons = cf.getPersons();
//        for (Person person : persons) {
//            System.out.println(person.getFirstName() + person.getEmail());
//        }
//        Person p = cf.getPerson("00991133");
//            System.out.println(p.getFirstName() + " " + p.getLastName());
//       // System.out.println(c.getName());

//        List<Person> out = cf.getPersons();
//        List<Company> outs = cf.getCompanyListWithMoreEmployees(20000);
//
        
//        for (int i = 0; i < outs.size(); i++) {
//            System.out.println(outs.get(i).getName());

//        }
//        Hobby hobb = new Hobby();
//        hobb.setId(2);
//        System.out.println(cf.getHobbiesCount(hobb));
        
        

    }
}
