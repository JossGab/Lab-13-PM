package com.example.lab13

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun AnimateDp() {
    var isExpanded by remember { mutableStateOf(false) }
    // Animar el tamaño del círculo
    val circleDiameter by animateDpAsState(
        targetValue = if (isExpanded) 180.dp else 80.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "Circle Size Animation"
    )
    val offset by animateDpAsState(
        targetValue = if (isExpanded) 100.dp else (-50).dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "Offset Animation"
    )
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        color = Color(0xFFE0E0E0)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { isExpanded = !isExpanded },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03DAC5),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(text = if (isExpanded) "Resetear Movimiento" else "Expandir y Mover")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .offset(x = offset, y = offset)
                    .size(circleDiameter)
                    .background(Color(0xFFFFC107), shape = CircleShape)
            )
        }
    }
}



