package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fluke on 4/26/2017 AD.
 */

public class BorderCheckpointTypeCollection {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<BorderCheckpointTypeDao> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<BorderCheckpointTypeDao> getData() {
        return data;
    }

    public void setData(List<BorderCheckpointTypeDao> data) {
        this.data = data;
    }

}
