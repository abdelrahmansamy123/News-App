package com.route.news_app.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.route.news_app.R
import com.route.news_app.main.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper())
            .postDelayed({
                startMainActivity();
            }, 2000)
    }
    fun startMainActivity() {
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}