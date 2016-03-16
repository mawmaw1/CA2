/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Company;
import entity.Hobby;
import entity.Person;
import exception.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author Magnus
 */
public interface iFacade {

    public List<Person> getPersons();
    public List<Company> getCompanies();
    public Person getPerson(String phoneNumber);

    public Company getCompany(String phoneNumber);

    public List<Person> getPersonsFromHobby(Hobby hobby);

    public List<Person> getPersons(String zip);

    public int getHobbiesCount(Hobby hobby);

    public List<String> getAllZip();

    public List<Company> getCompanyListWithMoreEmployees(int number);
    public Person deletePerson(int id) throws PersonNotFoundException;
    public Person editPerson(int id) throws PersonNotFoundException;
    public Person addPerson(Person p);
    
}
