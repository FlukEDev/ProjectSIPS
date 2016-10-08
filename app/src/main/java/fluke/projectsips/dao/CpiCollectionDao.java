package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 9/2/2016 AD.
 */

public class CpiCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<CpiDao> data = new ArrayList<CpiDao>();

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
    public List<CpiDao> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<CpiDao> data) {
        this.data = data;
    }

}
