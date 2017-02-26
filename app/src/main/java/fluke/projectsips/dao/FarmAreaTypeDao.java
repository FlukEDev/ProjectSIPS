package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 1/27/2017 AD.
 */

public class FarmAreaTypeDao {

    @SerializedName("areaTypeID")
    @Expose
    private String areaTypeID;
    @SerializedName("areaTypeName")
    @Expose
    private String areaTypeName;

    public String getAreaTypeID() {
        return areaTypeID;
    }

    public void setAreaTypeID(String areaTypeID) {
        this.areaTypeID = areaTypeID;
    }

    public String getAreaTypeName() {
        return areaTypeName;
    }

    public void setAreaTypeName(String areaTypeName) {
        this.areaTypeName = areaTypeName;
    }

}
