package com.example.lab13

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class UiState {
    Cargando, Contenido, Error
}
@Composable
fun StateTransitionDemo() {
    var currentState by remember { mutableStateOf(UiState.Cargando) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Cambiar el estado de forma cíclica
                currentState = when (currentState) {
                    UiState.Cargando -> UiState.Contenido
                    UiState.Contenido -> UiState.Error
                    UiState.Error -> UiState.Cargando
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Cambiar Estado")
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedContent(
            targetState = currentState,
            transitionSpec = {
                (slideInVertically { it } + fadeIn()) togetherWith
                        (slideOutVertically { -it } + fadeOut())
            }, label = ""
        ) { state ->
            val backgroundColor = when (state) {
                UiState.Cargando -> Color(0xFFBBDEFB)
                UiState.Contenido -> Color(0xFFC8E6C9)
                UiState.Error -> Color(0xFFFFCDD2)
            }
            val textColor = when (state) {
                UiState.Cargando -> Color(0xFF1976D2)
                UiState.Contenido -> Color(0xFF388E3C)
                UiState.Error -> Color(0xFFD32F2F)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .padding(16.dp)
                    .animateContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (state) {
                        UiState.Cargando -> "Cargando..."
                        UiState.Contenido -> "Contenido disponible"
                        UiState.Error -> "Se ha producido un error"
                    },
                    color = textColor,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}
