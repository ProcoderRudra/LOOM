package com.loom.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loom.ui.theme.LoomColors

@Composable
fun PrimaryLoginButton(
    text: String = "Login",
    isLoading: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = LoomColors.PrimaryBlue,
            disabledContainerColor = LoomColors.PrimaryBlue.copy(alpha = 0.5f)
        ),
        enabled = !isLoading
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .height(20.dp),
                    color = LoomColors.TextWhite,
                    strokeWidth = 2.dp
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Login,
                    contentDescription = "Login",
                    tint = LoomColors.TextWhite,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(
                text = if (isLoading) "Logging in..." else text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = LoomColors.TextWhite
            )
        }
    }
}

@Composable
fun TelegramLoginButton(
    isLoading: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(1.dp, LoomColors.TelegramBlue, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = LoomColors.TelegramBlue
        ),
        enabled = !isLoading
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "✈ ",
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = if (isLoading) "Connecting..." else "Login with Telegram",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = LoomColors.TelegramBlue
            )
        }
    }
}

@Composable
fun DividerWithText(
    text: String = "OR",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = LoomColors.TextMuted.copy(alpha = 0.2f),
            thickness = 1.dp
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 12.sp,
            color = LoomColors.TextMuted,
            fontWeight = FontWeight.Medium
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = LoomColors.TextMuted.copy(alpha = 0.2f),
            thickness = 1.dp
        )
    }
}

@Composable
fun ForgotPasswordLink(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = "Forgot Password?",
            fontSize = 14.sp,
            color = LoomColors.PrimaryBlue,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.SemiBold
        )
    }
}
