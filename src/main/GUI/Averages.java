package main.GUI;

import javafx.beans.property.SimpleStringProperty;
public class Averages {
    private SimpleStringProperty ao5;
    private SimpleStringProperty ao12;
    private SimpleStringProperty ao50;
    private SimpleStringProperty ao100;

    public Averages(String ao5, String ao12, String ao50, String ao100){
        this.ao5 = new SimpleStringProperty();
        this.ao12 = new SimpleStringProperty();
        this.ao50 = new SimpleStringProperty();
        this.ao100 = new SimpleStringProperty();
        setao5(ao5);
        setao12(ao12);
        setao50(ao50);
        setao100(ao100);
    }

    public String getAo5() {
        return ao5.get().toString();
    }

    public String getAo12() {
        return ao12.get().toString();
    }

    public String getAo50() {
        return ao50.get().toString();
    }

    public String getAo100() {
        return ao100.get().toString();
    }

    public void setao12(String s ){
        ao12.setValue(s);
    }

    public void setao50(String s ){
        ao50.setValue(s);
    }

    public void setao5(String s ){
        ao5.setValue(s);
    }

    public void setao100(String s ){
        ao100.setValue(s);
    }
}

