package pl.artur.gpsspeedwidget

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : Activity() {
    private val requestCode = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this).apply {
            text = "GPS Speed Widget\n\nDodaj widget do Nova, a następnie uruchom tę aplikację raz, aby włączyć odczyt GPS."
            textSize = 18f
            setPadding(40, 60, 40, 60)
        }
        setContentView(text)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                requestCode
            )
        } else {
            startSpeedService()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, results: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, results)
        if (requestCode == this.requestCode &&
            results.isNotEmpty() && results[0] == PackageManager.PERMISSION_GRANTED) {
            startSpeedService()
        }
    }

    private fun startSpeedService() {
        val intent = Intent(this, SpeedService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }
}
