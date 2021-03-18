package com.example.dz3_android3.data;

import com.example.dz3_android3.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MockerApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @DELETE("posts/{id}")
    Call<Post> deletePost(@Path("id") String id);

    @POST("posts")
    Call<Post> insertPost(@Body Post post);

    @PUT("posts/{id}")
    Call<Post> updatePost(@Path("id") String id, @Body Post post);

}
