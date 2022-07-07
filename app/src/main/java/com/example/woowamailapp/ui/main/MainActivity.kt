package com.example.woowamailapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.ActivityMainBinding
import com.example.woowamailapp.model.User
import com.example.woowamailapp.utils.PRIMARY
import com.example.woowamailapp.utils.PROMOTION
import com.example.woowamailapp.utils.SOCIAL
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationView(binding.bnMain)
        initUser()
        initDrawerLayout(binding.nvMain)

        binding.tbMain.setOnClickListener {
            binding.dlMain.openDrawer(Gravity.LEFT)
        }
    }

    private fun initBottomNavigationView(bottomNavigationView: BottomNavigationView){
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.mail -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fc_main,MailFragment()).commit()
                }
                else -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fc_main,SettingFragment()).commit()
                }
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.mail
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
                this.selectType(
                    when(item.itemId){
                        R.id.nav_primary -> PRIMARY
                        R.id.nav_social -> SOCIAL
                        else -> PROMOTION
                    }
                )
            }
            binding.dlMain.closeDrawer(Gravity.LEFT)
            true
        }
    }

}