
package com.example.admin.w3d1gsonexample.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("results")
    @Expose
    private List<Result_> results = new ArrayList<Result_>();
    @SerializedName("info")
    @Expose
    private Info info;

    /**
     * 
     * @return
     *     The results
     */
    public List<Result_> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result_> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The info
     */
    public Info getInfo() {
        return info;
    }

    /**
     * 
     * @param info
     *     The info
     */
    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Result{" +
                "info=" + info +
                ", results=" + results +
                '}';
    }
}
