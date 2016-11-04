package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 11/2/2016 AD.
 */

public class LfpLaborSexDao {

    @SerializedName("laborStatusDetail")
    @Expose
    private String laborStatusDetail;
    @SerializedName("maleAmount")
    @Expose
    private Integer maleAmount;
    @SerializedName("femaleAmount")
    @Expose
    private Integer femaleAmount;

    /**
     *
     * @return
     * The laborStatusDetail
     */
    public String getLaborStatusDetail() {
        return laborStatusDetail;
    }

    /**
     *
     * @param laborStatusDetail
     * The laborStatusDetail
     */
    public void setLaborStatusDetail(String laborStatusDetail) {
        this.laborStatusDetail = laborStatusDetail;
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
