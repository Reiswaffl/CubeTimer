package main.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController {

    public GUI gui;
    @FXML  Label solvingTime;
    // Methods
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @FXML
    public void initialize(){
        Refresher f = new Refresher(solvingTime);
        f.start();
    }

    static class Refresher extends Thread{
        Label l;
        public Refresher(Label l){
            this.l = l;
        }
        @Override
        public void run(){

        }
    }
}
