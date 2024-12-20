package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.InMemoryMuseumStorage
import com.jetbrains.kmpapp.data.KtorMuseumApi
import com.jetbrains.kmpapp.data.MuseumApi
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.data.MuseumStorage
import com.jetbrains.kmpapp.navigation.CircuitPresenterFactory
import com.jetbrains.kmpapp.navigation.CircuitUiFactory
import com.slack.circuit.foundation.Circuit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@AppScope
@Component
abstract class AppComponent {

    abstract val circuit: Circuit

    @AppScope
    @Provides
    protected fun provideHttpClient(): HttpClient {
        val json = Json { ignoreUnknownKeys = true }
        return HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    @Provides
    @AppScope
    fun provideCircuit(
        uiFactory: CircuitUiFactory,
        presenterFactory: CircuitPresenterFactory,
    ): Circuit {
        return Circuit.Builder()
            .addUiFactory(uiFactory)
            .addPresenterFactory(presenterFactory)
            .build()
    }

    protected val KtorMuseumApi.bind: MuseumApi
        @Provides get() = this

    protected val InMemoryMuseumStorage.bind: MuseumStorage
        @Provides get() = this

    companion object
}

@KmpComponentCreate
expect fun AppComponent.Companion.createKmp(): AppComponent
