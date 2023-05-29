package com.roxasjearom.hellovalorant.presentation.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxasjearom.hellovalorant.domain.model.AgentDetails
import com.roxasjearom.hellovalorant.domain.repository.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val agentRepository: AgentRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _agentDetailsUiState = MutableStateFlow(AgentDetailsUiState())
    val agentDetailsUiState: StateFlow<AgentDetailsUiState> = _agentDetailsUiState.asStateFlow()

    init {
        val agentUuid: String = checkNotNull(savedStateHandle["agentUuid"])
        getAgentByUuid(agentUuid)
    }

    private fun getAgentByUuid(uuid: String) {
        viewModelScope.launch {
            val agentDetails = agentRepository.getAgentByUuid(uuid)
            _agentDetailsUiState.update {
                it.copy(agentDetails = agentDetails)
            }
        }
    }
}

data class AgentDetailsUiState(val agentDetails: AgentDetails? = null)
