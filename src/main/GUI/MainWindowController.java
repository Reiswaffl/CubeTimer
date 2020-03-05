package main.GUI;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import javafx.scene.input.KeyEvent;

public class MainWindowController {

    public GUI gui;
    private int state;
    private long startTime;
    @FXML
    Text time;

    // Methods
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @FXML
    public void initialize() {
        state = 1;
        startTime = 0;
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (state == 1) {
                    time.setText("0.00");
                }
                if (state == 2) {
                    long cur = System.currentTimeMillis() - startTime;
                    long min = cur / 60000;
                    long sek = cur % 60000 / 1000;
                    long mil = cur % 1000 / 10;
                    if (min == 0)
                        time.setText(sek + ":" + mil);
                    else if (mil == 0)
                        time.setText(min + ":" + sek + ":" + mil);
                }
            }
        };
        animation.start();
    }

    public void start() {
        gui.scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                if (state == 2)
                    state = 3;
                else if (state == 3)
                    state = 1;
            }

        });
        gui.scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                if (state == 1) {
                    state = 2;
                    startTime = System.currentTimeMillis();
                }

            }
        });
    }


}
