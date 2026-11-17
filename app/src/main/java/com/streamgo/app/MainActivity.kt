package com.streamgo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: StreamingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StreamGoTheme {
                val estado by viewModel.estado.collectAsState()
                Scaffold(containerColor = Color(0xFF0F0F0F)) { padding ->
                    Column(modifier = Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
                        // PREVIEW - Compositor OpenGL
                        Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.Black)) {
                            AndroidView(factory = { viewModel.getPreviewView(it) }, modifier = Modifier.fillMaxSize())
                            if(!estado.cameraConectada) {
                                Box(Modifier.fillMaxSize().background(Color.Black.copy(0.7f)), contentAlignment = Alignment.Center) {
                                    Text("Nenhuma placa de captura detectada.\nConecte via Hub USB-C com alimentação.", color = Color.White, textAlign = TextAlign.Center)
                                }
                            }
                            // OVERLAY WEBCAM + WIDGETS são desenhados no GL FBO antes do encoding
                            MonitoringOverlay(estado = estado, modifier = Modifier.align(Alignment.TopStart))
                        }
                        Spacer(Modifier.height(16.dp))
                        // CONTROLES
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            Button(
                                onClick = { viewModel.toggleStream() },
                                colors = ButtonDefaults.buttonColors(containerColor = if(estado.aoVivo) Color(0xFFD32F2F) else Color(0xFF00C853)),
                                modifier = Modifier.weight(1f).height(56.dp)
                            ) { Text(if(estado.aoVivo) stringResource(R.string.parar_transmissao) else stringResource(R.string.iniciar_transmissao), fontWeight = FontWeight.Bold) }
                            IconButton(onClick = { /* Abrir Mixer */ }, modifier = Modifier.size(56.dp).background(Color(0xFF212121), CircleShape)) { Icon(Icons.Filled.Mic, null, tint = Color.White) }
                            IconButton(onClick = { /* Abrir Chat */ }, modifier = Modifier.size(56.dp).background(Color(0xFF212121), CircleShape)) { Icon(Icons.Filled.Chat, null, tint = Color.White) }
                        }
                        // MONITORAMENTO TÉCNICO
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top=12.dp)) {
                            item { StatChip("1080p60" , estado.resolucao) }
                            item { StatChip(String.format("%.0f kbps", estado.bitrate), if(estado.bitrate < 2500) Color.Yellow else Color.Green) }
                            item { StatChip("${estado.fps} FPS", if(estado.fps < 55) Color.Red else Color.Green) }
                            item { StatChip("${estado.temperatura}°C", if(estado.temperatura > 42) Color.Red else Color.White) }
                        }
                    }
                }
            }
        }
    }
}
