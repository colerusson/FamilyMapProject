package dao;

import model.Person;

import java.sql.Connection;
import java.util.List;

public class PersonDao {
    private final Connection conn;

    public PersonDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Person person) {
    }

    public Person getPersonByID(String personID) {
        return null;
    }

    public List<Person> getPersonsForUser(String username) {
        return null;
    }

    public List<Person> getPersonsByGender(String gender) {
        return null;
    }

    public List<Person> getPersonsByLastName(String lastName) {
        return null;
    }

    public void deleteRow() {
    }

    public void deleteTable() {

    }

}
