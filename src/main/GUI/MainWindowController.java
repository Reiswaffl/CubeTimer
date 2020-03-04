package main.GUI;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import javafx.scene.input.KeyEvent;

public class MainWindowController  {

    public GUI gui;
    private boolean running;
    private boolean reset;
    private boolean wait;
    @FXML Text time;

    // Methods
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @FXML
    public void initialize(){
        reset = true;
        running = false;
        wait = false;
        AnimationTimer animation = new AnimationTimer() {
            long startTime = System.currentTimeMillis();
            @Override
            public void handle(long l) {
                if(reset){
                    time.setText("0.00");
                    reset = false;
                }
                if(running){
                    long cur = System.currentTimeMillis()- startTime;
                    long sek = cur/1000;
                    long mil = cur%1000 / 100;
                    time.setText(sek + ":" + mil);
                }
            }
        };
        animation.start();
    }

    public void start(){
        gui.scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode().equals(KeyCode.SPACE)) {
                if(running) {
                    running = false;
                    wait = true;
                }
                else
                    reset = true;
            }

        });
        gui.scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode().equals(KeyCode.SPACE)) {
                if(!wait)
                    running = true;
                wait = false;
            }
        });
    }




}
