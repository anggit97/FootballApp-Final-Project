package com.anggitprayogo.footballclub_scheduling.api

import com.anggitprayogo.footballapp.fotballapp.network.repository.ApiRepository
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun doRequest() {

        //make mock object
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"

        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}