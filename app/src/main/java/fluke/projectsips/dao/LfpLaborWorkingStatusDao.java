package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 12/25/2016 AD.
 */

public class LfpLaborWorkingStatusDao {

    @SerializedName("workingStatusName")
    @Expose
    private String workingStatusName;
    @SerializedName("maleAmount")
    @Expose
    private Integer maleAmount;
    @SerializedName("femaleAmount")
    @Expose
    private Integer femaleAmount;

    public String getWorkingStatusName() {
        return workingStatusName;
    }

    public void setWorkingStatusName(String workingStatusName) {
        this.workingStatusName = workingStatusName;
    }

    public Integer getMaleAmount() {
        return maleAmount;
    }

    public void setMaleAmount(Integer maleAmount) {
        this.maleAmount = maleAmount;
    }

    public Integer getFemaleAmount() {
        return femaleAmount;
    }

    public void setFemaleAmount(Integer femaleAmount) {
        this.femaleAmount = femaleAmount;
    }

}
