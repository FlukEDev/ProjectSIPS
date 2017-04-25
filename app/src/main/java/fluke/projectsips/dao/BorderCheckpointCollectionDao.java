package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 4/26/2017 AD.
 */

public class BorderCheckpointCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<BorderCheckpointDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<BorderCheckpointDao> getData() {
        return data;
    }

    public void setData(List<BorderCheckpointDao> data) {
        this.data = data;
    }

}
