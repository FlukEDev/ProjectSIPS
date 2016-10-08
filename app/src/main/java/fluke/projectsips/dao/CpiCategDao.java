package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 6/30/2016 AD.
 */

public class CpiCategDao {

    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("cpiValue")
    @Expose
    private String cpiValue;
    @SerializedName("cpiCategName")
    @Expose
    private String cpiCategName;

    /**
     * @return The weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight The weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return The cpiValue
     */
    public String getCpiValue() {
        return cpiValue;
    }

    /**
     * @param cpiValue The cpiValue
     */
    public void setCpiValue(String cpiValue) {
        this.cpiValue = cpiValue;
    }

    /**
     * @return The cpiCategName
     */
    public String getCpiCategName() {
        return cpiCategName;
    }

    /**
     * @param cpiCategName The cpiCategName
     */
    public void setCpiCategName(String cpiCategName) {
        this.cpiCategName = cpiCategName;
    }

}
