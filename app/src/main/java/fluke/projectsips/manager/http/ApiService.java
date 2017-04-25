package fluke.projectsips.manager.http;

import fluke.projectsips.dao.BorderCheckpointCollectionDao;
import fluke.projectsips.dao.BorderCheckpointTypeCollection;
import fluke.projectsips.dao.CpiCategCollectionDao;
import fluke.projectsips.dao.CpiCollectionDao;
import fluke.projectsips.dao.EcoMonthCollectionDao;
import fluke.projectsips.dao.FarmAreaCollectionDao;
import fluke.projectsips.dao.FarmAreaTypeCollectionDao;
import fluke.projectsips.dao.GdpCollectionDao;
import fluke.projectsips.dao.GppProvinceCollectionDao;
import fluke.projectsips.dao.GppSakaeoCollectionDao;
import fluke.projectsips.dao.GrpBuraphaCollectionDao;
import fluke.projectsips.dao.GrpCollectionDao;
import fluke.projectsips.dao.LfpEducationSexCollectionDao;
import fluke.projectsips.dao.LfpLaborCareerCollectionDao;
import fluke.projectsips.dao.LfpLaborEduCollectionDao;
import fluke.projectsips.dao.LfpLaborIndustryCollectionDao;
import fluke.projectsips.dao.LfpLaborNoWorkCollectionDao;
import fluke.projectsips.dao.LfpLaborSexCollectionDao;
import fluke.projectsips.dao.LfpLaborWorkingHoursCollectionDao;
import fluke.projectsips.dao.LfpLaborWorkingStatusCollectionDao;
import fluke.projectsips.dao.PopulationCollectionDao;
import fluke.projectsips.dao.ProvinceCollectionDao;
import fluke.projectsips.dao.RegionCollectionDao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("population.php")
    Call<PopulationCollectionDao> getPopulation(@Query("districtID") int districtID,
                                                @Query("populationYear") int populationYear);

    @GET("cpi.php")
    Call<CpiCollectionDao> getCpi(@Query("date") String date);

    @GET("cpi-categ.php")
    Call<CpiCategCollectionDao> getCpiCateg(@Query("date") String date);

    @GET("gpp-sakaeo.php")
    Call<GppSakaeoCollectionDao> getGppSakaeo(@Query("type") int type,
                                              @Query("year") int year);

    @GET("gpp-province.php")
    Call<GppProvinceCollectionDao> getGppProvince(@Query("type") int type,
                                                  @Query("year") int year,
                                                  @Query("provinceID") int provinceID);

    @GET("grp.php")
    Call<GrpCollectionDao> getGrp(@Query("type") int type,
                                  @Query("year") int year,
                                  @Query("region") int region);

    @GET("grp-burapha.php")
    Call<GrpBuraphaCollectionDao> getGrpBurapha(@Query("type") int type,
                                                @Query("year") int year);

    @GET("gdp.php")
    Call<GdpCollectionDao> getGdp(@Query("type") int type,
                                  @Query("year") int year);

    @GET("labor-sex.php")
    Call<LfpLaborSexCollectionDao> getLaborSex(@Query("year") int year,
                                               @Query("quarter") int quarter);

    @GET("edu-sex.php")
    Call<LfpEducationSexCollectionDao> getEduSex(@Query("year") int year,
                                                 @Query("quarter") int quarter);

    @GET("labor-edu.php")
    Call<LfpLaborEduCollectionDao> getLaborEdu(@Query("year") int year,
                                               @Query("quarter") int quarter);

    @GET("labor-career.php")
    Call<LfpLaborCareerCollectionDao> getLaborCareer(@Query("year") int year,
                                                     @Query("quarter") int quarter);

    @GET("labor-industry.php")
    Call<LfpLaborIndustryCollectionDao> getLaborIndustry(@Query("year") int year,
                                                       @Query("quarter") int quarter);

    @GET("labor-working-status.php")
    Call<LfpLaborWorkingStatusCollectionDao> getLaborWorkingStatus(@Query("year") int year,
                                                                   @Query("quarter") int quarter);

    @GET("labor-working-hours.php")
    Call<LfpLaborWorkingHoursCollectionDao> getLaborWorkingHours(@Query("year") int year,
                                                                 @Query("quarter") int quarter);

    @GET("labor-nowork.php")
    Call<LfpLaborNoWorkCollectionDao> getLaborNoWork(@Query("year") int year,
                                                     @Query("quarter") int quarter);

    @GET("eco-month.php")
    Call<EcoMonthCollectionDao> getEcoMonth(@Query("side") int side,
                                            @Query("date") String date);

    @GET("farm-area.php")
    Call<FarmAreaCollectionDao> getFarmArea(@Query("product") int product);

    @GET("border-checkpoint.php")
    Call<BorderCheckpointCollectionDao> getBorderCheckpoint(@Query("product") int product);

    @POST("province.php")
    Call<ProvinceCollectionDao> getProvince();

    @POST("farm-area-type.php")
    Call<FarmAreaTypeCollectionDao> getFarmAreaType();

    @POST("region.php")
    Call<RegionCollectionDao> getRegion();

    @POST("border-checkpoint-type.php")
    Call<BorderCheckpointTypeCollection> getBorderCheckpointType();

}
