package fluke.projectsips.manager.http;

import fluke.projectsips.dao.PopulationCollectionDao;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("population/districtID/{districtID}/populationYear/{populationYear}")
    Call<PopulationCollectionDao> getPopulation(@Path("districtID") int districtID, @Path("populationYear") int populationYear);


}
