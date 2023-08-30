package com.bashirli.techstorecompose.ui.screen.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.domain.model.products.ProductModel
import com.bashirli.techstorecompose.domain.useCase.GetProductsUseCase
import com.bashirli.techstorecompose.domain.useCase.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMVVM @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state= mutableStateOf<SearchUiState>(SearchUiState.Loading)
    val state : State<SearchUiState> get()=_state



    private fun initProduct(){
        viewModelScope.launch {
            getProductsUseCase.getAllProducts().collectLatest {
                when(it){
                    is Resource.Error -> {
                        _state.value=SearchUiState.Error(it.message)
                    }
                    Resource.Loading -> _state.value=SearchUiState.Loading
                    is Resource.Success -> {
                        it.result?.let {
                            _state.value=SearchUiState.InitialProducts(it)
                        }
                    }
                }
            }
        }
    }

    private fun searchProduct(query: String){
        viewModelScope.launch {
            searchProductsUseCase(query).collectLatest {
                when(it){
                    is Resource.Error -> {
                        _state.value=SearchUiState.Error(it.message)
                    }
                    Resource.Loading -> {
                        _state.value=SearchUiState.Loading
                    }
                    is Resource.Success -> {
                        it.result?.let {
                            _state.value=SearchUiState.SearchProducts(it)
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.SearchProducts -> {
                searchProduct(event.query)
            }

            SearchEvent.InitialProduct->{
                initProduct()
            }
        }
    }

    sealed class SearchUiState{
        data class Error (val message:String) : SearchUiState()

        object Loading : SearchUiState()

        data class SearchProducts (val data : ProductModel) : SearchUiState()

        data class InitialProducts(val data : ProductModel) : SearchUiState()
    }

    sealed class SearchEvent{
        data class SearchProducts(val query:String) : SearchEvent()

        object InitialProduct : SearchEvent()
    }

}