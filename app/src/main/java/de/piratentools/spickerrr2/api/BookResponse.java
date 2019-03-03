package de.piratentools.spickerrr2.api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.piratentools.spickerrr2.model.JsonBook;

public class BookResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<JsonBook> data = new ArrayList<>();

    /**
     * @return The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * @return The data
     */
    public List<JsonBook> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<JsonBook> data) {
        this.data = data;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Successfull: ").append(success).append("\n");
        sb.append("Books: ").append(data.toString());

        return sb.toString();
    }
}

