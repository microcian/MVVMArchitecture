package com.abe.boilerplatemvvm.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.abe.boilerplatemvvm.databinding.ActivitySplashBinding
import com.abe.boilerplatemvvm.view.main.MainActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 1000)
    }
}