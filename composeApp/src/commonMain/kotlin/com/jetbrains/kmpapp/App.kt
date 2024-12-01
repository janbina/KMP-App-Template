package com.jetbrains.kmpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.jetbrains.kmpapp.di.AppComponent
import com.jetbrains.kmpapp.di.createKmp
import com.jetbrains.kmpapp.navigation.ListScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator

private val appComponent by lazy { AppComponent.createKmp() }

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            CircuitCompositionLocals(circuit = appComponent.circuit) {
                val backStack = rememberSaveableBackStack(root = ListScreen)
                val navigator = rememberCircuitNavigator(backStack) {
                    // Do something when the root screen is popped, usually exiting the app
                }
                NavigableCircuitContent(
                    navigator = navigator,
                    backStack = backStack,
//                    decoration = remember(navigator) {
//                        GestureNavigationDecoration(onBackInvoked = navigator::pop)
//                    },
                )
            }
        }
    }
}
