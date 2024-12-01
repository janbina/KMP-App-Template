package com.jetbrains.kmpapp.navigation

import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.detail.DetailScreenUiState
import com.jetbrains.kmpapp.screens.list.ListScreen
import com.jetbrains.kmpapp.screens.list.ListScreenUiState
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject

@Inject
class CircuitUiFactory : Ui.Factory {
    
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
        return when (screen) {
            is ListScreen -> ui<ListScreenUiState> { state, modifier ->
                ListScreen(modifier, state)
            }
            is DetailScreen -> ui<DetailScreenUiState> { state, modifier ->
                DetailScreen(modifier, state)
            }
            else -> null
        }
    }
}
