package com.example.task3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val distance = intent.getStringExtra("distance") ?: "Неизвестно"
        val sport = intent.getStringExtra("sport") ?: "Нет описания"
        val time = intent.getStringExtra("time") ?: "Неизвестно"
        val date = intent.getStringExtra("date") ?: "Неизвестно"
        val user_nick = intent.getStringExtra("user_nick") ?: ""
        val position = intent.getIntExtra("position", -1) // Позиция задачи

        findViewById<TextView>(R.id.detailDistance).text = distance
        findViewById<TextView>(R.id.detailTime).text = time
        findViewById<TextView>(R.id.detailDate).text = date
        findViewById<TextView>(R.id.detailSport).text = sport
        val delete_button = findViewById<ImageView>(R.id.deleteButton)
        val share_button = findViewById<ImageView>(R.id.shareButton)
        if (user_nick.isNotEmpty()){
            delete_button.visibility = View.GONE
            share_button.visibility=View.GONE
        }


        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("position", position)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }


}