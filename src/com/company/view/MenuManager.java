package com.company.view;

import com.company.data.Place;
import com.company.data.PlacesManager;
import com.company.utils.ConsoleHelper;

import java.util.ArrayList;

public class MenuManager {
    //region Fields

    private PlacesManager placesManager;

    //endregion

    //region Constructor

    public MenuManager(PlacesManager placesManager) {
        this.placesManager = placesManager;
    }

    //endregion

    //region Private Methods

    private void printPlaces() {
        ArrayList<Place> places = placesManager.getPlaces();

        if (places.size() == 0) {
            ConsoleHelper.printlnMessage("Список пуст");
            ConsoleHelper.printlnMessage("=====");
            return;
        }

        String tableHeader = String.format("%-5s%-10s%-10s%-15s%-14s%-10s", "ИД", "Длина(м)", "Ширина(м)", "Землевладелец", "Цена($ млн.)", "Планета");

        ConsoleHelper.printlnMessage(tableHeader);

        for (int i = 0; i < places.size(); i++) {

            Place currentPlace = places.get(i);

            String tableCurrentRow = String.format("%-5d%-10d%-10d%-15s%-14.2f%-10s", currentPlace.getId(), currentPlace.getLength(), currentPlace.getWidth(), currentPlace.getLandlord(), currentPlace.getPrice(), currentPlace.getPlanet().getValue());

            ConsoleHelper.printlnMessage(tableCurrentRow);
        }

        ConsoleHelper.printlnMessage("=====");
    }

    //endregion

    //region Public Methods

    public void execute() throws Exception {
        int action = -1;
        boolean isRun = true;

        while (isRun) {

            printPlaces();

            ConsoleHelper.printlnMessage("Меню:");
            ConsoleHelper.printlnMessage("1. Добавить новую запись об участке");
            ConsoleHelper.printlnMessage("2. Удалить участок по ИД");
            ConsoleHelper.printlnMessage("3. Загрузить участки из файла");
            ConsoleHelper.printlnMessage("4. Сохранить участки в файл");
            ConsoleHelper.printlnMessage("5. Изменить цену участка по ИД");
            ConsoleHelper.printlnMessage("0. Выход");

            action = ConsoleHelper.inputInt("Введите номер пункта меню: ", 0, 5);

            switch (action) {
                case 1: {
                    int length = ConsoleHelper.inputInt("Введите длину(м): ");
                    int width = ConsoleHelper.inputInt("Введите ширину(м): ");
                    String landlord = ConsoleHelper.inputString("Введите имя владельца участка: ");
                    double price = ConsoleHelper.inputDouble("Введите цену($ млн.): ");

                    int planetIndex = ConsoleHelper.inputInt("Введите индекс планеты(0-Земля, 1-Луна, 2-Марс, 3-Меркурий): ", 0, 4);
                    Place.Planet planet = Place.Planet.values()[planetIndex];

                    placesManager.addNewPlace(length, width, landlord, price, planet);
                }
                break;

                case 2: {
                    try {
                        int id = ConsoleHelper.inputInt("Введите ИД участка для удаления: ");
                        placesManager.deletePlaceById(id);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 3: {
                    try {
                        String fileName = ConsoleHelper.inputString("Введите имя файла для загрузки данных: ");
                        placesManager.loadFromDataFile(fileName);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 4: {
                    try {
                        String fileName = ConsoleHelper.inputString("Введите имя файла для сохранения данных: ");
                        placesManager.saveToDataFile(fileName);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;


                case 5: {
                    try {
                        int id = ConsoleHelper.inputInt("Введите ИД участка для изменения цены: ");
                        double price = ConsoleHelper.inputDouble("Введите цену($ млн.): ");

                        placesManager.setNewPlacePriceById(id, price);
                    } catch (Exception e) {
                        ConsoleHelper.printlnMessage(e.getMessage());
                    }
                }
                break;

                case 0: {
                    isRun = false;
                }
                break;
            }
        }
    }

    //endregion
}