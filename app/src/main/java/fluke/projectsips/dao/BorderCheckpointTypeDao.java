package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 4/26/2017 AD.
 */

public class BorderCheckpointTypeDao {

    @SerializedName("borderTypeID")
    @Expose
    private String borderTypeID;
    @SerializedName("borderTypeName")
    @Expose
    private String borderTypeName;

    public String getBorderTypeID() {
        return borderTypeID;
    }

    public void setBorderTypeID(String borderTypeID) {
        this.borderTypeID = borderTypeID;
    }

    public String getBorderTypeName() {
        return borderTypeName;
    }

    public void setBorderTypeName(String borderTypeName) {
        this.borderTypeName = borderTypeName;
    }

}
