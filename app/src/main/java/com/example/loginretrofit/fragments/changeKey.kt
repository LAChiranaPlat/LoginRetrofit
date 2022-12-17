package com.example.loginretrofit.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.loginretrofit.R
import com.example.loginretrofit.databinding.FragmentChangeKeyBinding
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


class changeKey : myFragments() {

    var user=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user= arguments?.get("user").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout=FragmentChangeKeyBinding.inflate(inflater,container,false)

        layout.apply {
            txtCaption.append(" $user")

            btnUpdate.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {

                    var respuesta: Response<dtResponse> = myRetroft.retrofit().create(
                        InterfaceRetroft::class.java).upKey(
                        dtLogin(user,tilUpdateKey.editText?.text.toString())
                    )

                    var data=respuesta.body()

                    Log.i("result",respuesta.toString())
                    Log.i("result",data.toString())

                    activity?.runOnUiThread{

                        if(respuesta.isSuccessful){

                            if(data?.status.equals("OK")){

                                dismiss()

                            }


                        }

                    }

                }
            }
        }

        return layout.root
    }

}