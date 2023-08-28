package com.bashirli.techstorecompose.domain.useCase

import com.bashirli.techstorecompose.domain.repo.ApiRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ApiRepository
) {

    suspend fun getCategoryProducts(category:String) = repository.getCategoryProducts(category)

}