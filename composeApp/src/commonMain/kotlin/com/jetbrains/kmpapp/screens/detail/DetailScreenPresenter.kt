package com.jetbrains.kmpapp.screens.detail

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
class DetailScreenPresenter(
    @Assisted
    private val navigator: Navigator,
    @Assisted
    private val screen: DetailScreen,
    private val museumRepository: MuseumRepository,
) : Presenter<DetailScreenUiState> {

    @Composable
    override fun present(): DetailScreenUiState {

        val flow = remember { museumRepository.getObjectById(screen.objectId) }

        fun eventSink(event: DetailScreenUiEvent) {
            when (event) {
                DetailScreenUiEvent.NavigateBack -> navigator.pop()
            }
        }

        return DetailScreenUiState(
            obj = flow.collectAsState(null).value,
            eventSink = ::eventSink,
        )
    }
}
