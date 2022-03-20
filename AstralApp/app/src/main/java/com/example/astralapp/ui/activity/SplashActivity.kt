package com.example.astralapp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.astralapp.MainActivity
import com.example.astralapp.R
import com.example.astralapp.databinding.ActivitySplashBinding
import kotlin.math.hypot

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                v.removeOnLayoutChangeListener(this)
                animateView()
            }
        })
        applyFontGradient()
    }

    private fun applyFontGradient() {
        val paint = binding.textView.paint
        val width = paint.measureText(binding.textView.text.toString())
        val textShader: Shader = LinearGradient(
            0f,
            0f,
            width,
            binding.textView.textSize, intArrayOf(
                Color.parseColor(getString(R.string.gradient_start)),
                Color.parseColor(getString(R.string.gradient_mid)),
                Color.parseColor(getString(R.string.gradient_end))
            ),
            null,
            Shader.TileMode.REPEAT
        )

        binding.textView.paint.shader = textShader
    }

    private fun animateView() {
        with(binding) {
            val cx = logoImageView.width / 2
            val cy = logoImageView.height / 2
            val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(
                logoImageView,
                cx,
                cy,
                0f, finalRadius
            )
            logoImageView.visibility = View.VISIBLE
            anim.duration = 2000
            anim.doOnEnd {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
            anim.start()

        }
    }
}