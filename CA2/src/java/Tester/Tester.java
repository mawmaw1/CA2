/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import facade.Facade;
import javax.persistence.EntityManager;
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
//        EntityManager em = emf.createEntityManager();
//        Person p = new Person();
//        p.setFirstName("Pter");
//        p.setEmail("somemail");
//        p.setLastName("Hansen");
//        Phone ph = new Phone();
//        ph.setPhoneNumber("1234");
//        InfoEntity ie = em.find(InfoEntity.class, 1);
//        ph.setInfoEntity(ie);
//        
//        ph.setInfoEntity(ph.getInfoEntity());
//        
//        try {
//            em.getTransaction().begin();
//            em.persist(ph);
//            
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Person p = cf.getPerson("1234");
        System.out.println(p.getFirstName() + " " + p.getLastName());
    }
}
