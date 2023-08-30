package com.bashirli.techstorecompose.data.source

import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.common.util.findExceptionMessage
import com.bashirli.techstorecompose.data.dto.CategoryDTO
import com.bashirli.techstorecompose.data.dto.products.Product
import com.bashirli.techstorecompose.data.dto.products.ProductDTO
import com.bashirli.techstorecompose.data.service.Service
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(
    private val service: Service
) : ApiSource{

    override suspend fun getCategories(): Resource<CategoryDTO> {
        return handleResponse(service.getCategories())
    }

    override suspend fun getCategoryProducts(category:String): Resource<ProductDTO> {
        return handleResponse(service.getCategoryProducts(category))
    }

    override suspend fun getProductDetails(id: Int): Resource<Product> {
        return handleResponse(service.getProductDetails(id))
    }

    override suspend fun searchProducts(query: String): Resource<ProductDTO> {
        return handleResponse(service.searchProducts(query))
    }

    override suspend fun getAllProducts(): Resource<ProductDTO> {
        return handleResponse(service.getAllProducts())
    }

    private fun <T : Any> handleResponse(response: Response<T>) : Resource<T>{
        return try {
            if(response.isSuccessful){
                response.body()?.let {
                    Resource.Success(it)
                }?:Resource.Error("Error")
            }else{
                Resource.Error(findExceptionMessage(response.errorBody()))
            }
        }catch (e:Exception){
            Resource.Error(e.localizedMessage)
        }
    }

}