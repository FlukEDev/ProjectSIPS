package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 10/25/2016 AD.
 */

public class GdpCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<GdpDao> data = new ArrayList<GdpDao>();

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
    public List<GdpDao> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<GdpDao> data) {
        this.data = data;
    }

}
