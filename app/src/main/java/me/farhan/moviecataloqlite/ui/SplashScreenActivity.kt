package me.farhan.moviecataloqlite.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import me.farhan.moviecataloqlite.R
import me.farhan.moviecataloqlite.helper.SPLASH_SCREEN_TIMEOUT
import me.farhan.moviecataloqlite.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }
}