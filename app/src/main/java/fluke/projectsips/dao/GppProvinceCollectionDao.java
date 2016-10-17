package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 7/31/2016 AD.
 */

public class GppProvinceCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GppProvinceDao> data = new ArrayList<GppProvinceDao>();

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
    public List<GppProvinceDao> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<GppProvinceDao> data) {
        this.data = data;
    }


}
