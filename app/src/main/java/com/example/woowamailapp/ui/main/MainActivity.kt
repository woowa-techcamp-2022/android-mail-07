package com.example.woowamailapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.core.view.get
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.ActivityMainBinding
import com.example.woowamailapp.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigationView(binding.bnMain)
        initUser()


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
    }
    private fun initUser(){
        mainViewModel.initUser(User(
            nickname = intent.getStringExtra("nickname") ?: "testName",
            email = intent.getStringExtra("email") ?: "testEmail"
        ))

    }


}