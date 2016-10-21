package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 10/21/2016 AD.
 */

public class GrpBuraphaCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GrpBuraphaDao> data = new ArrayList<GrpBuraphaDao>();

    /**
     *
     * @return
     * The success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The data
     */
    public List<GrpBuraphaDao> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<GrpBuraphaDao> data) {
        this.data = data;
    }

}
