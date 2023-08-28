package com.bashirli.techstorecompose.data.mapper

import com.bashirli.techstorecompose.data.dto.CategoryDTO
import com.bashirli.techstorecompose.data.dto.products.Product
import com.bashirli.techstorecompose.data.dto.products.ProductDTO
import com.bashirli.techstorecompose.domain.model.CategoryModel
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.domain.model.products.ProductModel

fun CategoryDTO.toCategoryModel()=
    CategoryModel(
    this
)


fun List<Product>.toProductListModel()=map {
    with(it){
        ProductListModel(
            brand, category, description, discountPercentage, id,price, images, rating, stock, thumbnail, title
        )
    }
}

fun Product.toProductListModel()=  ProductListModel(
            brand, category, description, discountPercentage, id,price, images, rating, stock, thumbnail, title
        )



fun ProductDTO.toProductModel()=ProductModel(
    limit,
    products.toProductListModel(),
    skip,
    total
)