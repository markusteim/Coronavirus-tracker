package com.xer0ld.coronatracker.services;

import com.xer0ld.coronatracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

//Makes the call to the URL and fetches the data

//Creating the service
@Service
public class VirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private List<LocationStats> allStats = new ArrayList<>();


    //When you construct this service, execute this method and prints out the body
    @PostConstruct
    @Scheduled(cron = "0 0 11 * * ?") //(cron = "0 0 11 * * ?") //cron string to specify how often to run it, right now every second, fires at 11pm every noon
    public void fetchData() throws IOException, InterruptedException {
        List<LocationStats> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());


        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            int latestDateIndex = record.size();

            LocationStats locationStat = new LocationStats();

            int latestTotalCases = Integer.parseInt(record.get(latestDateIndex - 1));
            int previousDayTotalCases = Integer.parseInt(record.get(latestDateIndex - 2));
            int numberOfNewCases = latestTotalCases - previousDayTotalCases;

            locationStat.setCountry(record.get("Country/Region"));
            locationStat.setState(record.get("Province/State"));
            locationStat.setLatestTotalCases(latestTotalCases);
            locationStat.setNumberOfNewCases(numberOfNewCases);

            newStats.add(locationStat);
        }
        this.allStats = newStats;

    }
}
