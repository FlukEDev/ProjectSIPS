package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 11/15/2016 AD.
 */

public class LfpLaborIndustryDao {

    @SerializedName("industryName")
    @Expose
    private String industryName;
    @SerializedName("maleAmount")
    @Expose
    private Integer maleAmount;
    @SerializedName("femaleAmount")
    @Expose
    private Integer femaleAmount;

    /**
     *
     * @return
     * The industryName
     */
    public String getIndustryName() {
        return industryName;
    }

    /**
     *
     * @param industryName
     * The industryName
     */
    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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
