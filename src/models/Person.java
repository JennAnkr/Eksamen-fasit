package models;

public class Person {
    private int id;
    private String name;
    private String mobileNumber;
    private String emailAddress;

    public Person(int id, String name, String mobileNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getMailAddress() {
        return emailAddress;
    }
}
