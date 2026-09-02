package pl.artur.gpsspeedwidget

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

class SpeedService : Service() {
    private lateinit var locationManager: LocationManager

    private val listener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val speed = if (location.hasSpeed()) {
                (location.speed * 3.6f).roundToInt().coerceAtLeast(0)
            } else 0
            SpeedWidgetProvider.render(this@SpeedService, speed)
        }
    }

    override fun onCreate() {
        super.onCreate()
        createChannel()
        startForeground(1001, notification())
        locationManager = getSystemService(LocationManager::class.java)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            stopSelf()
            return
        }

        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                250L,
                0.5f,
                listener
            )
        } catch (_: SecurityException) {
            stopSelf()
        }
    }

    override fun onDestroy() {
        if (::locationManager.isInitialized) {
            locationManager.removeUpdates(listener)
        }
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                "speed",
                "GPS Speed",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun notification(): Notification {
        val open = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder(this, "speed")
                .setContentTitle("GPS Speed Widget")
                .setContentText("Odczyt prędkości GPS jest aktywny")
                .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                .setContentIntent(open)
                .setOngoing(true)
                .build()
        } else {
            Notification.Builder(this)
                .setContentTitle("GPS Speed Widget")
                .setContentText("Odczyt prędkości GPS jest aktywny")
                .setSmallIcon(android.R.drawable.ic_menu_mylocation)
                .setContentIntent(open)
                .setOngoing(true)
                .build()
        }
    }
}
