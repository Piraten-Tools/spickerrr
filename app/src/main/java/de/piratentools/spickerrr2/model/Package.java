package de.piratentools.spickerrr2.model;

/**
 * Created by lostincoding on 02.06.17.
 */

public class Package {
    private String name;
    private String key;
    private String sourceType;
    private String csvSeperator;
    private String csvQuote;
    private String dataUrl;
    private String colId;
    private String colTitle;
    private String colTopic;
    private String colKind;
    private String colOwner;
    private String colInfoUrl;
    private String colAbstract;
    private String colDescription;
    private String colMotivation;
    private String bookKey;
    private String id;

    public Package(String name, String key, String sourceType, String csvSeperator, String csvQuote, String dataUrl, String colId, String colTitle, String colTopic, String colKind, String colOwner, String colInfoUrl, String colAbstract, String colDescription, String colMotivation, String bookKey, String id) {
        this.name = name;
        this.key = key;
        this.sourceType = sourceType;
        this.csvSeperator = csvSeperator;
        this.csvQuote = csvQuote;
        this.dataUrl = dataUrl;
        this.colId = colId;
        this.colTitle = colTitle;
        this.colTopic = colTopic;
        this.colKind = colKind;
        this.colOwner = colOwner;
        this.colInfoUrl = colInfoUrl;
        this.colAbstract = colAbstract;
        this.colDescription = colDescription;
        this.colMotivation = colMotivation;
        this.bookKey = bookKey;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getCsvSeperator() {
        return csvSeperator;
    }

    public void setCsvSeperator(String csvSeperator) {
        this.csvSeperator = csvSeperator;
    }

    public String getCsvQuote() {
        return csvQuote;
    }

    public void setCsvQuote(String csvQuote) {
        this.csvQuote = csvQuote;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getColTitle() {
        return colTitle;
    }

    public void setColTitle(String colTitle) {
        this.colTitle = colTitle;
    }

    public String getColTopic() {
        return colTopic;
    }

    public void setColTopic(String colTopic) {
        this.colTopic = colTopic;
    }

    public String getColKind() {
        return colKind;
    }

    public void setColKind(String colKind) {
        this.colKind = colKind;
    }

    public String getColOwner() {
        return colOwner;
    }

    public void setColOwner(String colOwner) {
        this.colOwner = colOwner;
    }

    public String getColInfoUrl() {
        return colInfoUrl;
    }

    public void setColInfoUrl(String colInfoUrl) {
        this.colInfoUrl = colInfoUrl;
    }

    public String getColAbstract() {
        return colAbstract;
    }

    public void setColAbstract(String colAbstract) {
        this.colAbstract = colAbstract;
    }

    public String getColDescription() {
        return colDescription;
    }

    public void setColDescription(String colDescription) {
        this.colDescription = colDescription;
    }

    public String getColMotivation() {
        return colMotivation;
    }

    public void setColMotivation(String colMotivation) {
        this.colMotivation = colMotivation;
    }

    public String getBookKey() {
        return bookKey;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Package p = (Package) o;

        return p.getKey() == getKey();
    }
}
