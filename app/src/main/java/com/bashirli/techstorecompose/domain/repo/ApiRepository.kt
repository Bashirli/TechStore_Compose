package com.bashirli.techstorecompose.domain.repo

import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.data.dto.products.Product
import com.bashirli.techstorecompose.domain.model.CategoryModel
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.domain.model.products.ProductModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getCategories(): Flow<Resource<CategoryModel>>

    suspend fun getCategoryProducts(category:String) : Flow<Resource<ProductModel>>

    suspend fun getProductDetails(id:Int) : Flow<Resource<ProductListModel>>

    suspend fun searchProducts(query:String) : Flow<Resource<ProductModel>>

    suspend fun getAllProducts() : Flow<Resource<ProductModel>>

}