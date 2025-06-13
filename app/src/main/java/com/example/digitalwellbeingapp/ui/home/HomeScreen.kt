package com.example.digitalwellbeingapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
){
    val state = homeViewModel.homeUiState.collectAsState().value
    HomeContent(
        state = state,
        onEvent = {event->homeViewModel.onEvent(event)}
    )
}