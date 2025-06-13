package com.example.digitalwellbeingapp.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitalwellbeingapp.core.domain.model.AppUsageInfo
import com.example.digitalwellbeingapp.core.domain.model.AppUsageUseCases
import com.example.digitalwellbeingapp.core.domain.usecase.GetTodayUsageUseCase
import com.example.digitalwellbeingapp.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTodayUsageUseCase: GetTodayUsageUseCase
): ViewModel(){

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        onEvent(HomeUiEvent.LoadHomeData)
    }
    fun onEvent(e: HomeUiEvent){
        when(e){
            HomeUiEvent.LoadHomeData -> {
                loadHomeData()
            }
            is HomeUiEvent.NavigateToAppDetailDashboard -> {

            }
            HomeUiEvent.NavigateToTotalUsageDashboard -> {

            }
        }
    }

    private fun loadHomeData(){
        viewModelScope.launch {
            _homeUiState.update {
                it.copy(isLoading = true)
            }
            getTodayUsageUseCase().collectLatest {
                result->
                when(result){
                    is Resource.Error<*> -> {
                        _homeUiState.update {
                            it.copy(isLoading = false, error = result.message)
                        }
                    }
                    is Resource.Loading<*> -> {
                        _homeUiState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success<*> -> {
                        val appList = result.data as? List<AppUsageInfo>
                        val totalTime = appList?.sumOf{it.totalTimeForeground}
                        _homeUiState.update {
                            it.copy(isLoading = false,
                                totalScreenTimeToday = totalTime?:0L,
                                appUsageInfo = appList!!
                                )
                        }
                    }
                }
            }
        }
    }
}