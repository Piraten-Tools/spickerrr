package de.piratentools.spickerrr2.model;

import android.content.SharedPreferences;

/**
 * Created by lostincoding on 26.05.16.
 */
public class Antrag {
    private final String id;
    private final String title;
    private final String topic;
    private final String kind;
    private final String owner;
    private final String infourl;
    private final String abstract_short; //abstract is forbidden
    private final String description;
    private final String motivation;
    private final String bookKey;
    private final String packageKey;

    public Antrag(String id, String title, String topic, String kind, String owner, String infourl, String abstract_short, String description, String motivation, String bookKey, String packageKey) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.kind = kind;
        this.owner = owner;
        this.infourl = infourl;
        this.abstract_short = abstract_short;
        this.description = description;
        this.motivation = motivation;
        this.bookKey = bookKey;
        this.packageKey = packageKey;
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

    public String getBookKey() {
        return bookKey;
    }

    public String getPackageKey() {
        return packageKey;
    }

    public String getMotivation() {
        return motivation;
    }

    public String getNoticePreference() {
        SharedPreferences votePreferences = DataHolder.getInstance().getNoticePreferences();
        return votePreferences.getString(this.getBookKey() + "_" + this.getPackageKey() + "_" + this.getId(), "");
    }

    public void setNoticePreference(String notice) {
        SharedPreferences noticePreferences = DataHolder.getInstance().getNoticePreferences();
        SharedPreferences.Editor editor = noticePreferences.edit();
        editor.putString(this.getBookKey() + "_" + this.getPackageKey() + "_" + this.getId(), notice);
        editor.apply();
    }

    public VotePreference getVotePreference() {
        SharedPreferences votePreferences = DataHolder.getInstance().getVotePreferences();
        String vote = votePreferences.getString(this.getBookKey() + "_" + this.getPackageKey() + "_" + this.getId(), "NOT_SET");
        return VotePreference.valueOf(vote);
    }

    public void setVotePreference(VotePreference votePreference) {
        SharedPreferences votePreferences = DataHolder.getInstance().getVotePreferences();
        SharedPreferences.Editor editor = votePreferences.edit();
        editor.putString(this.getBookKey() + "_" + this.getPackageKey() + "_" + this.getId(), votePreference.toString());
        editor.apply();
    }



    @Override
    public boolean equals(Object o) {
        if (o instanceof Antrag) {
            Antrag antrag = (Antrag) o;
            return antrag.id.equals(id);
        }
        return false;
    }
}
