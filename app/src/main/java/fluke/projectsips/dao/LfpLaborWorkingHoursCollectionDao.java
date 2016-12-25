package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 12/25/2016 AD.
 */

public class LfpLaborWorkingHoursCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<LfpLaborWorkingHoursDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<LfpLaborWorkingHoursDao> getData() {
        return data;
    }

    public void setData(List<LfpLaborWorkingHoursDao> data) {
        this.data = data;
    }


}
