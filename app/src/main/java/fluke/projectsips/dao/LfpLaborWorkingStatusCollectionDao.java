package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 12/25/2016 AD.
 */

public class LfpLaborWorkingStatusCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<LfpLaborWorkingStatusDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<LfpLaborWorkingStatusDao> getData() {
        return data;
    }

    public void setData(List<LfpLaborWorkingStatusDao> data) {
        this.data = data;
    }


}
