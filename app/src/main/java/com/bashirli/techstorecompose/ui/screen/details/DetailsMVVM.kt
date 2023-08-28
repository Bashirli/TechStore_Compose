package com.bashirli.techstorecompose.ui.screen.details

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.techstorecompose.common.util.Resource
import com.bashirli.techstorecompose.domain.model.products.ProductListModel
import com.bashirli.techstorecompose.domain.useCase.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsMVVM @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<DetailsUiState>(DetailsUiState.Loading)
    val state : State<DetailsUiState> get()=_state


    private fun getProductDetails(id:Int){
        viewModelScope.launch {
            getProductDetailsUseCase(id).collectLatest {
                when(it){
                    is Resource.Error -> {
                        _state.value=DetailsUiState.Error(it.message)
                    }
                    Resource.Loading -> {
                        _state.value = DetailsUiState.Loading
                    }
                    is Resource.Success -> {
                        it.result?.let {
                            _state.value=DetailsUiState.ProductDetails(it)
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.GetProductDetails -> {
                getProductDetails(event.id)
            }
        }
    }


    sealed class DetailsEvent(){
        data class GetProductDetails(val id:Int) : DetailsEvent()
    }


    sealed class DetailsUiState(){
        object Loading : DetailsUiState()

        data class Error(val message:String) : DetailsUiState()

        data class ProductDetails(val data : ProductListModel) : DetailsUiState()
    }

}