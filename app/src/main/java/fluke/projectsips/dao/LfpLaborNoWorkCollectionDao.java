package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 12/26/2016 AD.
 */

public class LfpLaborNoWorkCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<LfpLaborNoWorkDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<LfpLaborNoWorkDao> getData() {
        return data;
    }

    public void setData(List<LfpLaborNoWorkDao> data) {
        this.data = data;
    }

}
