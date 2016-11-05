package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 11/5/2016 AD.
 */

public class LfpLaborEduDao {

    @SerializedName("eduDetail")
    @Expose
    private String eduDetail;
    @SerializedName("maleAmount")
    @Expose
    private Integer maleAmount;
    @SerializedName("femaleAmount")
    @Expose
    private Integer femaleAmount;

    /**
     *
     * @return
     * The eduDetail
     */
    public String getEduDetail() {
        return eduDetail;
    }

    /**
     *
     * @param eduDetail
     * The eduDetail
     */
    public void setEduDetail(String eduDetail) {
        this.eduDetail = eduDetail;
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
