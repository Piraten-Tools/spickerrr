package de.lostincoding.spickerrr2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lostincoding on 26.05.16.
 */
public class Antrag implements Parcelable {
    private String id;
    private String title;
    private String topic;
    private String kind;
    private String owner;
    private String infourl;
    private String abstract_short; //abstract is forbidden
    private String description;
    private String motivation;

    public Antrag(String id, String title, String topic, String kind, String owner, String infourl, String abstract_short, String description, String motivation) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.kind = kind;
        this.owner = owner;
        this.infourl = infourl;
        this.abstract_short = abstract_short;
        this.description = description;
        this.motivation = motivation;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public String getKind() {
        return kind;
    }

    public String getOwner() {
        return owner;
    }

    public String getInfourl() {
        return infourl;
    }

    public String getAbstract_short() {
        return abstract_short;
    }

    public String getDescription() {
        return description;
    }

    public String getMotivation() {
        return motivation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(topic);
        dest.writeString(kind);
        dest.writeString(owner);
        dest.writeString(infourl);
        dest.writeString(abstract_short);
        dest.writeString(description);
        dest.writeString(motivation);
    }

    public Antrag(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        id = in.readString();
        title = in.readString();
        topic = in.readString();
        kind = in.readString();
        owner = in.readString();
        infourl = in.readString();
        abstract_short = in.readString();
        description = in.readString();
        motivation = in.readString();

    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Antrag createFromParcel(Parcel in) {
                    return new Antrag(in);
                }

                public Antrag[] newArray(int size) {
                    return new Antrag[size];
                }
            };
}
