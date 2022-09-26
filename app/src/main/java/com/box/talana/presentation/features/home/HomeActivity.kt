package com.box.talana.presentation.features.home

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.box.talana.R
import com.box.talana.core.extension.dataBinding
import com.box.talana.databinding.ActivityHomeBinding
import com.box.talana.presentation.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mobile_navigation) as NavHostFragment
        val navController = navHostFragment.navController

        //val navController = findNavController(R.id.mobile_navigation)
        //supportFragmentManager.findFragmentById(R.id.mobile_navigation) as NavHostFragment
        navView.setupWithNavController(navController)
    }

    override val binding: ActivityHomeBinding by dataBinding(ActivityHomeBinding::inflate)
}
