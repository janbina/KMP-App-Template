package com.jetbrains.kmpapp.screens.detail

import androidx.compose.runtime.Stable
import com.jetbrains.kmpapp.data.MuseumObject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState

@Stable
data class DetailScreenUiState(
    val obj: MuseumObject?,
    val eventSink: (DetailScreenUiEvent) -> Unit,
) : CircuitUiState

sealed interface DetailScreenUiEvent : CircuitUiEvent {
    data object NavigateBack : DetailScreenUiEvent
}
