package com.example.loginretrofit.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.loginretrofit.R
import com.example.loginretrofit.System
import com.example.loginretrofit.databinding.FragmentRecuperacionBinding
import com.example.loginretrofit.myRetrofit.InterfaceRetroft
import com.example.loginretrofit.myRetrofit.dtLogin
import com.example.loginretrofit.myRetrofit.dtResponse
import com.example.loginretrofit.myRetrofit.myRetroft
import com.example.loginretrofit.utilities.myFragments
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class recuperacion : myFragments() {

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

            btnUpdateClave.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {

                    var respuesta: Response<dtResponse> = myRetroft.retrofit().create(
                        InterfaceRetroft::class.java).verify(
                        dtLogin(tilAccount.str())
                    )

                    var data=respuesta.body()

                    Log.i("result",respuesta.toString())
                    Log.i("result",data.toString())

                    activity?.runOnUiThread{

                        if(respuesta.isSuccessful){

                            if(data?.status.equals("OK")){

                               var changeKey=changeKey()

                                var pack=Bundle()
                                pack.putString("user",tilAccount.str())

                                changeKey.arguments=pack

                               changeKey.show(parentFragmentManager.beginTransaction(),"x")

                                return@runOnUiThread
                            }

                            MaterialAlertDialogBuilder(requireContext())
                                .setTitle("Usuario no Encontrado")
                                .setMessage(data?.message.toString())
                                .setPositiveButton("Entiendo"){x,y->
                                    tilAccount.editText?.text!!.clear()
                                    tilAccount.editText?.requestFocus()

                                }.show()

                        }

                    }

                }
            }

        }

        return views.root
    }

}