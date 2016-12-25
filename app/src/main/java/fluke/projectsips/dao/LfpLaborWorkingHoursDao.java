package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 12/25/2016 AD.
 */

public class LfpLaborWorkingHoursDao {

    @SerializedName("workingHrStart")
    @Expose
    private String workingHrStart;
    @SerializedName("workingHrEnd")
    @Expose
    private String workingHrEnd;
    @SerializedName("maleAmount")
    @Expose
    private Integer maleAmount;
    @SerializedName("femaleAmount")
    @Expose
    private Integer femaleAmount;

    public String getWorkingHrStart() {
        return workingHrStart;
    }

    public void setWorkingHrStart(String workingHrStart) {
        this.workingHrStart = workingHrStart;
    }

    public String getWorkingHrEnd() {
        return workingHrEnd;
    }

    public void setWorkingHrEnd(String workingHrEnd) {
        this.workingHrEnd = workingHrEnd;
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
