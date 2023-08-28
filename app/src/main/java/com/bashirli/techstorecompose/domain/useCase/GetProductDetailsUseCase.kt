package com.bashirli.techstorecompose.domain.useCase

import com.bashirli.techstorecompose.domain.repo.ApiRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repo:ApiRepository
) {

    suspend operator fun invoke(id:Int) = repo.getProductDetails(id)

}