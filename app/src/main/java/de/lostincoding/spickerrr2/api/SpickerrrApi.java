package de.lostincoding.spickerrr2.api;

import java.util.List;

import de.lostincoding.spickerrr2.model.Package;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lostincoding on 26.05.16.
 */
public interface SpickerrrApi {

    @GET("{apikey}/books")
    Call<BookResponse> listBooks(@Path("apikey") String apikey);

    @GET("{apikey}/currentbooks")
    Call<BookResponse> listCurrentBooks(@Path("apikey") String apikey);

    @GET("{apikey}/book/{bookkey}/packages")
    Call<PackageResponse> listPackagesFromBook(@Path("apikey") String apikey, @Path("bookkey") String bookkey);

    @GET("{apikey}/activepackages")
    Call<PackageResponse> listActivePackages(@Path("apikey") String apikey);

    @GET("{apikey}/package/{packagekey}")
    Call<PackageResponse> getPackage(@Path("apikey") String apikey, @Path("packagekey") String packagekey);

}

