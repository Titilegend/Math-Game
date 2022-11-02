package com.example.mathgameforkids

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var resultScore: TextView

    lateinit var playAgain: Button
    lateinit var exit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

    resultScore = findViewById(R.id.resultScoreText)

    playAgain= findViewById(R.id.playAgainButon)
    exit = findViewById(R.id.exitButton)

    val score = intent.getIntExtra("score",0)
    resultScore.text = "Your Score: "+ score

    playAgain.setOnClickListener {
        val intent = Intent(this@ResultActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    exit.setOnClickListener {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    }
}