/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import data.DataGen;
import entity.Address;
import entity.CityInfo;
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
            TypedQuery<Person> tq = em.createQuery("select p from Person p join p.phones ph where ph.phoneNumber = :phoneNumber", Person.class);
            tq.setParameter("phoneNumber", phoneNumber);

            Person p = em.find(Person.class, tq.getSingleResult().getId());
            return p;

        } finally {
            em.close();
        }

    }

    @Override
    public Company getCompany(String phoneNumber) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Company> tq = em.createQuery("select c from Company c join c.phones ph where ph.phoneNumber = :phoneNumber", Company.class);
            tq.setParameter("phoneNumber", phoneNumber);

            Company c = em.find(Company.class, tq.getSingleResult().getId());
            return c;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsFromHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> tq = em.createQuery("select p from Person p join p.hobbies h where h.id = :hobbyId", Person.class);
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
            TypedQuery<Person> tq = em.createQuery("select p from InfoEntity p join p.address a where a.cityInfo.zip = :zip", Person.class);
            tq.setParameter("zip", zip);

            List<Person> out = tq.getResultList();

            return out;

        } finally {
            em.close();
        }
    }

    @Override
    public int getHobbiesCount(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> tq = em.createQuery("select p from Person p join p.hobbies h where h.id = :hobby", Person.class);
            tq.setParameter("hobby", hobby.getId());

            List<Person> out = tq.getResultList();

            return out.size();

        } finally {
            em.close();
        }
    }

    @Override
    public List<String> getAllZip() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<String> tq = em.createQuery("select c.zip from CityInfo c", String.class);

            List<String> out = tq.getResultList();

            return out;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Company> getCompanyListWithMoreEmployees(int number) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Company> tq = em.createQuery("select c from Company c where c.numEmployees >= :number", Company.class);
            tq.setParameter("number", number);

            List<Company> out = tq.getResultList();

            return out;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Person> tq = em.createQuery("select p from Person p", Person.class);
            List<Person> out = tq.getResultList();
            return out;

        } finally {
            em.close();
        }
    }

}
