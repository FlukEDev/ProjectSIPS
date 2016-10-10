package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 9/2/2016 AD.
 */

public class CpiDao {

    @SerializedName("cpiValue")
    @Expose
    private String cpiValue;
    @SerializedName("mountYear")
    @Expose
    private String mountYear;

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
     * @return The mountYear
     */
    public String getMountYear() {
        return mountYear;
    }

    /**
     * @param mountYear The mountYear
     */
    public void setMountYear(String mountYear) {
        this.mountYear = mountYear;
    }

}
