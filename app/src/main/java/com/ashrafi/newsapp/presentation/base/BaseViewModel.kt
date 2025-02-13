package com.ashrafi.newsapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : ViewState, Intent : ViewIntent, Effect : ViewEffect>(
) : ViewModel() {

    private val initialState: UiState by lazy { initialState() }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    protected fun getState(): UiState = uiState.value

    private val _event: MutableSharedFlow<Intent> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        observeEvents()
    }

     private fun observeEvents() {
        viewModelScope.launch {
            _event.collect{
                processIntents(it)
            }
        }
     }

    fun setEvent(intent: Intent) {
      viewModelScope.launch { _event.emit(intent) }
    }

    protected fun updateState(reduce: (UiState) -> UiState) {
        _uiState.update{
            reduce(_uiState.value)
        }

    }

    protected fun sendEffect(handler: () -> Effect) {
        viewModelScope.launch {
            _effect.send(handler())
        }
    }

    abstract fun initialState(): UiState


    abstract fun processIntents(viewIntent: Intent)
}

interface ViewState

interface ViewIntent

interface ViewEffect