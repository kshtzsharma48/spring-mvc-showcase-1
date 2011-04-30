package net.nicoll.bookstore.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stephane Nicoll
 */
@XmlRootElement
public class Author extends BaseBookModel {

    private String firstName;
    private String lastName;
    private String email;

    public Author() {
    }

    public Author(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}