package com.jetbrains.kmpapp.navigation

import com.jetbrains.kmpapp.util.Parcelize
import com.slack.circuit.runtime.screen.Screen

@Parcelize
data object ListScreen : Screen

@Parcelize
data class DetailScreen(
    val objectId: Int,
) : Screen
