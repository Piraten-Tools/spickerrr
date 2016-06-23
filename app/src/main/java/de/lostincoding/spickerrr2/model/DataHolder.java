package de.lostincoding.spickerrr2.model;

import java.util.ArrayList;

/**
 * Created by lostincoding on 23.06.16.
 */
public class DataHolder {
    private static DataHolder instance;

    private Book book;
    private Package aPackage;
    private ArrayList<Antrag> antragslist;

    private DataHolder() {
        setInstance(new DataHolder());
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

    public ArrayList<Antrag> getAntragslist() {
        return antragslist;
    }

    public void setAntragslist(ArrayList<Antrag> antragslist) {
        this.antragslist = antragslist;
    }
}
