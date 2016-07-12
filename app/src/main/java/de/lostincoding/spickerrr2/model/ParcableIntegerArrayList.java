package de.lostincoding.spickerrr2.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lostincoding on 12.07.16.
 */
public class ParcableIntegerArrayList implements Parcelable {
    private ArrayList<Integer> list;

    public ParcableIntegerArrayList() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(list);

    }

    public ParcableIntegerArrayList(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        in.readList(list, null);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public ParcableIntegerArrayList createFromParcel(Parcel in) {
                    return new ParcableIntegerArrayList(in);
                }

                public ParcableIntegerArrayList[] newArray(int size) {
                    return new ParcableIntegerArrayList[size];
                }
            };

    public ArrayList<Integer> getList() {
        return list;
    }


    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }
}
