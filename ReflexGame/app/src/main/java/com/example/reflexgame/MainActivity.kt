package com.example.reflexgame

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var scoreText: TextView
    private lateinit var hitButton: Button
    private var score = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreText = findViewById(R.id.scoreText)
        hitButton = findViewById(R.id.hitButton)

        hitButton.setOnClickListener {
            score++
            scoreText.text = "Score: $score"
            moveButton()
        }

        delayButton()
    }

    private fun delayButton() {
        hitButton.visibility = View.INVISIBLE
        handler.postDelayed({
            hitButton.post {
                moveButton()
                hitButton.visibility = View.VISIBLE
            }
        }, 1000)
    }

    private fun moveButton() {
        val parent = hitButton.parent as View
        val maxX = parent.width - hitButton.width
        val maxY = parent.height - hitButton.height
        if (maxX > 0 && maxY > 0) {
            hitButton.translationX = Random.nextInt(maxX).toFloat()
            hitButton.translationY = Random.nextInt(maxY).toFloat()
        }
    }
}
