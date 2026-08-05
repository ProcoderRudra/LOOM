package com.loom.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.loom.ui.components.AuthEmailField
import com.loom.ui.components.AuthPasswordField
import com.loom.ui.components.DividerWithText
import com.loom.ui.components.EncryptionBadge
import com.loom.ui.components.ForgotPasswordLink
import com.loom.ui.components.PrimaryLoginButton
import com.loom.ui.components.TelegramLoginButton
import com.loom.ui.theme.LoomColors

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LoomColors.BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo placeholder (TODO: Replace with actual logo)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "◆◇◆",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = LoomColors.PrimaryBlue
                    )
                }

                Text(
                    text = "LOOM",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoomColors.TextWhite,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "Secure. Private. Yours.",
                    fontSize = 12.sp,
                    color = LoomColors.PrimaryBlue,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Welcome Text & Form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    text = "Welcome back",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = LoomColors.TextWhite
                )
                Text(
                    text = "Login to access your encrypted cloud storage",
                    fontSize = 14.sp,
                    color = LoomColors.TextMuted,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                // Error Message
                if (uiState.errorMessage != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = LoomColors.ErrorRed.copy(alpha = 0.1f),
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp)
                    ) {
                        Text(
                            text = uiState.errorMessage!!,
                            fontSize = 12.sp,
                            color = LoomColors.ErrorRed,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }

                // Email Field
                AuthEmailField(
                    value = uiState.emailInput,
                    onValueChange = { viewModel.onEmailChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(12.dp))

                // Password Field with Forgot Password Link
                Column {
                    AuthPasswordField(
                        value = uiState.passwordInput,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        isVisible = uiState.isPasswordVisible,
                        onVisibilityToggle = { viewModel.togglePasswordVisibility() },
                        modifier = Modifier.fillMaxWidth()
                    )
                    ForgotPasswordLink(
                        onClick = onForgotPasswordClick,
                        modifier = Modifier.align(Alignment.End)
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                // Primary Login Button
                PrimaryLoginButton(
                    isLoading = uiState.isLoading,
                    onClick = { viewModel.onLoginClick() }
                )

                // Divider
                DividerWithText(modifier = Modifier.fillMaxWidth())

                // Telegram Login Button
                TelegramLoginButton(
                    isLoading = uiState.isLoading,
                    onClick = { viewModel.onTelegramLoginClick() }
                )
            }

            // Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EncryptionBadge(modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.padding(16.dp))

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "New to LOOM? ",
                        fontSize = 14.sp,
                        color = LoomColors.TextMuted
                    )
                    TextButton(onClick = onSignUpClick) {
                        Text(
                            text = "Create an account",
                            fontSize = 14.sp,
                            color = LoomColors.PrimaryBlue,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(12.dp))
            }
        }
    }

    // Handle navigation on successful login
    if (uiState.isLoginSuccessful) {
        onLoginSuccess()
    }
}

@Composable
@androidx.compose.material3.Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen()
}