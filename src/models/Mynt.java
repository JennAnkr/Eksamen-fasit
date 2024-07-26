package models;

import java.util.Date;

public class Mynt extends Funn {
    private int diameter;
    private String metal;

    public Mynt(int funnId, int personId, int museumId, String locationFound, Date dateFound, int assumedYear, int diameter, String metal) {
        super(funnId, personId, museumId, locationFound, dateFound, assumedYear);
        this.diameter = diameter;
        this.metal = metal;
    }

    public int getDiameter() {
        return diameter;
    }

    public String getMetal() {
        return metal;
    }
}