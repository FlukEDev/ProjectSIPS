package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 3/12/2017 AD.
 */

public class EcoMonthCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<EcoMonthDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<EcoMonthDao> getData() {
        return data;
    }

    public void setData(List<EcoMonthDao> data) {
        this.data = data;
    }

}
