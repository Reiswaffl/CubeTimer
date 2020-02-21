package main.GUI;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController  {

    public GUI gui;
    @FXML  Label solvingTime;
    // Methods
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @FXML
    public void initialize(){
    }
}
