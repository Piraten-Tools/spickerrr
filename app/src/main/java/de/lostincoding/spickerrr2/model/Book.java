package de.lostincoding.spickerrr2.model;

/**
 * Created by lostincoding on 26.05.16.
 */
public class Book {
    private String APIKey;
    private String Key;
    private String Name;
    private String Description;
    private String EventFrom;
    private String EventTo;
    private String EventDate;
    private long EventTimestamp;
    private boolean IsPast;
    private boolean IsRunningOrOver;
    private String EventLocation;
    private String EventUrl;
    private int PackageCountActive;
    private int PackageCountAll;
    private String Url;
    private String UrlEdit;
    private String Id;
    private String CreatedAt;
    private String CreatedBy;
    private boolean IsMyOwn;
    private long Timestamp;
    private long UnixUimestamp;
    private int IdNumber;

    public String getAPIKey() {
        return APIKey;
    }

    public void setAPIKey(String APIKey) {
        this.APIKey = APIKey;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEventFrom() {
        return EventFrom;
    }

    public void setEventFrom(String eventFrom) {
        EventFrom = eventFrom;
    }

    public String getEventTo() {
        return EventTo;
    }

    public void setEventTo(String eventTo) {
        EventTo = eventTo;
    }

    public String getEventDate() {
        return EventDate;
    }

    public void setEventDate(String eventDate) {
        EventDate = eventDate;
    }

    public long getEventTimestamp() {
        return EventTimestamp;
    }

    public void setEventTimestamp(long eventTimestamp) {
        EventTimestamp = eventTimestamp;
    }

    public boolean isPast() {
        return IsPast;
    }

    public void setPast(boolean past) {
        IsPast = past;
    }

    public boolean isRunningOrOver() {
        return IsRunningOrOver;
    }

    public void setRunningOrOver(boolean runningOrOver) {
        IsRunningOrOver = runningOrOver;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public void setEventLocation(String eventLocation) {
        EventLocation = eventLocation;
    }

    public String getEventUrl() {
        return EventUrl;
    }

    public void setEventUrl(String eventUrl) {
        EventUrl = eventUrl;
    }

    public int getPackageCountActive() {
        return PackageCountActive;
    }

    public void setPackageCountActive(int packageCountActive) {
        PackageCountActive = packageCountActive;
    }

    public int getPackageCountAll() {
        return PackageCountAll;
    }

    public void setPackageCountAll(int packageCountAll) {
        PackageCountAll = packageCountAll;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUrlEdit() {
        return UrlEdit;
    }

    public void setUrlEdit(String urlEdit) {
        UrlEdit = urlEdit;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public boolean isMyOwn() {
        return IsMyOwn;
    }

    public void setMyOwn(boolean myOwn) {
        IsMyOwn = myOwn;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }

    public long getUnixUimestamp() {
        return UnixUimestamp;
    }

    public void setUnixUimestamp(long unixUimestamp) {
        UnixUimestamp = unixUimestamp;
    }

    public int getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(int idNumber) {
        IdNumber = idNumber;
    }
}
