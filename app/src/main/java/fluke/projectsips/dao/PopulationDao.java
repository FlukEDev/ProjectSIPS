package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopulationDao {

    @SerializedName("populationMale")
    @Expose
    private String populationMale;
    @SerializedName("populationFemale")
    @Expose
    private String populationFemale;

    /**
     * @return The populationMale
     */
    public String getPopulationMale() {
        return populationMale;
    }

    /**
     *
     * @param populationMale
     * The populationMale
     */
    public void setPopulationMale(String populationMale) {
        this.populationMale = populationMale;
    }

    /**
     *
     * @return
     * The populationFemale
     */
    public String getPopulationFemale() {
        return populationFemale;
    }

    /**
     *
     * @param populationFemale
     * The populationFemale
     */
    public void setPopulationFemale(String populationFemale) {
        this.populationFemale = populationFemale;
    }
}

