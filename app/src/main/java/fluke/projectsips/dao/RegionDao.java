package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 10/11/2016 AD.
 */

public class RegionDao {

    @SerializedName("regionName")
    @Expose
    private String regionName;

    /**
     *
     * @return
     * The regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     *
     * @param regionName
     * The regionName
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}
