package main.GUI;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
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
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//TODO
// Tabelleninhalt centern (mit css)
public class MainWindowController {
    final String format = "MMM dd yyyy - HH:mm:ss";
    public GUI gui;
    private int state;
    private long startTime;
    private List<Solve> times;
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
    @FXML
    private ListView<String> timeList0;
    @FXML
    private ListView<String> timeList1;
    @FXML
    private ListView<String> timeList2;

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
                    System.out.println(time.getText());
                    String[] arr = time.getText().replace(":", ".").split("\\.");
                    String s = "";
                    if (arr.length == 3)
                        s = arr[0] + ":" + arr[1] + "." + arr[2];
                    else
                        s = arr[0] + "." + arr[1];
                    Solve solve = new Solve(puzzleSelect.getValue().toString(),
                            specSelect.getValue().toString(), s, formatter.format(date), scramble.getText());
                    Logic.save(solve);
                    // generate new Scramble
                    scramble.setText(ScrambleGenerator.generate(Puzzles.THREE));
                    // update avg
                    updateAvg();
                    // update times
                    updateTimes();
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
        updateTimes();
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
     * updates the values of the latest solves (in table/list)
     */
    public void updateTimes() {
        times = Logic.listTimes(puzzleSelect.getValue().toString(), specSelect.getValue().toString(), 12);
        if(times != null) {
            Collections.reverse(times);
            List<String> left = new LinkedList<>();
            List<String> mid = new LinkedList<>();
            List<String> right = new LinkedList<>();
            for (int i = 0; i <= 12; i += 3) {
                try {
                    left.add(times.get(i).getSolveTime());
                    mid.add(times.get(1 + i).getSolveTime());
                    right.add(times.get(2 + i).getSolveTime());
                } catch (Exception e) {
                }

            }
            timeList0.getItems().setAll(left);
            timeList1.getItems().setAll(mid);
            timeList2.getItems().setAll(right);
        }

    }
    /**
     * state = 1: waiting to start solve (inspection)
     * state = 2: solving
     * state = 3: pause
     */


}
