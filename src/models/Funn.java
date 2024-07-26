package models;

import java.util.Date;

public class Funn {
    private int funnId;
    private int personId;
    private int museumId;
    private String locationFound;
    private Date dateFound;
    private int assumedYear;

    public Funn(int funnId, int personId, int museumId, String locationFound, Date dateFound, int assumedYear) {
        this.funnId = funnId;
        this.personId = personId;
        this.museumId = museumId;
        this.locationFound = locationFound;
        this.dateFound = dateFound;
        this.assumedYear = assumedYear;
    }

    public int getFunnId() {
        return funnId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getMuseumId() {
        return museumId;
    }

    public String getPlaceOfDiscovery() {
        return locationFound;
    }

    public Date getTimeOfDiscovery() {
        return dateFound;
    }

    public int getAssumedYear() {
        return assumedYear;
    }

}