package com.bashirli.techstorecompose.domain.model.products

import com.google.gson.annotations.SerializedName

data class ProductListModel(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double?,
    val id: Int?,
    val price: Int?,
    val images: List<String>,
    val rating: Double?,
    val stock: Int?,
    val thumbnail: String?,
    val title: String?
)