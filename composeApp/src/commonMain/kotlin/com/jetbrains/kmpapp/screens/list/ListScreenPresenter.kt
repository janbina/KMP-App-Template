package com.jetbrains.kmpapp.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.navigation.DetailScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class ListScreenPresenter(
    @Assisted
    private val navigator: Navigator,
    private val museumRepository: MuseumRepository,
) : Presenter<ListScreenUiState> {

    @Composable
    override fun present(): ListScreenUiState {
        val flow = remember { museumRepository.getObjects() }

        fun eventSink(event: ListScreenUiEvent) {
            when (event) {
                is ListScreenUiEvent.NavigateToDetail ->  {
                    navigator.goTo(DetailScreen(event.objectId))
                }
            }
        }

        return ListScreenUiState(
            objects = flow.collectAsState(emptyList()).value,
            eventSink = ::eventSink,
        )
    }
}
