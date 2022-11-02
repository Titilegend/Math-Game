package com.example.mathgameforkids

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class DivisionActivity : AppCompatActivity() {
    lateinit var score: TextView
    lateinit var life: TextView
    lateinit var time: TextView

    lateinit var questionBox: TextView
    lateinit var answerBox: EditText

    lateinit var okButton: Button
    lateinit var nextButton: Button


    var correctAnswer = 0
    var userScore = 0
    var userLife = 3
    //create an object of the CountDownTimer Class
    lateinit var timer: CountDownTimer
    private val startTimerInMillis: Long= 20000
    var timeLeftInMillis: Long = startTimerInMillis
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)
        supportActionBar!!.title = "Division"

        score = findViewById(R.id.textView1)
        life = findViewById(R.id.textView)
        time = findViewById(R.id.textView8)
        questionBox = findViewById(R.id.textViewQuestion)
        answerBox = findViewById(R.id.editTextAnswer)
        okButton = findViewById(R.id.buttonOk)
        nextButton = findViewById(R.id.buttonNext)
        //the game continue function is called as the game starts
        gameContinue()

        okButton.setOnClickListener {
            //if the user clicks ok without entering an answer
            val input = answerBox.text.toString()
            if(input == " "){
                Toast.makeText(applicationContext,"Please enter an answer or click the next button",
                    Toast.LENGTH_LONG).show()
            }
            else{
                pauseTimer()
                val userAnswer = input.toInt()

                //if the user types in the correct number
                if(userAnswer == correctAnswer){
                    userScore += 10
                    questionBox.text= "Correct!"
                    questionBox.setTextColor(Color.GREEN)
                    score.text = userScore.toString()

                }
                else{
                    userLife--
                    questionBox.text= "Incorrect!"
                    questionBox.setTextColor(Color.RED)
                    life.text = userLife.toString()
                }
            }
        }
        nextButton.setOnClickListener {
            pauseTimer()
            resetTimer()
            gameContinue()
            questionBox.setTextColor(Color.BLUE)
            answerBox.setText("")
            //if the lives left is 0,it takes you to the result page
            if(userLife == 0){
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@DivisionActivity,ResultActivity::class.java)
                //to place the user score in the result score text view
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }
            else{
                gameContinue()
            }

        }


    }
    //create a function to generate the questions and game logic
    fun gameContinue(){
        val number1= Random.nextInt(0,100)
        val number2= Random.nextInt(0,100)

        //to write these numbers in the question text view
        questionBox.text = "$number1 / $number2"
        //correct answer
        correctAnswer = number1 / number2
        startTimer()
    }
    //function for the timer
    fun startTimer(){
        timer = object: CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinish: Long) {
                timeLeftInMillis = millisUntilFinish
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                life.text = userLife.toString()
                questionBox.text = "Sorry,Time is up!"
                questionBox.setTextColor(Color.RED)
            }

        }.start()
    }
    fun updateText(){
        val remainingTime:Int = (timeLeftInMillis/1000).toInt()
        time.text =String.format(Locale.getDefault(),"%02d",remainingTime)
    }

    fun pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){
        timeLeftInMillis = startTimerInMillis
        updateText()
    }

}
