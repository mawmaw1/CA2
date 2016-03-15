/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
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
            TypedQuery tq = em.createQuery("select p.infoEntity from Phone p where p.phoneNumber = :phoneNumber", InfoEntity.class);
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

    public Person createPerson(Person person) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();

            InfoEntity ie = em.find(InfoEntity.class, person.getId());
            Phone ph1 = new Phone();
            ph1.setPhoneNumber("22334455");
            ph1.setInfoEntity(ie);
            ph1.getInfoEntity().setId(ie.getId());
            ie.addPhoneNumber(ph1);
            
            Address a1 = new Address();
            a1.setStreet("RandomStreet");

            em.getTransaction().begin();
            em.persist(ie);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return person;
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
