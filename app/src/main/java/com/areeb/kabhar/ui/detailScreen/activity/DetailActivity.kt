package com.areeb.kabhar.ui.detailScreen.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.areeb.kabhar.R
import com.areeb.kabhar.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val getData = intent.getStringExtra(Constants.String_CONSTANTS.WEB_VIEW_URL)
        val bundle = Bundle().apply {
            putString(Constants.String_CONSTANTS.WEB_VIEW_URL, getData)
        }
        val navHost =
            supportFragmentManager.findFragmentById(R.id.detailFragmentContainerView) as NavHostFragment
        val navController = navHost.navController
        val navGraph = navController.navInflater.inflate(R.navigation.detail_nav)
        navController.setGraph(navGraph, bundle)
    }
}
