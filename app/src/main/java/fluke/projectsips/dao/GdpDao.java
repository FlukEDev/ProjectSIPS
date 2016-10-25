package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 10/25/2016 AD.
 */

public class GdpDao {

    @SerializedName("SUM(Economic_GPP_Province_Transaction.yearPrice)")
    @Expose
    private String sUMEconomicGPPProvinceTransactionYearPrice;
    @SerializedName("SUM(Economic_GPP_Province_Transaction.stablePrice)")
    @Expose
    private String sUMEconomicGPPProvinceTransactionStablePrice;
    @SerializedName("Population")
    @Expose
    private String population;
    @SerializedName("provinceName")
    @Expose
    private String provinceName;

    /**
     *
     * @return
     * The sUMEconomicGPPProvinceTransactionYearPrice
     */
    public String getSUMEconomicGPPProvinceTransactionYearPrice() {
        return sUMEconomicGPPProvinceTransactionYearPrice;
    }

    /**
     *
     * @param sUMEconomicGPPProvinceTransactionYearPrice
     * The SUM(Economic_GPP_Province_Transaction.yearPrice)
     */
    public void setSUMEconomicGPPProvinceTransactionYearPrice(String sUMEconomicGPPProvinceTransactionYearPrice) {
        this.sUMEconomicGPPProvinceTransactionYearPrice = sUMEconomicGPPProvinceTransactionYearPrice;
    }

    /**
     *
     * @return
     * The sUMEconomicGPPProvinceTransactionStablePrice
     */
    public String getSUMEconomicGPPProvinceTransactionStablePrice() {
        return sUMEconomicGPPProvinceTransactionStablePrice;
    }

    /**
     *
     * @param sUMEconomicGPPProvinceTransactionStablePrice
     * The SUM(Economic_GPP_Province_Transaction.stablePrice)
     */
    public void setSUMEconomicGPPProvinceTransactionStablePrice(String sUMEconomicGPPProvinceTransactionStablePrice) {
        this.sUMEconomicGPPProvinceTransactionStablePrice = sUMEconomicGPPProvinceTransactionStablePrice;
    }

    /**
     *
     * @return
     * The population
     */
    public String getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     * The Population
     */
    public void setPopulation(String population) {
        this.population = population;
    }

    /**
     *
     * @return
     * The provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     *
     * @param provinceName
     * The provinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

}
