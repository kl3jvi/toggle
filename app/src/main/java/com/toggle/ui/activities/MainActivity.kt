package com.toggle.ui.activities

import android.os.Bundle
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
import org.pjsip.pjsua2.Endpoint
import org.pjsip.pjsua2.EpConfig
import org.pjsip.pjsua2.pj_log_decoration
import org.pjsip.pjsua2.pjsua_state
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    internal object g {
        lateinit var logWriter: MyLogWriter
    }

    @Inject
    lateinit var endpoint: Endpoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        initLibrary()
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


    private fun initLibrary() {
        if (::endpoint.isInitialized) {
            if (endpoint.libGetState() > pjsua_state.PJSUA_STATE_NULL)
                return
            val epConfig = EpConfig()

            /* Setup our log writer */
            val logCfg = epConfig.logConfig
            g.logWriter = MyLogWriter()
            logCfg.writer = g.logWriter
            logCfg.decor = logCfg.decor and
                    (pj_log_decoration.PJ_LOG_HAS_CR or
                            pj_log_decoration.PJ_LOG_HAS_NEWLINE).inv().toLong()

            /* Create & init PJSUA2 */
            try {
                endpoint.libCreate()
                endpoint.libInit(epConfig)
            } catch (e: Exception) {
                println(e)
            }


            /* Start PJSUA2 */
            try {
                endpoint.libStart()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}