package com.example.loginretrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.loginretrofit.R
import com.example.loginretrofit.databinding.FragmentRecuperacionBinding

class recuperacion : DialogFragment() {

    lateinit var views:FragmentRecuperacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        views= FragmentRecuperacionBinding.inflate(inflater,container,false)

        views.apply {

            imgClose.setOnClickListener {
                dismiss()
            }

        }

        return views.root
    }

}