package com.example.marvel.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                openMainActivity()
            },
            SPLASH_TIME
        )
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }

    companion object {
        private const val SPLASH_TIME = 1000L
    }
}
