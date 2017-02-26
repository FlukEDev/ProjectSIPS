package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 1/27/2017 AD.
 */

public class FarmAreaCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<FarmAreaDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<FarmAreaDao> getData() {
        return data;
    }

    public void setData(List<FarmAreaDao> data) {
        this.data = data;
    }

}
