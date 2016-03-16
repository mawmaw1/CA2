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

    public static void main(String[] args) throws PersonNotFoundException {
//            Persistence.generateSchema("PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        Facade cf = new Facade(emf);
        DataGen df = new DataGen(emf);
        

        df.createPerson();
        df.createCompany();
        
        
        
        Person p1 = new Person();
        
        p1.setFirstName("Karlot");
        p1.setLastName("Güggenheim");
        cf.addPerson(p1);
        
        
//        Company c = cf.getCompany("99847752");
//
//        Person p = cf.getPerson("00991133");
//            System.out.println(p.getFirstName() + " " + p.getLastName());
//        System.out.println(c.getName());
//        Hobby hobby = new Hobby();
//        hobby.setId(1);
//        
//        List<Person> out = cf.getPersonsFromHobby(hobby);
//            List<Company> out = cf.getCompanyListWithMoreEmployees(20000);
//        for(int i=0;i<out.size();i++){
//            System.out.println(out.get(i).getName() + " Id: " + out.get(i).getId());
//        }
        
        
    }
}
