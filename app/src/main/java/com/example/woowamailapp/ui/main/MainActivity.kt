package com.example.woowamailapp.ui.main

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
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
    private var displaySize = 0
    private var currentTab = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDisplaySize(context = this)

        initUser()
        initDrawerLayout(binding.nvMain)

        binding.tbMain.setNavigationOnClickListener {
            binding.dlMain.openDrawer(GravityCompat.START)
        }
        if(savedInstanceState != null){
            currentTab = savedInstanceState.getInt(CURRENT_TAB)
        }
        else{
            currentTab = MAIL
        }

        navigateToCurrentTab()
    }
    private fun navigateToCurrentTab(){
        when(currentTab){
            MAIL -> {
                if(displaySize >= 600){
                    binding.nrMain?.selectedItemId = R.id.rail_mail
                }
                else {
                    binding.bnMain?.selectedItemId = R.id.bottom_mail
                }
            }
            SETTING -> {
                if(displaySize >= 600){
                    binding.nrMain?.selectedItemId = R.id.rail_setting
                }
                else {
                    binding.bnMain?.selectedItemId = R.id.bottom_setting
                }
            }
        }
    }
    private fun getDisplaySize(context: Context) {
        displaySize = getScreenWidth(context)
        if(displaySize >= 600){
            initNavigationRail(binding.nrMain)
        }
        else {
            initBottomNavigationView(binding.bnMain)
        }
    }
    private fun initBottomNavigationView(bottomNavigationView: BottomNavigationView?){
        bottomNavigationView?.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_mail -> {
                    changeFragmentAndSetCurrentTab(MAIL)
                }
                R.id.bottom_setting -> {
                    changeFragmentAndSetCurrentTab(SETTING)
                }
            }
            true
        }
    }
    private fun initNavigationRail(navigationRailView: NavigationRailView?){
        navigationRailView?.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.rail_mail -> {
                    changeFragmentAndSetCurrentTab(MAIL)
                }
                R.id.rail_setting -> {
                    changeFragmentAndSetCurrentTab(SETTING)
                }
            }
            true
        }
    }
    private fun changeFragmentAndSetCurrentTab(tab: Int){
        when(tab){
            MAIL -> {
                currentTab = MAIL
                supportFragmentManager.beginTransaction().replace(binding.fcMain.id,MailFragment()).commit()
                binding.tbMain.visibility = View.VISIBLE            }
            SETTING -> {
                currentTab = SETTING
                mainViewModel.selectType(PRIMARY)
                supportFragmentManager.beginTransaction().replace(binding.fcMain.id,SettingFragment()).commit()
                binding.tbMain.visibility = View.GONE
            }
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
                    }
                    R.id.nav_social -> {
                        this.selectType(SOCIAL)
                    }
                    else -> {
                        this.selectType(PROMOTION)
                    }
                }
                item.isChecked = true
            }
            binding.dlMain.closeDrawer(GravityCompat.START)
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
            windowManager.defaultDisplay.getMetrics(displayMetrics) // ***
            (displayMetrics.widthPixels / density).toInt()
        }
    }

    override fun onBackPressed() {
        if(binding.bnMain?.isVisible == true && binding.bnMain?.selectedItemId == R.id.bottom_setting){
            binding.bnMain?.selectedItemId = R.id.bottom_mail
        }
        else if(binding.nrMain?.isVisible == true && binding.nrMain?.selectedItemId == R.id.rail_setting){
            binding.nrMain?.selectedItemId = R.id.rail_mail
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_TAB,currentTab)
    }
}