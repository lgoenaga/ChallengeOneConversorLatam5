package com.alura.challenger.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRate {

    public double getExchangeRate(String originCurrency, String destinationCurrency) {
        double exchangeRate = 0.0;

        try {
            // Construir la URL de la API
            String urlStr = "https://api.exchangerate-api.com/v4/latest/" + originCurrency;

            // Realizar la solicitud HTTP GET a la API
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();

            // Parsear la respuesta JSON y obtener la tasa de cambio
            if (response != null) {
                response = response.substring(response.indexOf(destinationCurrency) + 5);
                response = response.substring(0, response.indexOf(","));
                exchangeRate = Double.parseDouble(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchangeRate;
    }

}
