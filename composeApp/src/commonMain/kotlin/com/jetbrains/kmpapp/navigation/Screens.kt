package com.jetbrains.kmpapp.navigation

import com.jetbrains.kmpapp.util.CommonParcelable
import com.jetbrains.kmpapp.util.CommonParcelize
import com.slack.circuit.runtime.screen.Screen

@CommonParcelize
data object ListScreen : Screen, CommonParcelable

@CommonParcelize
data class DetailScreen(
    val objectId: Int,
) : Screen, CommonParcelable
