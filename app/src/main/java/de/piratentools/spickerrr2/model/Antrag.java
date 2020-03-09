package de.piratentools.spickerrr2.model;

import android.content.SharedPreferences;

/**
 * Created by lostincoding on 26.05.16.
 */
public class Antrag {
    private String id;
    private String title;
    private String topic;
    private String kind;
    private String owner;
    private String infourl;
    private String abstract_short; //abstract is forbidden
    private String description;
    private String motivation;
    private String bookKey;
    private String packageKey;

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
        String vote = votePreferences.getString(this.getBookKey() + "_" + this.getPackageKey() + "_" + this.getId(), "");
        return vote;
    }

    public void setNoticePreference(String notice) {
        SharedPreferences noticePreferences = DataHolder.getInstance().getNoticePreferences();
        SharedPreferences.Editor editor = noticePreferences.edit();
        editor.putString(this.getBookKey() + "_" + this.getPackageKey() + "_" + this.getId(), notice);
        editor.commit();
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
        editor.commit();
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
