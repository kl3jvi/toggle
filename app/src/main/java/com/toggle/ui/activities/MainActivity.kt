package com.toggle.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.toggle.R
import com.toggle.databinding.ActivityMainBinding
import com.toggle.services.MyLogWriter
import com.toggle.utils.hide
import com.toggle.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    internal object g {
        lateinit var logWriter: MyLogWriter
    }

//    @Inject
//    lateinit var endpoint: Endpoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
//        initLibrary()
    }

    fun hideBottomNavBar() {
        binding.navView.animate().translationY(binding.navView.height.toFloat()).run {
            duration = 500
            binding.navView.hide()
        }
    }

    fun showBottomNavBar() {
        binding.navView.show()
        binding.navView.animate().translationY(0f).duration = 500
    }

}