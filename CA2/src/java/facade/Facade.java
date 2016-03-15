/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Magnus
 */
public class Facade implements iFacade {

    private EntityManagerFactory emf;

    public Facade(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person getPerson(String phoneNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery tq = em.createQuery("select p.id from InfoEntity p join p.phones ph where ph.phoneNumber = :phoneNumber", InfoEntity.class);
            tq.setParameter("phoneNumber", phoneNumber);
            
            Person p = em.find(Person.class, tq.getSingleResult());
            return p;
        } finally {
            em.close();
        }

    }

    @Override
    public Company getCompany(String phoneNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery tq = em.createQuery("select p.id from InfoEntity p join p.phones ph where ph.phoneNumber = :phoneNumber", InfoEntity.class);
            tq.setParameter("phoneNumber", phoneNumber);

            Company c = em.find(Company.class, tq.getSingleResult());
            return c;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsFromHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery tq = em.createQuery("select p from Person p join p.hobbies h where h.id = :hobbyId", InfoEntity.class);
            tq.setParameter("hobbyId", hobby.getId());

            return tq.getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons(String zip) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery tq = em.createQuery("select p from InfoEntity p join p.address a where a.cityInfo.zip = :zip", InfoEntity.class);
            tq.setParameter("zip", zip);

            List<Person> out = tq.getResultList();
            
            return out;

        } finally {
            em.close();
        }
    }

    @Override
    public int getHobbiesCount(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAllZip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Company> getCompanyListWithMoreEmployees(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
