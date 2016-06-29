package de.lostincoding.spickerrr2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lostincoding on 23.06.16.
 */
public class DataHolder {
    private static DataHolder instance;

    private Book book;
    private Package aPackage;
    private ArrayList<Antrag> antragslist;

    private DataHolder() {

    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }


    public static void setInstance(DataHolder instance) {
        DataHolder.instance = instance;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public List<Antrag> getAntragslist() {
        return antragslist;
    }

    public void setAntragslist(List<Antrag> antragslist) {
        this.antragslist = (ArrayList<Antrag>) antragslist;
    }
}
