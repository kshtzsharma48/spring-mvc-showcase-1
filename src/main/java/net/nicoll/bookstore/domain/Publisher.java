package net.nicoll.bookstore.domain;

/**
 * @author Stephane Nicoll
 */
public class Publisher extends BaseBookModel {

    private String name;

    public Publisher() {
    }

    public Publisher(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
