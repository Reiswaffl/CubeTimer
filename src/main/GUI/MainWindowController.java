package main.GUI;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import javafx.scene.input.KeyEvent;
import main.Logic.Logic;
import main.Logic.Puzzles;
import main.Logic.ScrambleGenerator;
import main.dataTransfer.Solve;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

//TODO
// Tabelleninhalt centern (mit css)
public class MainWindowController {
    final String format = "MMM dd yyyy - HH:mm:ss";
    public GUI gui;
    private int state;
    private long startTime;
    @FXML
    Text time;
    @FXML
    Text scramble;
    @FXML
    ChoiceBox puzzleSelect;
    @FXML
    ChoiceBox specSelect;
    @FXML
    TableView<Averages> averages;
    // Methods
    @FXML
    private TableColumn<Averages, String> ao5;
    @FXML
    private TableColumn<Averages, String> ao12;
    @FXML
    private TableColumn<Averages, String> ao50;
    @FXML
    private TableColumn<Averages, String> ao100;

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * GUI gets prepared and the default-values are set
     */
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
        scramble.setText(ScrambleGenerator.generate(Puzzles.THREE));
    }

    /**
     * listebers get initialized
     */
    public void start() {
        gui.scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                if (state == 2) {
                    state = 3;
                    // save
                    SimpleDateFormat formatter = new SimpleDateFormat(format);
                    Date date = new Date();
                    Solve solve = new Solve(puzzleSelect.getValue().toString(),
                            specSelect.getValue().toString(), time.getText(), formatter.format(date), scramble.getText());
                    Logic.save(solve);
                    // generate new Scramble
                    scramble.setText(ScrambleGenerator.generate(Puzzles.THREE));
                    // update avg
                    updateAvg();
                } else if (state == 3) {
                    state = 1;
                }
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
        updateAvg();
    }

    /**
     * updates the averages on the GUI (resets the values)
     */
    public void updateAvg() {
        ao5.setCellValueFactory(new PropertyValueFactory<Averages, String>("ao5"));
        ao12.setCellValueFactory(new PropertyValueFactory<Averages, String>("ao12"));
        ao50.setCellValueFactory(new PropertyValueFactory<Averages, String>("ao50"));
        ao100.setCellValueFactory(new PropertyValueFactory<Averages, String>("ao100"));
        LinkedList<Averages> list = new LinkedList<>();
        Averages avg = new Averages(Logic.avg(puzzleSelect.getValue().toString(), specSelect.getValue().toString(), 5),
                Logic.avg(puzzleSelect.getValue().toString(), specSelect.getValue().toString(), 12),
                Logic.avg(puzzleSelect.getValue().toString(), specSelect.getValue().toString(), 50),
                Logic.avg(puzzleSelect.getValue().toString(), specSelect.getValue().toString(), 100));
        list.add(avg);
        averages.getItems().setAll(list);
    }
    /**
     * state = 1: waiting to start solve (inspection)
     * state = 2: solving
     * state = 3: pause
     */


}
