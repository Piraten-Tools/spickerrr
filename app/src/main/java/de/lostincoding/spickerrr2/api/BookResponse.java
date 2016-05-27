package de.lostincoding.spickerrr2.api;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.lostincoding.spickerrr2.model.Book;

public class BookResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Book> data = new ArrayList<>();

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
    public List<Book> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Book> data) {
        this.data = data;
    }

}

