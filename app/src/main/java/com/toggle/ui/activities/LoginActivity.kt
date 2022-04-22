package com.toggle.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.toggle.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test()
    }

    fun test() = runBlocking {
        launch {
            delay(1L)
            Log.e("------", "B")
        }
        Log.e("------", "A")

    }

    val x = null
    val l = listOf(x)

    override fun setFinishOnTouchOutside(finish: Boolean) {
        super.setFinishOnTouchOutside(finish)
    }


}