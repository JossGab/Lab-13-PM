package com.example.lab13

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
@Composable
fun ColorChangeDemo() {
    var isBlue by remember { mutableStateOf(true) }

    // Animar el color de fondo del cuadro
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color(0xFF2196F3) else Color(0xFF4CAF50),
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color(0xFFF5F5F5)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { isBlue = !isBlue },
                colors = ButtonDefaults.run {
                    val buttonColors = buttonColors(
                        Color(0xFF6200EE), Color.White
                    )
                    buttonColors
                },
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Cambiar color")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor)
            )
        }
    }
}
