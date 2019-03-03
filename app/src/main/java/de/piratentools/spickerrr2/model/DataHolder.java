package de.piratentools.spickerrr2.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lostincoding on 23.06.16.
 */
public class DataHolder {
    private static DataHolder instance;

    private Book book;
    private Package aPackage;
    private List<Antrag> antragslist;
    private HashMap<String, ArrayList<Antrag>> mapOfLists;

    private DataHolder() {

    }

    public static DataHolder getInstance() {
        if (instance == null) {
            instance = new DataHolder();
        }
        return instance;
    }

    public HashMap<String, ArrayList<Antrag>> getMapOfLists() {
        return mapOfLists;
    }

    public void setMapOfLists(HashMap<String, ArrayList<Antrag>> mapOfLists) {
        this.mapOfLists = mapOfLists;
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
