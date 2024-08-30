package com.example.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherAppGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("weather-app.fxml"));
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(new Scene(root, 450, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}