package com.example.lab13

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedScreenDemo() {
    var isExpanded by remember { mutableStateOf(false) }
    val boxSize by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        animationSpec = tween(durationMillis = 500), label = ""
    )
    val boxColor by animateColorAsState(
        targetValue = if (isExpanded) Color(0xFF4CAF50) else Color(0xFF2196F3),
        animationSpec = tween(durationMillis = 500), label = ""
    )
    var isButtonVisible by remember { mutableStateOf(true) }

       var isLightMode by remember { mutableStateOf(true) }

        val backgroundColor by animateColorAsState(
        targetValue = if (isLightMode) Color(0xFFF5F5F5) else Color.DarkGray,
        animationSpec = tween(durationMillis = 700), label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = if (isLightMode) {
                    Brush.verticalGradient(
                        colors = listOf(Color.White, backgroundColor)
                    )
                } else {
                    Brush.verticalGradient(
                        colors = listOf(backgroundColor, Color.Black)
                    )
                }
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .shadow(8.dp, shape = RoundedCornerShape(12.dp))
                    .size(boxSize)
                    .background(boxColor, shape = RoundedCornerShape(12.dp))
                    .clickable { isExpanded = !isExpanded }
            )

            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(
                visible = isButtonVisible,
                enter = slideInVertically(animationSpec = tween(500)) + fadeIn(animationSpec = tween(500)),
                exit = slideOutVertically(animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            ) {
                Button(
                    onClick = { isButtonVisible = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE91E63),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Desaparecer")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { isLightMode = !isLightMode },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isLightMode) Color(0xFFBDBDBD) else Color(0xFF424242),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(text = if (isLightMode) "Activar Modo Oscuro" else "Activar Modo Claro")
            }
        }
    }
}
