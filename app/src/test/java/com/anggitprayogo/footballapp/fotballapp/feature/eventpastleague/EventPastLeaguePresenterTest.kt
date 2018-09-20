package com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague

import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.EventPastLeagueCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventPastLeaguePresenterTest {

    @Mock
    private lateinit var presenter: EventPastLeaguePresenter
    @Mock
    private lateinit var scheduleRepository: MatchRepository
    @Mock
    private lateinit var events: EventPastLeagueResponse
    @Mock
    private lateinit var view: EventPastLeagueView


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = EventPastLeaguePresenter(view, scheduleRepository)
    }


    /**
     * Test Apakah API Call schedule pertandingan Gagal
     */
    @Test
    fun getScheduleError(){
        val id = "4328"

        presenter.getPastEvent(id)
        argumentCaptor<EventPastLeagueCallback<EventPastLeagueResponse>>().apply {
            Mockito.verify(scheduleRepository).getPastMatch(eq(id), capture())
            firstValue.onDataError()
        }

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventError()
        Mockito.verify(view).hideProgress()
    }
}