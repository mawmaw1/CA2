/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kristoffernoga
 */
public class DataGen {

    private EntityManagerFactory emf;

    public static String[] firstnames = {"Mads", "Søren", "Peter", "Lars", "Tobias", "Magnus", "Ingi", "Kristoffer", "Christoffer", "Christopher",
        "Hans", "David", "Jonathan", "Eske", "Michael", "Sebastian", "Kasper", "Jesper", "Sofus", "Thomas", "Dorte", "Svend", "Line",
        "Lotte", "Sofie", "Cecilie", "Pia", "Shakira", "Lotte", "Ane", "Anne", "Anna", "Pil", "Mette", "Helle", "Maiken"}; // length = 36
    public static String[] lastnames = {"Larsen", "Madsen", "Hansen", "Jensen", "Christensen", "Christiansen", "Wolff", "Dohn", "Vedersø", "Gjedde", "Weberg",
        "Smith", "Berner", "Hemmingsen", "Friis", "Bruhn", "Bruun", "Ward", "Nielsen", "Frederiksen", "Knobluach", "Lassen", "Herdahl", "Kokholm", "Carsentense",
        "Madsen", "Mikkelsen"}; // length = 27
    public static String[] emails = {"test@test.dk", "hejmeddig@hotmail.com", "sutmin@gmail.com", "randomord@live.dk", "datamatiker@cphbusiness.dk",
        "cph-kn140@cphbusiness.dk", "kanikkefindepåmere@hotmail.com", "computer@keyboard.mus", "LiverpoolFC@bedstehold.dk", "facebook@facebook.dk",
        "densidste@live.dk"}; // length = 11
    public static String[] phonenumbers = {"15839486", "58724645", "85848183", "99833373", "88485883", "33446669", "49950693",
        "85717724", "88692058", "00991133", "8829409", "77449922", "84722164", "99847752", "99583756", "84712236", "88503849", "99292003",
        "8837165", "86773615", "25546781"}; // length = 21
    public static String[] addresses = {"Sørengade", "Larsengade", "Petersgade", "Hansengade", "Cypresvej", "Gothersgade", "Jagtvej", "Slangerupgade",
        "Motorvej", "Cykelsti", "Enevang", "Supervang", "Trævang"}; // length = 13
    public static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}; // length = 15
    public static String[] desc = {"Mobil", "Fastnet", "Arbejde", "Hjemme"};
    public static String[] hobbies = {"Fiske", "Løbe", "Spise", "Svømme", "Sport", "Computer", "Programmering", "Læse"}; // lenght = 8
    public static String[] cnames = {"Facebook", "Google", "Amazon", "Microsoft", "BenQ", "Dell", "Apple", "Corsair", "Astro", "Logitech",
    "Samsung", "Sony", "TurtleBeach", "A-Jayz", "Random"}; // length = 15

    public DataGen(EntityManagerFactory e) {
        emf = e;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person createPerson() {
        Person person = null;
        for (int i = 0; i < 30; i++) {

            EntityManager em = getEntityManager();
            person = new Person();
            person.setFirstName(firstnames[randInt(0, 35)]);
            person.setLastName(lastnames[randInt(0, 26)]);
            person.setEmail(emails[randInt(0, 10)]);
            Hobby hobby = em.find(Hobby.class, randInt(1,10));
            //hobby.setName(hobbies[randInt(0,7)]);
            person.addHobby(hobby);

            try {
                em.getTransaction().begin();
                em.persist(person);
                em.getTransaction().commit();

                InfoEntity ie = em.find(InfoEntity.class, person.getId());
                Phone ph1 = new Phone();
                ph1.setPhoneNumber(phonenumbers[randInt(0, 19)]);
                ph1.setDescription(desc[randInt(0, 3)]);
                ph1.setInfoEntity(ie);
                ph1.getInfoEntity().setId(ie.getId());
                ie.addPhoneNumber(ph1);

                Address a1 = new Address();
                a1.setStreet(addresses[randInt(0, 12)]);
                a1.setAdditionalInfo(numbers[randInt(0, 14)]);
                CityInfo ci = em.find(CityInfo.class, DataGen.randInt(1, 1000));

                a1.setCityInfo(ci);
                ie.setAddress(a1);

                em.getTransaction().begin();
                em.persist(ie);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }
        return person;
    }
    
    public Company createCompany() {
        Company company = null;
        for (int i = 0; i < 30; i++) {

            EntityManager em = getEntityManager();
            company = new Company();
            company.setName(cnames[randInt(0, 14)]);
            company.setEmail(emails[randInt(0, 10)]);
            company.setCvr("" + randInt(1000000, 2000000));
            company.setNumEmployees(randInt(0, 30000));
            company.setMarketValue(randInt(0, 1_000_000_000));

            try {
                em.getTransaction().begin();
                em.persist(company);
                em.getTransaction().commit();

                InfoEntity ie = em.find(InfoEntity.class, company.getId());
                Phone ph1 = new Phone();
                ph1.setPhoneNumber(phonenumbers[randInt(0, 19)]);
                ph1.setDescription(desc[randInt(0, 3)]);
                ph1.setInfoEntity(ie);
                ph1.getInfoEntity().setId(ie.getId());
                ie.addPhoneNumber(ph1);

                Address a1 = new Address();
                a1.setStreet(addresses[randInt(0, 12)]);
                a1.setAdditionalInfo(numbers[randInt(0, 14)]);
                CityInfo ci = em.find(CityInfo.class, DataGen.randInt(1, 1000));

                a1.setCityInfo(ci);
                ie.setAddress(a1);

                em.getTransaction().begin();
                em.persist(ie);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }
        return company;
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
