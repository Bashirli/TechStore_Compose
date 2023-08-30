package com.bashirli.techstorecompose.data.repo

import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.data.dto.products.Product
import com.bashirli.techstorecompose.data.mapper.toCategoryModel
import com.bashirli.techstorecompose.data.mapper.toProductListModel
import com.bashirli.techstorecompose.data.mapper.toProductModel
import com.bashirli.techstorecompose.data.source.ApiSource
import com.bashirli.techstorecompose.domain.model.CategoryModel
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.domain.model.products.ProductModel
import com.bashirli.techstorecompose.domain.repo.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiSource: ApiSource
) : ApiRepository {
    override suspend fun getCategories(): Flow<Resource<CategoryModel>> = flow {
        emit(Resource.Loading)

        val response = apiSource.getCategories()
        when(response){
            is Resource.Error->{
                emit(Resource.Error(response.message))
            }
            is Resource.Success->{
                emit(Resource.Success(response.result?.toCategoryModel()))
            }
            else->{Unit}
        }

    }

    override suspend fun getCategoryProducts(category: String): Flow<Resource<ProductModel>> = flow {
        emit(Resource.Loading)

        val response = apiSource.getCategoryProducts(category)
        when(response){
            is Resource.Error->{
                emit(Resource.Error(response.message))
            }
            is Resource.Success->{
                emit(Resource.Success(response.result?.toProductModel()))
            }
            else->{Unit}
        }
    }

    override suspend fun getProductDetails(id: Int): Flow<Resource<ProductListModel>> = flow {
        emit(Resource.Loading)

        val response = apiSource.getProductDetails(id)
        when(response){
            is Resource.Error -> TODO()
            Resource.Loading -> Unit
            is Resource.Success -> emit(Resource.Success(response.result?.toProductListModel()))
        }

    }

    override suspend fun searchProducts(query: String): Flow<Resource<ProductModel>> = flow {
        emit(Resource.Loading)
        val response = apiSource.searchProducts(query)
        when(response){
            is Resource.Error -> {
                emit(Resource.Error(response.message))
            }
            Resource.Loading -> Unit
            is Resource.Success -> {
                emit(Resource.Success(response.result?.toProductModel()))
            }
        }
    }

    override suspend fun getAllProducts(): Flow<Resource<ProductModel>> = flow {
        emit(Resource.Loading)
        val response = apiSource.getAllProducts()
        when(response){
            is Resource.Error -> {
                emit(Resource.Error(response.message))
            }
            Resource.Loading -> Unit
            is Resource.Success -> {
                emit(Resource.Success(response.result?.toProductModel()))
            }
        }
    }

}