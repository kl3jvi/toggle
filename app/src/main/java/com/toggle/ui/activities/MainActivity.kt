package com.toggle.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.toggle.databinding.ActivityMainBinding
import com.toggle.utils.hide
import com.toggle.utils.show

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun hideBottomNavBar() {
        binding.navView.animate().translationY(binding.navView.height.toFloat()).duration = 500
        binding.navView.hide()
    }

    fun showBottomNavBar() {
        binding.navView.animate().translationY(0f).duration = 500
        binding.navView.show()
    }
}