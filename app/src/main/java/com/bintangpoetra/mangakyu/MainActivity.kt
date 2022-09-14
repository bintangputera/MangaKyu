package com.bintangpoetra.mangakyu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bintangpoetra.mangakyu.databinding.ActivityMainBinding
import com.bintangpoetra.mangakyu.utils.ext.gone
import com.bintangpoetra.mangakyu.utils.ext.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var _activityMainBinding: ActivityMainBinding
    private val binding get() = _activityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMainBinding.root)

        initUI()
    }

    private fun initUI() {
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navHostBottom = supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment
        val navControllerBottom = navHostBottom.navController
        navControllerBottom.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                binding.btmNav.show()
            } else {
                binding.btmNav.gone()
            }
        }

        binding.btmNav.apply {
            background = null
            setupWithNavController(navControllerBottom)
        }
    }

}