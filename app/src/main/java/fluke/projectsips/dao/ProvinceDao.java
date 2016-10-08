package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 9/3/2016 AD.
 */

public class ProvinceDao {

    @SerializedName("provinceID")
    @Expose
    private String provinceID;
    @SerializedName("provinceName")
    @Expose
    private String provinceName;

    /**
     * @return The provinceID
     */
    public String getProvinceID() {
        return provinceID;
    }

    /**
     * @param provinceID The provinceID
     */
    public void setProvinceID(String provinceID) {
        this.provinceID = provinceID;
    }

    /**
     * @return The provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName The provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


}
