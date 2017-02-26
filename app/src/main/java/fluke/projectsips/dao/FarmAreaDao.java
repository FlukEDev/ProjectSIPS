package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 1/27/2017 AD.
 */

public class FarmAreaDao {

    @SerializedName("areaName")
    @Expose
    private String areaName;
    @SerializedName("areaDetail")
    @Expose
    private String areaDetail;
    @SerializedName("areaLatitude")
    @Expose
    private String areaLatitude;
    @SerializedName("areaLongitude")
    @Expose
    private String areaLongitude;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaDetail() {
        return areaDetail;
    }

    public void setAreaDetail(String areaDetail) {
        this.areaDetail = areaDetail;
    }

    public String getAreaLatitude() {
        return areaLatitude;
    }

    public void setAreaLatitude(String areaLatitude) {
        this.areaLatitude = areaLatitude;
    }

    public String getAreaLongitude() {
        return areaLongitude;
    }

    public void setAreaLongitude(String areaLongitude) {
        this.areaLongitude = areaLongitude;
    }


}
