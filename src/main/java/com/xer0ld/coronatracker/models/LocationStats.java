package com.xer0ld.coronatracker.models;

public class LocationStats {

    private String state;
    private String country;
    private int latestTotalCases;
    private int numberOfNewCases;

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                ", numberOfNewCases=" + numberOfNewCases +
                '}';
    }

    public int getNumberOfNewCases() {
        return numberOfNewCases;
    }

    public void setNumberOfNewCases(int numberOfNewCases) {
        this.numberOfNewCases = numberOfNewCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }
}
