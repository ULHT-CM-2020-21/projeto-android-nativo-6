package pt.ulusofona.deisi.a2020.cm.g6.ui.listener

import pt.ulusofona.deisi.a2020.cm.g6.data.local.room.entities.Covid

interface FetchRepositoryListener {
    fun onFetchedRepository(covid: Covid)
}