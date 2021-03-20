package me.farhan.moviecataloqlite.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import me.farhan.moviecataloqlite.R

/**
 * @author farhan
 * created at at 10:09 on 03/03/21.
 */
class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnBack = findViewById<ImageView>(R.id.imageView_back)
        btnBack.setOnClickListener { onBackPressed() }
    }
}