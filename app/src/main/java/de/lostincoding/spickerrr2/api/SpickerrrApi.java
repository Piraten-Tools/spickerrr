package de.lostincoding.spickerrr2.api;

import java.util.List;

import de.lostincoding.spickerrr2.model.Book;
import de.lostincoding.spickerrr2.model.Package;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lostincoding on 26.05.16.
 */
public interface SpickerrrApi {

    @GET("{apikey}/books")
    Call<List<Book>> listBooks(@Path("apikey") String apikey);

    @GET("{apikey}/currentbooks")
    Call<List<Book>> listCurrentBooks(@Path("apikey") String apikey);

    @GET("{apikey}/book/{bookkey}/packages")
    Call<List<Package>> listPackagesFromBook(@Path("apikey") String apikey, @Path("bookkey") String bookkey);

    @GET("{apikey}/activepackages")
    Call<List<Package>> listActivePackages(@Path("apikey") String apikey);

    @GET("{apikey}/package/{packagekey}")
    Call<Package> getPackage(@Path("apikey") String apikey, @Path("packagekey") String packagekey);
    
}

