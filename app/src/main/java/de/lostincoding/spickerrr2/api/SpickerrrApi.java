package de.lostincoding.spickerrr2.api;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lostincoding on 26.05.16.
 */
public interface SpickerrrApi {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user)
}
