package com.example.woowamailapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.FragmentMailBinding
import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.ui.adapter.MailAdapter

class MailFragment : Fragment() {
    private lateinit var binding : FragmentMailBinding
    private val mailAdapter = MailAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mail,container,false)
        binding.rvMail.adapter = mailAdapter

        mailAdapter.submitList(listOf(Mail(1,"asd","asd","asd","asd"),
            Mail(2,"asd","asd","asd","asd"),
            Mail(3,"asd","asd","asd","asd")))
        return binding.root
    }


}