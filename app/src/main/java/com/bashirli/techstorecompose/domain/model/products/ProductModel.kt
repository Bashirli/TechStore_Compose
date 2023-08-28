package com.bashirli.techstorecompose.domain.model.products

import com.bashirli.techstorecompose.data.dto.products.Product
import com.google.gson.annotations.SerializedName

data class ProductModel (
    val limit: Int?,
    val products: List<ProductListModel>,
    val skip: Int?,
    val total: Int?
)