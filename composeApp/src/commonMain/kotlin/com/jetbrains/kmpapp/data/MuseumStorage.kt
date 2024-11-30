package com.jetbrains.kmpapp.data

import com.jetbrains.kmpapp.di.AppScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

interface MuseumStorage {
    suspend fun saveObjects(newObjects: List<MuseumObject>)

    fun getObjectById(objectId: Int): Flow<MuseumObject?>

    fun getObjects(): Flow<List<MuseumObject>>
}

@AppScope
@Inject
class InMemoryMuseumStorage : MuseumStorage {
    private val storedObjects = MutableStateFlow(emptyList<MuseumObject>())

    override suspend fun saveObjects(newObjects: List<MuseumObject>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        return storedObjects.map { objects ->
            objects.find { it.objectID == objectId }
        }
    }

    override fun getObjects(): Flow<List<MuseumObject>> = storedObjects
}
