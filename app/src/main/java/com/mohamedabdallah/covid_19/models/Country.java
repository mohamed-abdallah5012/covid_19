package com.mohamedabdallah.covid_19.models;

public class Country {


    private String country;
    private String cases;
    private String deaths;
    private String todayCases;
    private String todayDeaths;
    private String critical;
    private String recovered;
    private String active;
    private String casesPerOneMillion;
    private String deathsPerOneMillion;

    private CountryInfo countryInfo;

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo countryInfo) {
        this.countryInfo = countryInfo;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCasesPerOneMillion(String casesPerOneMillion) {
        this.casesPerOneMillion = casesPerOneMillion;
    }

    public void setDeathsPerOneMillion(String deathsPerOneMillion) {
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getCritical() {
        return critical;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public String getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

}
