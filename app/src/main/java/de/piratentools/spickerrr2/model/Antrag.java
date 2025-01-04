package de.piratentools.spickerrr2.model;

import android.content.SharedPreferences;

/**
 * @param abstract_short abstract is forbidden
 */
public record Antrag(String id, String title, String topic, String kind, String owner,
                     String infourl, String abstract_short, String description, String motivation,
                     String bookKey, String packageKey) {

    public String getNoticePreference() {
        SharedPreferences votePreferences = DataHolder.getInstance().getNoticePreferences();
        return votePreferences.getString(this.bookKey() + "_" + this.packageKey() + "_" + this.id(), "");
    }

    public void setNoticePreference(String notice) {
        SharedPreferences noticePreferences = DataHolder.getInstance().getNoticePreferences();
        SharedPreferences.Editor editor = noticePreferences.edit();
        editor.putString(this.bookKey() + "_" + this.packageKey() + "_" + this.id(), notice);
        editor.apply();
    }

    public VotePreference getVotePreference() {
        SharedPreferences votePreferences = DataHolder.getInstance().getVotePreferences();
        String vote = votePreferences.getString(this.bookKey() + "_" + this.packageKey() + "_" + this.id(), "NOT_SET");
        return VotePreference.valueOf(vote);
    }

    public void setVotePreference(VotePreference votePreference) {
        SharedPreferences votePreferences = DataHolder.getInstance().getVotePreferences();
        SharedPreferences.Editor editor = votePreferences.edit();
        editor.putString(this.bookKey() + "_" + this.packageKey() + "_" + this.id(), votePreference.toString());
        editor.apply();
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Antrag antrag) {
            return antrag.id.equals(id);
        }
        return false;
    }
}
