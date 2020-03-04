package main.GUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    private  Stage primaryStage;
    public Scene scene;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        mainWindow();
    }
    public void mainWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("MainWindow.fxml"));
            AnchorPane pane = loader.load();
            primaryStage.setMinHeight(100);
            primaryStage.setMinWidth(100);
            primaryStage.setTitle("Menü");

            MainWindowController guiCon = loader.getController();
            guiCon.setGui(this);
            scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
            guiCon.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}