package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fluke on 11/5/2016 AD.
 */

public class LfpLaborCareerCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<LfpLaborCareerDao> data = new ArrayList<LfpLaborCareerDao>();

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
    public List<LfpLaborCareerDao> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<LfpLaborCareerDao> data) {
        this.data = data;
    }

}
