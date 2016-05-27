package de.lostincoding.spickerrr2.api;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Package> data = new ArrayList<Package>();

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The data
     */
    public List<Package> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Package> data) {
        this.data = data;
    }

}