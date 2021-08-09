package com.company.data;

import java.io.*;
import java.util.ArrayList;

public class PlacesManager {
    //region Fields

    private int currentId;
    private ArrayList<Place> places;

    //endregion

    //region Constructor

    public PlacesManager() {
        currentId = 0;
        places = new ArrayList<>();
    }

    //endregion

    //region Private Methods

    private int findIndexById(int id) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }

    private int getMaxId() {
        int maxId = 0;
        for (int i = 0; i < places.size(); i++) {

            int currentId = places.get(i).getId();

            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId;
    }

    //endregion

    //region Public Methods

    public void addNewPlace(int width, int length, String landlord, double price, Place.Planet planet) {
        currentId++;

        Place place = new Place(currentId, width, length, landlord, price, planet);

        places.add(place);
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void deletePlaceById(int id) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        places.remove(index);
    }

    public void setNewPlacePriceById(int id, double price) throws Exception {
        int index = findIndexById(id);

        if (index == -1) {
            throw new Exception("Место с таким ID не найдено");
        }

        Place findPlace = places.get(index);

        if (price < 0 || price > 150) {
            throw new Exception("Некорректная цена");
        }

        findPlace.setPrice(price);
    }

    public void seedPlaces() {
        currentId = 0;
        places = new ArrayList<>();

        addNewPlace(500, 600, "Иван", 1.2, Place.Planet.earth);
        addNewPlace(1000, 2000, "Юрий", 5.2, Place.Planet.mercury);
        addNewPlace(800, 900, "Джек", 8.8, Place.Planet.moon);
    }

    public void saveToDataFile(String fileName) throws Exception {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(places);
        } catch (Exception e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public void loadFromDataFile(String fileName) throws Exception {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(fileName));

            places = (ArrayList<Place>) inputStream.readObject();

            currentId = getMaxId();
        } catch (Exception e) {
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
    //endregion
}