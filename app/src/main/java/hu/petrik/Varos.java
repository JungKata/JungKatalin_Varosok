package hu.petrik;
import android.annotation.SuppressLint;

public class Varos {
    private int id;

    private String varos;

    private String orszag;

    private int lakossag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public int getLakossag() {
        return lakossag;
    }

    public void setLakossag(int lakossag) {
        this.lakossag = lakossag;
    }

    public Varos(int id, String varos, String orszag, int lakossag) {
        this.id = id;
        this.varos = varos;
        this.orszag = orszag;
        this.lakossag = lakossag;


    }
}
