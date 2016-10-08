package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 7/31/2016 AD.
 */

public class GppSakaeoCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GppSakaeoDao> data = new ArrayList<GppSakaeoDao>();

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
    public List<GppSakaeoDao> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<GppSakaeoDao> data) {
        this.data = data;
    }


}
