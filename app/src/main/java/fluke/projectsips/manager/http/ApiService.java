package fluke.projectsips.manager.http;

import fluke.projectsips.dao.CpiCategCollectionDao;
import fluke.projectsips.dao.CpiCollectionDao;
import fluke.projectsips.dao.GppProvinceCollectionDao;
import fluke.projectsips.dao.GppSakaeoCollectionDao;
import fluke.projectsips.dao.GrpBuraphaCollectionDao;
import fluke.projectsips.dao.GrpCollectionDao;
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

    @POST("province.php")
    Call<ProvinceCollectionDao> getProvince();

    @POST("region.php")
    Call<RegionCollectionDao> getRegion();

}
