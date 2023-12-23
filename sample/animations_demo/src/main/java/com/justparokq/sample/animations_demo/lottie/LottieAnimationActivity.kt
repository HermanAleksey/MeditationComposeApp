package com.justparokq.sample.animations_demo.lottie

import android.animation.Animator
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.justparokq.sample.animations_demo.R

class LottieAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation)
        val lottieView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
        findViewById<Button>(R.id.lottie_playAnimation).let { playButton ->
            playButton.setOnClickListener {
                lottieView.playAnimation()
            }
        }
        findViewById<Button>(R.id.lottie_pauseAnimation).let { stopButton ->
            stopButton.setOnClickListener {
                lottieView.pauseAnimation()
            }
        }
        findViewById<Button>(R.id.lottie_cancelAnimation).let { stopButton ->
            stopButton.setOnClickListener {
                lottieView.cancelAnimation()
            }
        }
        val textView = findViewById<TextView>(R.id.lottie_status_textView)


        lottieView.addAnimatorListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    textView.text = "onAnimationStart"
                }

                override fun onAnimationEnd(animation: Animator) {
                    textView.text = "onAnimationEnd"
                }

                override fun onAnimationCancel(animation: Animator) {
                    textView.text = "onAnimationCancel"
                }

                override fun onAnimationRepeat(animation: Animator) {
                    textView.text = "onAnimationRepeat"
                }
            }
        )
    }
}