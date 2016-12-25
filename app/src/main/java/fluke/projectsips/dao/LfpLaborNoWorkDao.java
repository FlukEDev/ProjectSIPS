package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 12/26/2016 AD.
 */

public class LfpLaborNoWorkDao {

    @SerializedName("laborNoworkMaleAmount")
    @Expose
    private Integer laborNoworkMaleAmount;
    @SerializedName("laborNoworkFemaleAmount")
    @Expose
    private Integer laborNoworkFemaleAmount;
    @SerializedName("laborWorkMaleAmount")
    @Expose
    private Integer laborWorkMaleAmount;
    @SerializedName("laborWorkFemaleAmount")
    @Expose
    private Integer laborWorkFemaleAmount;

    public Integer getLaborNoworkMaleAmount() {
        return laborNoworkMaleAmount;
    }

    public void setLaborNoworkMaleAmount(Integer laborNoworkMaleAmount) {
        this.laborNoworkMaleAmount = laborNoworkMaleAmount;
    }

    public Integer getLaborNoworkFemaleAmount() {
        return laborNoworkFemaleAmount;
    }

    public void setLaborNoworkFemaleAmount(Integer laborNoworkFemaleAmount) {
        this.laborNoworkFemaleAmount = laborNoworkFemaleAmount;
    }

    public Integer getLaborWorkMaleAmount() {
        return laborWorkMaleAmount;
    }

    public void setLaborWorkMaleAmount(Integer laborWorkMaleAmount) {
        this.laborWorkMaleAmount = laborWorkMaleAmount;
    }

    public Integer getLaborWorkFemaleAmount() {
        return laborWorkFemaleAmount;
    }

    public void setLaborWorkFemaleAmount(Integer laborWorkFemaleAmount) {
        this.laborWorkFemaleAmount = laborWorkFemaleAmount;
    }


}
