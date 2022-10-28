package com.dinhnt.loadmoredatainrecyclerviewandroid.service;

import com.dinhnt.loadmoredatainrecyclerviewandroid.models.Product;

import java.util.ArrayList;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPI {
    String baseURL = "https://kynalab.com/api/";

    @GET("get-data-product-load-more")
    Observable<ArrayList<Product>> getData(@Query("currentIndex") int currentIndex, @Query("elementsNumber") int elementsNumber);
}
