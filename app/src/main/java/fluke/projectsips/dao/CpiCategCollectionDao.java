package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 6/30/2016 AD.
 */

public class CpiCategCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<CpiCategDao> data = new ArrayList<CpiCategDao>();

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
    public List<CpiCategDao> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<CpiCategDao> data) {
        this.data = data;
    }


}
