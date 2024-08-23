package com.example.koala_game

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.koala_game.databinding.ActivityHardBinding
import kotlin.random.Random

class Hard : AppCompatActivity() {

    private lateinit var binding: ActivityHardBinding
    private lateinit var timer: CountDownTimer
    private var score = 0
    private var timeLeft = 20000L // 20 seconds
    private var imageViews: List<ImageView> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageViews = listOf(
            binding.imageView1, binding.imageView2, binding.imageView3,
            binding.imageView4, binding.imageView5, binding.imageView6,
            binding.imageView7, binding.imageView8, binding.imageView9,
            binding.imageView10, binding.imageView11, binding.imageView12
        )

        binding.restartid.setOnClickListener { restartGame() }
        binding.button4.setOnClickListener { goToMenu() }

        startGame()
    }

    private fun startGame() {
        score = 0
        timeLeft = 20000L
        updateScore()
        startTimer()
        showRandomImage()
    }

    private fun restartGame() {
        timer.cancel()
        startGame()
    }

    private fun goToMenu() {
        timer.cancel()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeft, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                binding.timeid.text = "TIMER : ${timeLeft / 1000}"
                showRandomImage()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.timeid.text = "TIME'S UP!"
                disableImageClicks()
            }
        }.start()
    }

    private fun showRandomImage() {
        imageViews.forEach { it.setImageResource(0) } // Clear all images
        val randomIndex = Random.nextInt(imageViews.size)
        val selectedImageView = imageViews[randomIndex]
        selectedImageView.setImageResource(R.drawable.sample3)
        selectedImageView.setOnClickListener { increaseScore() }

        imageViews.forEach { imageView ->
            imageView.setOnClickListener(null)
        }

        selectedImageView.setOnClickListener {
            increaseScore()
            showRandomImage()
        }
    }

    private fun increaseScore() {
        score += 1
        updateScore()
        showRandomImage()
    }

    @SuppressLint("SetTextI18n")
    private fun updateScore() {
        binding.scoreid.text = "SCORE : $score"
    }

    private fun disableImageClicks() {
        imageViews.forEach { it.setOnClickListener(null) }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
