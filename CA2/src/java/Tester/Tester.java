/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import facade.Facade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Magnus
 */
public class Tester {
    public static void main(String[] args) {
//        Persistence.generateSchema("PU", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        Facade cf = new Facade(emf);
        
        System.out.println(cf.getCompany("1231"));
    }
}
