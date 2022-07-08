package com.example.woowamailapp.ui.main

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.core.view.isVisible
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.ActivityMainBinding
import com.example.woowamailapp.model.User
import com.example.woowamailapp.utils.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUser()
        initDrawerLayout(binding.nvMain)

        binding.tbMain.setNavigationOnClickListener {
            binding.dlMain.openDrawer(Gravity.LEFT)
        }
        getScreenWidth(this).apply {
            if(this >= 600){
                binding.nrMain.visibility = View.VISIBLE
                binding.bnMain.visibility = View.GONE
                initNavigationRail(binding.nrMain)
                binding.nrMain.selectedItemId =
                    if(mainViewModel.tab == MAIL) R.id.mail else R.id.setting
            }else {
                binding.nrMain.visibility = View.GONE
                binding.bnMain.visibility = View.VISIBLE
                initBottomNavigationView(binding.bnMain)
                binding.bnMain.selectedItemId =
                    if(mainViewModel.tab == MAIL) R.id.mail else R.id.setting
            }
        }
    }

    private fun initBottomNavigationView(bottomNavigationView: BottomNavigationView){
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.mail -> {
                    mainViewModel.selectTab(MAIL)
                    supportFragmentManager.beginTransaction().replace(R.id.fc_main,MailFragment()).commit()
                    binding.tbMain.visibility = View.VISIBLE
                }
                R.id.setting -> {
                    mainViewModel.selectType(PRIMARY)
                    mainViewModel.selectTab(SETTING)
                    supportFragmentManager.beginTransaction().replace(R.id.fc_main,SettingFragment()).commit()
                    binding.tbMain.visibility = View.GONE
                }
            }
            true
        }
    }
    private fun initNavigationRail(navigationRailView: NavigationRailView){
        navigationRailView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.mail -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fc_main,MailFragment()).commit()
                    mainViewModel.selectTab(MAIL)
                    binding.tbMain.visibility = View.VISIBLE
                }
                R.id.setting -> {
                    mainViewModel.selectType(PRIMARY)
                    mainViewModel.selectTab(SETTING)
                    supportFragmentManager.beginTransaction().replace(R.id.fc_main,SettingFragment()).commit()
                    binding.tbMain.visibility = View.GONE
                }
            }
            true
        }
    }
    private fun initUser(){
        mainViewModel.initUser(User(
            nickname = intent.getStringExtra("nickname") ?: "testName",
            email = intent.getStringExtra("email") ?: "testEmail"
        ))
    }
    private fun initDrawerLayout(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { item->
            mainViewModel.apply {
                when(item.itemId){
                    R.id.nav_primary -> {
                        this.selectType(PRIMARY)
                        item.isChecked = true
                    }
                    R.id.nav_social -> {
                        this.selectType(SOCIAL)
                        item.isChecked = true
                    }
                    else -> {
                        this.selectType(PROMOTION)
                        item.isChecked = true
                    }
                }

            }
            binding.dlMain.closeDrawer(Gravity.LEFT)
            true
        }
    }

    private fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val density = resources.displayMetrics.density
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            ((windowMetrics.bounds.width() - insets.left - insets.right ) / density).toInt()
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            (displayMetrics.widthPixels / density).toInt()
        }
    }

    override fun onBackPressed() {
        if(binding.bnMain.isVisible && binding.bnMain.selectedItemId == R.id.setting){
            binding.bnMain.selectedItemId = R.id.mail
        }
        else if(binding.nrMain.isVisible && binding.nrMain.selectedItemId == R.id.setting){
            binding.nrMain.selectedItemId = R.id.mail
        }
        else {
            if(mainViewModel.isPrimaryTypeNow()){
                super.onBackPressed()
            }
            else {
                mainViewModel.selectType(PRIMARY)
            }
        }
    }
}