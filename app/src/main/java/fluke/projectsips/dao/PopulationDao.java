package fluke.projectsips.dao;

import com.google.gson.annotations.SerializedName;

public class PopulationDao {

    @SerializedName("populationMale")           private int populationMale;
    @SerializedName("populationFemale")         private int populationFemale;

    public int getPopulationMale() {
        return populationMale;
    }

    public void setPopulationMale(int populationMale) {
        this.populationMale = populationMale;
    }

    public int getPopulationFemale() {
        return populationFemale;
    }

    public void setPopulationFemale(int populationFemale) {
        this.populationFemale = populationFemale;
    }
}
