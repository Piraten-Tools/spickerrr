package de.lostincoding.spickerrr2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package implements Parcelable {

    @SerializedName("APIKey")
    @Expose
    private String aPIKey;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("SourceType")
    @Expose
    private String sourceType;
    @SerializedName("CsvSeperator")
    @Expose
    private String csvSeperator;
    @SerializedName("CsvQuote")
    @Expose
    private String csvQuote;
    @SerializedName("SourceZip")
    @Expose
    private Boolean sourceZip;
    @SerializedName("SourceVersion")
    @Expose
    private String sourceVersion;
    @SerializedName("SourceUrl")
    @Expose
    private String sourceUrl;
    @SerializedName("DataUrl")
    @Expose
    private String dataUrl;
    @SerializedName("InternalSourceFile")
    @Expose
    private String internalSourceFile;
    @SerializedName("InternalZipFile")
    @Expose
    private String internalZipFile;
    @SerializedName("InternalSourceFileLength")
    @Expose
    private String internalSourceFileLength;
    @SerializedName("InternalZipFileLength")
    @Expose
    private String internalZipFileLength;
    @SerializedName("Sorted")
    @Expose
    private Boolean sorted;
    @SerializedName("ColId")
    @Expose
    private String colId;
    @SerializedName("ColTitle")
    @Expose
    private String colTitle;
    @SerializedName("ColTopic")
    @Expose
    private String colTopic;
    @SerializedName("ColKind")
    @Expose
    private String colKind;
    @SerializedName("ColOwner")
    @Expose
    private String colOwner;
    @SerializedName("ColInfoUrl")
    @Expose
    private String colInfoUrl;
    @SerializedName("ColAbstract")
    @Expose
    private String colAbstract;
    @SerializedName("ColDescription")
    @Expose
    private String colDescription;
    @SerializedName("ColMotivation")
    @Expose
    private String colMotivation;
    @SerializedName("InfoUrlGeneric")
    @Expose
    private String infoUrlGeneric;
    @SerializedName("RegExColId")
    @Expose
    private String regExColId;
    @SerializedName("RegExTrimColId")
    @Expose
    private String regExTrimColId;
    @SerializedName("RegExTrimColTitle")
    @Expose
    private String regExTrimColTitle;
    @SerializedName("ConvertToHtml")
    @Expose
    private Boolean convertToHtml;
    @SerializedName("BookKey")
    @Expose
    private String bookKey;
    @SerializedName("InternalSourceUrl")
    @Expose
    private String internalSourceUrl;
    @SerializedName("InternalZipUrl")
    @Expose
    private String internalZipUrl;
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
     * @return The isActive
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive The IsActive
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return The sourceType
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType The SourceType
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * @return The csvSeperator
     */
    public String getCsvSeperator() {
        return csvSeperator;
    }

    /**
     * @param csvSeperator The CsvSeperator
     */
    public void setCsvSeperator(String csvSeperator) {
        this.csvSeperator = csvSeperator;
    }

    /**
     * @return The csvQuote
     */
    public String getCsvQuote() {
        return csvQuote;
    }

    /**
     * @param csvQuote The CsvQuote
     */
    public void setCsvQuote(String csvQuote) {
        this.csvQuote = csvQuote;
    }

    /**
     * @return The sourceZip
     */
    public Boolean getSourceZip() {
        return sourceZip;
    }

    /**
     * @param sourceZip The SourceZip
     */
    public void setSourceZip(Boolean sourceZip) {
        this.sourceZip = sourceZip;
    }

    /**
     * @return The sourceVersion
     */
    public String getSourceVersion() {
        return sourceVersion;
    }

    /**
     * @param sourceVersion The SourceVersion
     */
    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }

    /**
     * @return The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * @param sourceUrl The SourceUrl
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * @return The dataUrl
     */
    public String getDataUrl() {
        return dataUrl;
    }

    /**
     * @param dataUrl The DataUrl
     */
    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    /**
     * @return The internalSourceFile
     */
    public String getInternalSourceFile() {
        return internalSourceFile;
    }

    /**
     * @param internalSourceFile The InternalSourceFile
     */
    public void setInternalSourceFile(String internalSourceFile) {
        this.internalSourceFile = internalSourceFile;
    }

    /**
     * @return The internalZipFile
     */
    public String getInternalZipFile() {
        return internalZipFile;
    }

    /**
     * @param internalZipFile The InternalZipFile
     */
    public void setInternalZipFile(String internalZipFile) {
        this.internalZipFile = internalZipFile;
    }

    /**
     * @return The internalSourceFileLength
     */
    public String getInternalSourceFileLength() {
        return internalSourceFileLength;
    }

    /**
     * @param internalSourceFileLength The InternalSourceFileLength
     */
    public void setInternalSourceFileLength(String internalSourceFileLength) {
        this.internalSourceFileLength = internalSourceFileLength;
    }

    /**
     * @return The internalZipFileLength
     */
    public String getInternalZipFileLength() {
        return internalZipFileLength;
    }

    /**
     * @param internalZipFileLength The InternalZipFileLength
     */
    public void setInternalZipFileLength(String internalZipFileLength) {
        this.internalZipFileLength = internalZipFileLength;
    }

    /**
     * @return The sorted
     */
    public Boolean getSorted() {
        return sorted;
    }

    /**
     * @param sorted The Sorted
     */
    public void setSorted(Boolean sorted) {
        this.sorted = sorted;
    }

    /**
     * @return The colId
     */
    public String getColId() {
        return colId;
    }

    /**
     * @param colId The ColId
     */
    public void setColId(String colId) {
        this.colId = colId;
    }

    /**
     * @return The colTitle
     */
    public String getColTitle() {
        return colTitle;
    }

    /**
     * @param colTitle The ColTitle
     */
    public void setColTitle(String colTitle) {
        this.colTitle = colTitle;
    }

    /**
     * @return The colTopic
     */
    public String getColTopic() {
        return colTopic;
    }

    /**
     * @param colTopic The ColTopic
     */
    public void setColTopic(String colTopic) {
        this.colTopic = colTopic;
    }

    /**
     * @return The colKind
     */
    public String getColKind() {
        return colKind;
    }

    /**
     * @param colKind The ColKind
     */
    public void setColKind(String colKind) {
        this.colKind = colKind;
    }

    /**
     * @return The colOwner
     */
    public String getColOwner() {
        return colOwner;
    }

    /**
     * @param colOwner The ColOwner
     */
    public void setColOwner(String colOwner) {
        this.colOwner = colOwner;
    }

    /**
     * @return The colInfoUrl
     */
    public String getColInfoUrl() {
        return colInfoUrl;
    }

    /**
     * @param colInfoUrl The ColInfoUrl
     */
    public void setColInfoUrl(String colInfoUrl) {
        this.colInfoUrl = colInfoUrl;
    }

    /**
     * @return The colAbstract
     */
    public String getColAbstract() {
        return colAbstract;
    }

    /**
     * @param colAbstract The ColAbstract
     */
    public void setColAbstract(String colAbstract) {
        this.colAbstract = colAbstract;
    }

    /**
     * @return The colDescription
     */
    public String getColDescription() {
        return colDescription;
    }

    /**
     * @param colDescription The ColDescription
     */
    public void setColDescription(String colDescription) {
        this.colDescription = colDescription;
    }

    /**
     * @return The colMotivation
     */
    public String getColMotivation() {
        return colMotivation;
    }

    /**
     * @param colMotivation The ColMotivation
     */
    public void setColMotivation(String colMotivation) {
        this.colMotivation = colMotivation;
    }

    /**
     * @return The infoUrlGeneric
     */
    public String getInfoUrlGeneric() {
        return infoUrlGeneric;
    }

    /**
     * @param infoUrlGeneric The InfoUrlGeneric
     */
    public void setInfoUrlGeneric(String infoUrlGeneric) {
        this.infoUrlGeneric = infoUrlGeneric;
    }

    /**
     * @return The regExColId
     */
    public String getRegExColId() {
        return regExColId;
    }

    /**
     * @param regExColId The RegExColId
     */
    public void setRegExColId(String regExColId) {
        this.regExColId = regExColId;
    }

    /**
     * @return The regExTrimColId
     */
    public String getRegExTrimColId() {
        return regExTrimColId;
    }

    /**
     * @param regExTrimColId The RegExTrimColId
     */
    public void setRegExTrimColId(String regExTrimColId) {
        this.regExTrimColId = regExTrimColId;
    }

    /**
     * @return The regExTrimColTitle
     */
    public String getRegExTrimColTitle() {
        return regExTrimColTitle;
    }

    /**
     * @param regExTrimColTitle The RegExTrimColTitle
     */
    public void setRegExTrimColTitle(String regExTrimColTitle) {
        this.regExTrimColTitle = regExTrimColTitle;
    }

    /**
     * @return The convertToHtml
     */
    public Boolean getConvertToHtml() {
        return convertToHtml;
    }

    /**
     * @param convertToHtml The ConvertToHtml
     */
    public void setConvertToHtml(Boolean convertToHtml) {
        this.convertToHtml = convertToHtml;
    }

    /**
     * @return The bookKey
     */
    public String getBookKey() {
        return bookKey;
    }

    /**
     * @param bookKey The BookKey
     */
    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    /**
     * @return The internalSourceUrl
     */
    public String getInternalSourceUrl() {
        return internalSourceUrl;
    }

    /**
     * @param internalSourceUrl The InternalSourceUrl
     */
    public void setInternalSourceUrl(String internalSourceUrl) {
        this.internalSourceUrl = internalSourceUrl;
    }

    /**
     * @return The internalZipUrl
     */
    public String getInternalZipUrl() {
        return internalZipUrl;
    }

    /**
     * @param internalZipUrl The InternalZipUrl
     */
    public void setInternalZipUrl(String internalZipUrl) {
        this.internalZipUrl = internalZipUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aPIKey);
        dest.writeString(name);
        dest.writeString(key);
        dest.writeString(description);
        //boolean geht nicht WORKAROUND
        dest.writeByte((byte) (isActive ? 1 : 0));
        dest.writeString(sourceType);
        dest.writeString(csvSeperator);
        dest.writeString(csvQuote);
        dest.writeByte((byte) (sourceZip ? 1 : 0));
        dest.writeString(sourceVersion);
        dest.writeString(sourceUrl);
        dest.writeString(dataUrl);
        dest.writeString(internalSourceFile);
        dest.writeString(internalZipFile);
        dest.writeString(internalSourceFileLength);
        dest.writeString(internalZipFileLength);
        dest.writeByte((byte) (sorted ? 1 : 0));
        dest.writeString(colId);
        dest.writeString(colTitle);
        dest.writeString(colTopic);
        dest.writeString(colKind);
        dest.writeString(colOwner);
        dest.writeString(colInfoUrl);
        dest.writeString(colAbstract);
        dest.writeString(colDescription);
        dest.writeString(colMotivation);
        dest.writeString(infoUrlGeneric);
        dest.writeString(regExColId);
        dest.writeString(regExTrimColId);
        dest.writeString(regExTrimColTitle);
        dest.writeByte((byte) (convertToHtml ? 1 : 0));
        dest.writeString(bookKey);
        dest.writeString(internalSourceUrl);
        dest.writeString(internalZipUrl);
        dest.writeString(url);
        dest.writeString(urlEdit);
        dest.writeString(id);
        dest.writeString(createdAt);
        dest.writeString(createdBy);
        dest.writeByte((byte) (isMyOwn ? 1 : 0));
        dest.writeLong(timestamp);
        dest.writeLong(unixTimestamp);
        dest.writeInt(idNumber);
    }

    public Package(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        aPIKey = in.readString();
        name = in.readString();
        key = in.readString();
        description = in.readString();
        isActive = in.readByte() != 0;
        sourceType = in.readString();
        csvSeperator = in.readString();
        csvQuote = in.readString();
        sourceZip = in.readByte() != 0;
        sourceVersion = in.readString();
        sourceUrl = in.readString();
        dataUrl = in.readString();
        internalSourceFile = in.readString();
        internalZipFile = in.readString();
        internalSourceFileLength = in.readString();
        internalZipFileLength = in.readString();
        sorted = in.readByte() != 0;
        colId = in.readString();
        colTitle = in.readString();
        colTopic = in.readString();
        colKind = in.readString();
        colOwner = in.readString();
        colInfoUrl = in.readString();
        colAbstract = in.readString();
        colDescription = in.readString();
        colMotivation = in.readString();
        infoUrlGeneric = in.readString();
        regExColId = in.readString();
        regExTrimColId = in.readString();
        regExTrimColTitle = in.readString();
        convertToHtml = in.readByte() != 0;
        bookKey = in.readString();
        internalSourceUrl = in.readString();
        internalZipUrl = in.readString();
        url = in.readString();
        urlEdit = in.readString();
        id = in.readString();
        createdAt = in.readString();
        createdBy = in.readString();
        isMyOwn = in.readByte() != 0;
        timestamp = in.readLong();
        unixTimestamp = in.readLong();
        idNumber = in.readInt();

    }

}


