package com.company.data;

import java.io.Serializable;

public class Place implements Serializable {
    //region enum Planet

    public enum Planet {
        earth("Земля"),
        moon("Луна"),
        mars("Марс"),
        mercury("Меркурий");

        private final String value;

        Planet(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //endregion

    //region Fields

    private int id;
    private int length;
    private int width;
    private String landlord;
    private double price;
    private Planet planet;

    //endregion

    //region Constructor

    public Place(int id, int length, int width, String landlord, double price, Planet planet) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.landlord = landlord;
        this.price = price;
        this.planet = planet;
    }

    //endregion

    //region Getters

    public int getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public String getLandlord() {
        return landlord;
    }

    public double getPrice() {
        return price;
    }

    public Planet getPlanet() {
        return planet;
    }

    //endregion

    //region Setters

    public void setPrice(double price) {
        this.price = price;
    }

    //endregion
}