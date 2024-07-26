package models;

import java.util.Date;

public class Smykke extends Funn {
    private String type;
    private int estimatedValue;
    private String fileName;

    public Smykke(int funnId, int personId, int museumId, String locationFound, Date dateFound, int assumedYear, String type, int estimatedValue, String fileName) {
        super(funnId, personId, museumId, locationFound, dateFound, assumedYear);
        this.type = type;
        this.estimatedValue = estimatedValue;
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public int getEstimatedValue() {
        return estimatedValue;
    }

    public String getFileName() {
        return fileName;
    }

}