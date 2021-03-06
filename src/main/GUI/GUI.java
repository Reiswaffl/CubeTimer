package main.GUI;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUI extends Application {
    public  Stage primaryStage;
    public Scene scene;
    public AnchorPane pane;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        mainWindow();
    }
    public void mainWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(GUI.class.getResource("MainWindow.fxml"));
            pane = loader.load();
            primaryStage.setMinHeight(100);
            primaryStage.setMinWidth(100);
            primaryStage.setTitle("Menü");

            MainWindowController guiCon = loader.getController();
            guiCon.setGui(this);
            scene = new Scene(pane);
            scene.getStylesheets().add(getClass().getResource("MainWindow.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(800.00);
            primaryStage.setMinWidth(1200.00);
            primaryStage.initStyle(StageStyle.UNDECORATED);
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