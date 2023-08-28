package com.bashirli.techstorecompose.data.source

import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.data.dto.CategoryDTO
import com.bashirli.techstorecompose.data.dto.products.Product
import com.bashirli.techstorecompose.data.dto.products.ProductDTO
import kotlinx.coroutines.flow.Flow

interface ApiSource {

    suspend fun getCategories(): Resource<CategoryDTO>

    suspend fun getCategoryProducts(category:String):Resource<ProductDTO>

    suspend fun getProductDetails(id:Int) : Resource<Product>
}