package com.example.woowamailapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.woowamailapp.R
import com.example.woowamailapp.databinding.FragmentMailBinding
import com.example.woowamailapp.model.Mail
import com.example.woowamailapp.ui.adapter.MailAdapter
import com.example.woowamailapp.utils.PRIMARY
import com.example.woowamailapp.utils.PROMOTION
import com.example.woowamailapp.utils.SOCIAL

class MailFragment : Fragment() {
    private var _binding : FragmentMailBinding? = null
    private val binding get() = _binding!!
    private val mailAdapter = MailAdapter()
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mail,container,false)
        binding.rvMail.adapter = mailAdapter

        viewModel.mails.observe(viewLifecycleOwner) {
            mailAdapter.submitList(it.toList())
        }

        viewModel.type.observe(viewLifecycleOwner, Observer { currentType ->
            binding.tvTitle.apply {
                this.text =
                    when(currentType){
                        PRIMARY -> getString(R.string.drawer_menu_primary)
                        SOCIAL -> getString(R.string.drawer_menu_social)
                        else -> getString(R.string.drawer_menu_promotion)
                    }
            }

        })

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}