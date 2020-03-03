package main.GUI;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainWindowController  {

    public GUI gui;
    @FXML   Label solvingTime;
    // Methods
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @FXML
    public void initialize(){
        Task<Void> task = new Task<Void>() {
            private long time = 0;
            @Override
            protected Void call() throws Exception {
                while(true) {
                    Platform.runLater(() -> {
                        solvingTime.setText(String.valueOf(++time));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }


}
