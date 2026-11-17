package com.streamgo.app
import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class StreamingForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val channelId = "streamgo_channel"
        val channel = NotificationChannel(channelId, "StreamGo Transmissão", NotificationManager.IMPORTANCE_LOW)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(getString(R.string.notificacao_titulo))
            .setContentText(getString(R.string.notificacao_texto))
            .setSmallIcon(android.R.drawable.presence_video_online)
            .setOngoing(true).build()
        startForeground(1, notification)
        // AQUI INICIA O PIPELINE: UVC -> OpenGL FBO -> MediaCodec Surface -> RTMP
        return START_STICKY
    }
}
