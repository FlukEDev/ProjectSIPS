package fluke.projectsips.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PopulationCollectionDao {

    @SerializedName("success")      private boolean success;
    @SerializedName("data")         private List<PopulationDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PopulationDao> getData() {
        return data;
    }

    public void setData(List<PopulationDao> data) {
        this.data = data;
    }
}
