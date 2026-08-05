package com.loom.ui.screens.login

import android.util.Patterns
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
) {
    // SECURITY: Prevent accidental password leakage via logs/crash reports
    override fun toString(): String {
        return "LoginUiState(emailInput=$emailInput, passwordInput=***REDACTED***, " +
                "isPasswordVisible=$isPasswordVisible, isLoading=$isLoading, " +
                "errorMessage=$errorMessage, isLoginSuccessful=$isLoginSuccessful)"
    }
}

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

        when {
            currentState.emailInput.isBlank() -> {
                _uiState.value = currentState.copy(errorMessage = "Email is required")
                return
            }
            currentState.passwordInput.isBlank() -> {
                _uiState.value = currentState.copy(errorMessage = "Password is required")
                return
            }
            !isValidEmail(currentState.emailInput) -> {
                _uiState.value = currentState.copy(errorMessage = "Invalid email format")
                return
            }
        }

        _uiState.value = currentState.copy(isLoading = true)
        // TODO: Replace with actual TDLib/API call.
        // Do NOT log currentState.passwordInput anywhere in this path.
        _uiState.value = _uiState.value.copy(
            isLoading = false,
            isLoginSuccessful = true
        )
    }

    fun onTelegramLoginClick() {
        // TODO: Initiate Telegram OAuth flow.
        // Session strings must be encrypted at rest (EncryptedSharedPreferences / Keystore)
        // and never transmitted over cleartext HTTP.
        _uiState.value = _uiState.value.copy(isLoading = true)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }

    // SECURITY: Use Android's built-in RFC-compliant matcher instead of a naive "@" + "." check
    private fun isValidEmail(email: String): Boolean {
        return email.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
