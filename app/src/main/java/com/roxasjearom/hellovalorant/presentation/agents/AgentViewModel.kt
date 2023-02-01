package com.roxasjearom.hellovalorant.presentation.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxasjearom.hellovalorant.domain.model.Agent
import com.roxasjearom.hellovalorant.domain.repository.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(
    private val agentRepository: AgentRepository
) : ViewModel() {

    private val _agentUiState = MutableStateFlow(AgentUiState())
    val agentUiState: StateFlow<AgentUiState> = _agentUiState.asStateFlow()

    init {
        getAgents()
    }

    private fun getAgents() {
        viewModelScope.launch {
            val agents = agentRepository.getAgents()
            _agentUiState.update {
                it.copy(agents = agents)
            }
        }
    }
}

data class AgentUiState(
    val agents: List<Agent> = emptyList()
)
