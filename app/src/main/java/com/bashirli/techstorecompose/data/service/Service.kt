package com.bashirli.techstorecompose.data.service

import com.bashirli.techstorecompose.data.dto.CategoryDTO
import com.bashirli.techstorecompose.data.dto.products.Product
import com.bashirli.techstorecompose.data.dto.products.ProductDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("products/categories")
    suspend fun getCategories(): Response<CategoryDTO>

    @GET("products/category/{category}")
    suspend fun getCategoryProducts(@Path("category") category:String ) : Response<ProductDTO>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") id:Int) : Response<Product>

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query:String) : Response<ProductDTO>

    @GET("products")
    suspend fun getAllProducts() : Response<ProductDTO>
}