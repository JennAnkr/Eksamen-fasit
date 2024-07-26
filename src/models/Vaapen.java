package models;

import java.util.Date;

public class Vaapen extends Funn {
    private String type;
    private String material;
    private int weight;

    public Vaapen(int funnId, int personId, int museumId, String locationFound, Date dateFound, int assumedYear, String type, String material, int weight) {
        super(funnId, personId, museumId, locationFound, dateFound, assumedYear);
        this.type = type;
        this.material = material;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getWeight() {
        return weight;
    }

}