package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 9/3/2016 AD.
 */

public class ProvinceCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<ProvinceDao> data = new ArrayList<ProvinceDao>();

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
    public List<ProvinceDao> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<ProvinceDao> data) {
        this.data = data;
    }

}
