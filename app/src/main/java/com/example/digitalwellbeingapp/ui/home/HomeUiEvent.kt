package com.example.digitalwellbeingapp.ui.home

sealed class HomeUiEvent {
    data object LoadHomeData: HomeUiEvent()
    data class NavigateToAppDetailDashboard(val packageName:String): HomeUiEvent()
    data object NavigateToTotalUsageDashboard: HomeUiEvent()
}