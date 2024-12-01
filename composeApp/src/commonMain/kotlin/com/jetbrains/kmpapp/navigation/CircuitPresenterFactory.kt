package com.jetbrains.kmpapp.navigation

import com.jetbrains.kmpapp.screens.detail.DetailScreenPresenter
import com.jetbrains.kmpapp.screens.list.ListScreenPresenter
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import me.tatarka.inject.annotations.Inject

@Inject
class CircuitPresenterFactory(
    private val listScreenPresenter: (Navigator) -> ListScreenPresenter,
    private val detailScreenPresenter: (Navigator, DetailScreen) -> DetailScreenPresenter,
) : Presenter.Factory {

    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext,
    ): Presenter<*>? {
        return when (screen) {
            is ListScreen -> listScreenPresenter(navigator)
            is DetailScreen -> detailScreenPresenter(navigator, screen)
            else -> null
        }
    }
}