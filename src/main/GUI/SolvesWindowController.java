package main.GUI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Logic.Logic;
import main.dataTransfer.Solve;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SolvesWindowController {
    MainWindowController mainWindowController;
    private String puzzle;
    private String spec;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Button xButton;
    @FXML
    private ListView<String> solves;
    @FXML public void close(){
        Stage stage = (Stage) xButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public void initData(String puzzle, String spec, MainWindowController controller){
        this.puzzle = puzzle;
        this.spec = spec;
        this.mainWindowController = controller;
    }
    public void start() {
        List<Solve> list =Logic.listTimes(puzzle,spec,-1);
        System.out.println(list.size());
        List<String> show = new LinkedList<>();
        if(list != null) {
            Collections.reverse(list);
            for (Solve solve : list) {
                show.add(solve.getSolveTime() + " : " + solve.getDate());
            }
            solves.getItems().setAll(show);
        }

        mainWindowController.root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        //move around here
        mainWindowController.root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainWindowController.stage.setX(event.getScreenX() - xOffset);
                mainWindowController.stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}
