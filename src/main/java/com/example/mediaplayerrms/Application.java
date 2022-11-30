package com.example.mediaplayerrms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Sokolov Lab 2");
        stage.setScene(new Scene(root,1600,900));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}