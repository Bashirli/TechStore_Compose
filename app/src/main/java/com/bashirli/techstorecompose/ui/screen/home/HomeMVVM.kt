package com.bashirli.techstorecompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.domain.model.CategoryModel
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.domain.model.products.ProductModel
import com.bashirli.techstorecompose.domain.useCase.GetCategoriesUseCase
import com.bashirli.techstorecompose.domain.useCase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMVVM @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState.Nothing)
    val state : State<HomeState> get()=_state

    private val _categoryState = mutableStateOf<HomeCategoriesState>(HomeCategoriesState.Nothing)
    val categoryState : State<HomeCategoriesState> get()=_categoryState



    init {
        getCategories()
    }


    private fun getCategoryProducts(category:String){
        viewModelScope.launch {
            getCategoryProductsUseCase.getCategoryProducts(category).collectLatest {
                when(it){
                    is Resource.Error -> {
                        _categoryState.value=HomeCategoriesState.Error(it.message)
                    }
                    Resource.Loading -> _categoryState.value=HomeCategoriesState.LoadingCategories
                    is Resource.Success -> {
                        it.result?.let {
                            _categoryState.value=HomeCategoriesState.CategoryProducts(it)
                        }
                    }
                }
            }
        }
    }

    private fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase().collectLatest {
                when(it){
                    is Resource.Success -> {
                        it.result?.let {
                            _state.value = HomeState.Categories(it)
                        }
                    }

                    is Resource.Error -> {
                        _state.value = HomeState.Error(it.message)
                    }

                    is Resource.Loading -> {
                        _state.value = HomeState.Loading
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.Categories->{
                getCategories()
            }
            is HomeEvent.CategoryProduct->{
                getCategoryProducts(event.category)
            }
        }
    }


    sealed class HomeState(){
        object Nothing : HomeState()

        object Loading : HomeState()

        data class Error(val message:String) : HomeState()

        data class Categories(val data : CategoryModel) : HomeState()

    }

    sealed class HomeCategoriesState(){
        object Nothing : HomeCategoriesState()

        data class Error(val message:String) : HomeCategoriesState()

        object LoadingCategories : HomeCategoriesState()

        data class CategoryProducts(val data : ProductModel) : HomeCategoriesState()
    }

    sealed class HomeEvent(){
        object Nothing : HomeState()

        object Categories : HomeEvent()

        data class CategoryProduct(val category:String) : HomeEvent()
    }

}