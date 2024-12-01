package com.jetbrains.kmpapp.screens.list

import androidx.compose.runtime.Stable
import com.jetbrains.kmpapp.data.MuseumObject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

@Stable
data class ListScreenUiState(
    val objects: List<MuseumObject>,
    val eventSink: (ListScreenUiEvent) -> Unit,
) : CircuitUiState

sealed interface ListScreenUiEvent : CircuitUiEvent {
    data class NavigateToDetail(val objectId: Int) : ListScreenUiEvent
}
