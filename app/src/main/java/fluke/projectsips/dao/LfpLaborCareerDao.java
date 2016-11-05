package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 11/5/2016 AD.
 */

public class LfpLaborCareerDao {

    @SerializedName("careerName")
    @Expose
    private String careerName;
    @SerializedName("maleAmount")
    @Expose
    private Integer maleAmount;
    @SerializedName("femaleAmount")
    @Expose
    private Integer femaleAmount;

    /**
     *
     * @return
     * The careerName
     */
    public String getCareerName() {
        return careerName;
    }

    /**
     *
     * @param careerName
     * The careerName
     */
    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    /**
     *
     * @return
     * The maleAmount
     */
    public Integer getMaleAmount() {
        return maleAmount;
    }

    /**
     *
     * @param maleAmount
     * The maleAmount
     */
    public void setMaleAmount(Integer maleAmount) {
        this.maleAmount = maleAmount;
    }

    /**
     *
     * @return
     * The femaleAmount
     */
    public Integer getFemaleAmount() {
        return femaleAmount;
    }

    /**
     *
     * @param femaleAmount
     * The femaleAmount
     */
    public void setFemaleAmount(Integer femaleAmount) {
        this.femaleAmount = femaleAmount;
    }


}
