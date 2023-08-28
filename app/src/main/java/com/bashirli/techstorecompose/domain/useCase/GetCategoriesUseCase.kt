package com.bashirli.techstorecompose.domain.useCase

import com.bashirli.techstorecompose.domain.repo.ApiRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repo:ApiRepository
) {

    suspend operator fun invoke()=repo.getCategories()

}