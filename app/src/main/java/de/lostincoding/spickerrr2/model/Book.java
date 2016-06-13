package de.lostincoding.spickerrr2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("EventFrom")
    @Expose
    private String eventFrom;
    @SerializedName("EventTo")
    @Expose
    private String eventTo;
    @SerializedName("EventDate")
    @Expose
    private String eventDate;
    @SerializedName("EventTimeStamp")
    @Expose
    private Integer eventTimeStamp;
    @SerializedName("IsPast")
    @Expose
    private Boolean isPast;
    @SerializedName("IsRunningOrOver")
    @Expose
    private Boolean isRunningOrOver;
    @SerializedName("EventLocation")
    @Expose
    private String eventLocation;
    @SerializedName("EventUrl")
    @Expose
    private String eventUrl;
    @SerializedName("PackageCountActive")
    @Expose
    private Integer packageCountActive;
    @SerializedName("PackageCountAll")
    @Expose
    private Integer packageCountAll;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("UrlEdit")
    @Expose
    private String urlEdit;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("CreatedAt")
    @Expose
    private String createdAt;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("IsMyOwn")
    @Expose
    private Boolean isMyOwn;
    @SerializedName("Timestamp")
    @Expose
    private Long timestamp;
    @SerializedName("UnixTimestamp")
    @Expose
    private Long unixTimestamp;
    @SerializedName("IdNumber")
    @Expose
    private Integer idNumber;

    /**
     * @return The aPIKey
     */
    public String getAPIKey() {
        return aPIKey;
    }

    /**
     * @param aPIKey The APIKey
     */
    public void setAPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    /**
     * @return The key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key The Key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The eventFrom
     */
    public String getEventFrom() {
        return eventFrom;
    }

    /**
     * @param eventFrom The EventFrom
     */
    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    /**
     * @return The eventTo
     */
    public String getEventTo() {
        return eventTo;
    }

    /**
     * @param eventTo The EventTo
     */
    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    /**
     * @return The eventDate
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate The EventDate
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return The eventTimeStamp
     */
    public Integer getEventTimeStamp() {
        return eventTimeStamp;
    }

    /**
     * @param eventTimeStamp The EventTimeStamp
     */
    public void setEventTimeStamp(Integer eventTimeStamp) {
        this.eventTimeStamp = eventTimeStamp;
    }

    /**
     * @return The isPast
     */
    public Boolean getIsPast() {
        return isPast;
    }

    /**
     * @param isPast The IsPast
     */
    public void setIsPast(Boolean isPast) {
        this.isPast = isPast;
    }

    /**
     * @return The isRunningOrOver
     */
    public Boolean getIsRunningOrOver() {
        return isRunningOrOver;
    }

    /**
     * @param isRunningOrOver The IsRunningOrOver
     */
    public void setIsRunningOrOver(Boolean isRunningOrOver) {
        this.isRunningOrOver = isRunningOrOver;
    }

    /**
     * @return The eventLocation
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * @param eventLocation The EventLocation
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * @return The eventUrl
     */
    public String getEventUrl() {
        return eventUrl;
    }

    /**
     * @param eventUrl The EventUrl
     */
    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    /**
     * @return The packageCountActive
     */
    public Integer getPackageCountActive() {
        return packageCountActive;
    }

    /**
     * @param packageCountActive The PackageCountActive
     */
    public void setPackageCountActive(Integer packageCountActive) {
        this.packageCountActive = packageCountActive;
    }

    /**
     * @return The packageCountAll
     */
    public Integer getPackageCountAll() {
        return packageCountAll;
    }

    /**
     * @param packageCountAll The PackageCountAll
     */
    public void setPackageCountAll(Integer packageCountAll) {
        this.packageCountAll = packageCountAll;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The Url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The urlEdit
     */
    public String getUrlEdit() {
        return urlEdit;
    }

    /**
     * @param urlEdit The UrlEdit
     */
    public void setUrlEdit(String urlEdit) {
        this.urlEdit = urlEdit;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The CreatedAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy The CreatedBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return The isMyOwn
     */
    public Boolean getIsMyOwn() {
        return isMyOwn;
    }

    /**
     * @param isMyOwn The IsMyOwn
     */
    public void setIsMyOwn(Boolean isMyOwn) {
        this.isMyOwn = isMyOwn;
    }

    /**
     * @return The timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The Timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return The unixTimestamp
     */
    public Long getUnixTimestamp() {
        return unixTimestamp;
    }

    /**
     * @param unixTimestamp The UnixTimestamp
     */
    public void setUnixTimestamp(Long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    /**
     * @return The idNumber
     */
    public Integer getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber The IdNumber
     */
    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }


    public String toString() {
        StringBuilder sb= new StringBuilder();
        sb.append("Key: ").append(key).append("\n");
        sb.append("Name: ").append(name).append(key).append("\n");
        sb.append("Description: ").append(description).append(key).append("\n");
        return sb.toString();
    }
}
