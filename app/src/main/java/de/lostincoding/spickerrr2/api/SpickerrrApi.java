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

    @GET("books")
    Call<BookResponse> listBooks();

    @GET("currentbooks")
    Call<BookResponse> listCurrentBooks();

    @GET("book/{bookkey}/packages")
    Call<PackageResponse> listPackagesFromBook( @Path("bookkey") String bookkey);

    @GET("activepackages")
    Call<PackageResponse> listActivePackages();

    @GET("package/{packagekey}")
    Call<PackageResponse> getPackage( @Path("packagekey") String packagekey);

}

