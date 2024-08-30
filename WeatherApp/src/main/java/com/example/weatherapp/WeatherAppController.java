package com.example.weatherapp;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class WeatherAppController {

    @FXML
    private TextField cityTextField;

    @FXML
    private Label cityLabel;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label weatherLabel;

    @FXML
    private ImageView weatherImageView;

    private WeatherService weatherService;

    public WeatherAppController() {
        this.weatherService = new WeatherService();
    }

    @FXML
    public void getWeatherButtonClicked() {
        String cityName = cityTextField.getText().trim();
        if (!cityName.isEmpty()) {
            try {
                JsonNode weatherData = weatherService.getWeather(cityName);
                if (weatherData != null) {
                    updateWeatherInfo(weatherData);
                } else {
                    clearWeatherInfo();
                    cityLabel.setText("Weather data is null or incomplete.");
                }
            } catch (IOException | InterruptedException e) {
                clearWeatherInfo();
                cityLabel.setText("Failed to retrieve weather data.");
                e.printStackTrace();
            }
        } else {
            clearWeatherInfo();
            cityLabel.setText("Please enter a city name.");
        }
    }

    private void updateWeatherInfo(JsonNode weatherData) {
        String cityName = weatherData.get("name").asText();
        double temperature = weatherData.get("main").get("temp").asDouble();
        String weatherDescription = weatherData.get("weather").get(0).get("description").asText();

        cityLabel.setText(cityName);
        temperatureLabel.setText(String.format("%.2f°C", temperature));
        weatherLabel.setText(weatherDescription);

        updateWeatherImage(weatherDescription);
    }

    private void clearWeatherInfo() {
        cityLabel.setText("");
        temperatureLabel.setText("");
        weatherLabel.setText("");
    }

    private void updateWeatherImage(String weatherDescription) {
        String lowerCaseDescription = weatherDescription.toLowerCase();
        System.out.println("Loading image for: " + lowerCaseDescription);

        if (lowerCaseDescription.contains("clear sky")) {
            try {
                weatherImageView.setImage(new Image("file:///C:/Users/MERT TUNÇ/Desktop/Projelerim/WeatherApp/src/main/resources/images/clear.png"));
            } catch (Exception e) {
                System.err.println("Failed to load image for clear sky: " + e.getMessage());
                weatherImageView.setImage(null);
            }
        } else if (lowerCaseDescription.contains("rain")) {
            try {
                weatherImageView.setImage(new Image("file:///C:/Users/MERT TUNÇ/Desktop/Projelerim/WeatherApp/src/main/resources/images/rain.png"));
            } catch (Exception e) {
                System.err.println("Failed to load image for rain: " + e.getMessage());
                weatherImageView.setImage(null);
            }
        } else if (lowerCaseDescription.contains("cloud")) {
            try {
                weatherImageView.setImage(new Image("file:///C:/Users/MERT TUNÇ/Desktop/Projelerim/WeatherApp/src/main/resources/images/cloudy.png"));
            } catch (Exception e) {
                System.err.println("Failed to load image for cloudy: " + e.getMessage());
                weatherImageView.setImage(null);
            }
        } else if (lowerCaseDescription.contains("snow")) {
            try {
                weatherImageView.setImage(new Image("file:///C:/Users/MERT TUNÇ/Desktop/Projelerim/WeatherApp/src/main/resources/images/snow.png"));
            } catch (Exception e) {
                System.err.println("Failed to load image for snow: " + e.getMessage());
                weatherImageView.setImage(null);
            }
        } else {
            weatherImageView.setImage(null);
        }
    }

}

