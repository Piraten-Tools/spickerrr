package de.piratentools.spickerrr2.model;

import android.content.Context;
import android.content.SharedPreferences;

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
    private SharedPreferences appPreferences;
    private SharedPreferences votePreferences;

    private DataHolder(Context context) {
        votePreferences = context.getSharedPreferences("votes", Context.MODE_PRIVATE);
    }

    public static DataHolder createInstance(Context context) {
        instance = new DataHolder(context.getApplicationContext());
        return instance;
    }

    public static DataHolder getInstance() {
        if (instance != null) {
            return instance;
        }
        throw new IllegalStateException("You have to use createInstance(Context) at least once before using this method.");
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
        this.antragslist = antragslist;
    }

    public SharedPreferences getAppPreferences() {
        return appPreferences;
    }

    public void setAppPreferences(SharedPreferences appPreferences) {
        this.appPreferences = appPreferences;
    }

    public SharedPreferences getVotePreferences() {
        return votePreferences;
    }

    public void setVotePreferences(SharedPreferences votePreferences) {
        this.votePreferences = votePreferences;
    }
}
