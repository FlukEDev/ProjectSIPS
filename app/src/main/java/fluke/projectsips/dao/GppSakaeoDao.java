package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 7/31/2016 AD.
 */

public class GppSakaeoDao {

    @SerializedName("yearPrice")
    @Expose
    private String yearPrice;
    @SerializedName("stablePrice")
    @Expose
    private String stablePrice;
    @SerializedName("ecoSubSecName")
    @Expose
    private String ecoSubSecName;
    @SerializedName("Population")
    @Expose
    private String population;

    /**
     * @return The yearPrice
     */
    public String getYearPrice() {
        return yearPrice;
    }

    /**
     * @param yearPrice The yearPrice
     */
    public void setYearPrice(String yearPrice) {
        this.yearPrice = yearPrice;
    }

    /**
     * @return The stablePrice
     */
    public String getStablePrice() {
        return stablePrice;
    }

    /**
     * @param stablePrice The stablePrice
     */
    public void setStablePrice(String stablePrice) {
        this.stablePrice = stablePrice;
    }

    /**
     * @return The ecoSubSecName
     */
    public String getEcoSubSecName() {
        return ecoSubSecName;
    }

    /**
     * @param ecoSubSecName The ecoSubSecName
     */
    public void setEcoSubSecName(String ecoSubSecName) {
        this.ecoSubSecName = ecoSubSecName;
    }

    /**
     * @return The population
     */
    public String getPopulation() {
        return population;
    }

    /**
     * @param population The Population
     */
    public void setPopulation(String population) {
        this.population = population;
    }


}
