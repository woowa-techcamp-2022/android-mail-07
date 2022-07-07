package com.example.woowamailapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.FragmentMailBinding
import com.example.woowamailapp.databinding.FragmentSettingBinding
import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.ui.adapter.MailAdapter

class SettingFragment : Fragment() {
    private lateinit var binding : FragmentSettingBinding
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting,container,false)

        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            binding.tvEmail.text = user.email
            binding.tvNickname.text = user.nickname
        })


        return binding.root
    }


}