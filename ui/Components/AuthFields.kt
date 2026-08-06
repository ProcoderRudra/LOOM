package com.loom.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class LoginUiState(
    val emailInput: String = "",
    val passwordInput: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoginSuccessful: Boolean = false
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(emailInput = email, errorMessage = null)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(passwordInput = password, errorMessage = null)
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isPasswordVisible = !_uiState.value.isPasswordVisible
        )
    }

    fun onLoginClick() {
        val currentState = _uiState.value
        
        // Validation
        when {
            currentState.emailInput.isEmpty() -> {
                _uiState.value = currentState.copy(errorMessage = "Email is required")
                return
            }
            currentState.passwordInput.isEmpty() -> {
                _uiState.value = currentState.copy(errorMessage = "Password is required")
                return
            }
            !isValidEmail(currentState.emailInput) -> {
                _uiState.value = currentState.copy(errorMessage = "Invalid email format")
                return
            }
        }

        // Simulate login request
        _uiState.value = currentState.copy(isLoading = true)
        // TODO: Replace with actual TDLib/API call
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isLoginSuccessful = true
        )
    }

    fun onTelegramLoginClick() {
        // TODO: Initiate Telegram OAuth flow with encrypted session key handling
        _uiState.value = _uiState.value.copy(isLoading = true)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }
}
