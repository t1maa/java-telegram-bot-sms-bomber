package info.securedev.telegrambot.models;

import info.securedev.telegrambot.flood_services.Flood;

import java.util.List;

public class Model {
    private List<Flood> floodServicesContainer;
    private List<String> arrayNumbersContainer;

    public void setToFloodServicesContainer(List<Flood> floodServicesContainer) {
        this.floodServicesContainer = floodServicesContainer;
    }

    public List<Flood> getFloodServices() {
        return floodServicesContainer;
    }

    public void setToArrayNumbersContainer(List<String> arrayNumbers) {
        this.arrayNumbersContainer = arrayNumbers;
    }

    public List<String> getArrayNumbers() {
        return arrayNumbersContainer;
    }
}
