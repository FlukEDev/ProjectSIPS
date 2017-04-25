package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 4/26/2017 AD.
 */

public class BorderCheckpointDao {

    @SerializedName("checkpointName")
    @Expose
    private String checkpointName;
    @SerializedName("checkpointLocation")
    @Expose
    private String checkpointLocation;
    @SerializedName("checkpointLatitude")
    @Expose
    private String checkpointLatitude;
    @SerializedName("checkpointLongitude")
    @Expose
    private String checkpointLongitude;

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getCheckpointLocation() {
        return checkpointLocation;
    }

    public void setCheckpointLocation(String checkpointLocation) {
        this.checkpointLocation = checkpointLocation;
    }

    public String getCheckpointLatitude() {
        return checkpointLatitude;
    }

    public void setCheckpointLatitude(String checkpointLatitude) {
        this.checkpointLatitude = checkpointLatitude;
    }

    public String getCheckpointLongitude() {
        return checkpointLongitude;
    }

    public void setCheckpointLongitude(String checkpointLongitude) {
        this.checkpointLongitude = checkpointLongitude;
    }


}
