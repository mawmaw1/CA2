/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import data.DataGen;
import entity.Person;
import facade.Facade;
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
        
        //df.createPerson();
        df.createCompany();
       
        System.out.println(DataGen.firstnames.length);
        System.out.println(DataGen.lastnames.length);
        System.out.println(DataGen.emails.length);
        System.out.println(DataGen.phonenumbers.length);
        System.out.println(DataGen.addresses.length);
        System.out.println(DataGen.numbers.length);
    }
}
