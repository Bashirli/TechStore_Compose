package com.bashirli.techstorecompose.domain.useCase

import com.bashirli.techstorecompose.domain.repo.ApiRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repo : ApiRepository
) {

    suspend operator fun invoke(query:String) = repo.searchProducts(query)

}