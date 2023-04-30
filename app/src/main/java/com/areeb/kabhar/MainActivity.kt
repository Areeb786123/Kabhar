package com.areeb.kabhar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.areeb.kabhar.databinding.ActivityMainBinding
import com.areeb.kabhar.ui.home.fragment.HomeFragment
import com.areeb.kabhar.ui.saved.SavedFragment
import com.areeb.kabhar.ui.search.fragment.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
        init()
    }

    private fun init() {
        replaceFragment(HomeFragment())
        settingBottomNavigation()
    }

    private fun settingBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.searchFragment -> replaceFragment(SearchFragment())
                R.id.savedFragment -> replaceFragment(SavedFragment())
            }
            true
        }
    }

    @SuppressLint("CommitTransaction")
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, fragment).commit()
    }
}
