package com.streamgo.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                Scaffold(containerColor = Color(0xFF0F0F0F)) { padding ->
                    Column(Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
                        Box(Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black), contentAlignment = Alignment.Center) {
                            Text("Preview HDMI 1080p60\nConecte a placa via Hub USB-C com alimentação", color = Color.White, textAlign = TextAlign.Center)
                            // Aqui entra o TextureView do UVC + Compositor OpenGL
                            Box(Modifier.align(Alignment.TopStart).padding(8.dp).background(Color.Black.copy(0.6f), RoundedCornerShape(8.dp)).padding(8.dp)) {
                                Text("● 1080p60 | 4500 kbps | 60 FPS | 38°C", color = Color.Green)
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(onClick = { startForegroundService(intent) }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)), modifier = Modifier.weight(1f).height(56.dp)) {
                                Text(getString(R.string.iniciar_transmissao), fontWeight = FontWeight.Bold)
                            }
                            IconButton(onClick = {}, Modifier.size(56.dp).background(Color(0xFF212121), CircleShape)) { Icon(Icons.Filled.Mic, null, tint = Color.White) }
                            IconButton(onClick = {}, Modifier.size(56.dp).background(Color(0xFF212121), CircleShape)) { Icon(Icons.Filled.Chat, null, tint = Color.White) }
                        }
                    }
                }
            }
        }
    }
}
