package fluke.projectsips.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fluke on 3/12/2017 AD.
 */

public class EcoMonthDao {

    @SerializedName("kpiName")
    @Expose
    private String kpiName;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("transactionAmount")
    @Expose
    private Integer transactionAmount;

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
