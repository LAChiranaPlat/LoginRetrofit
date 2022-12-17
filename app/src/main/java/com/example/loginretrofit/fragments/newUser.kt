package com.example.loginretrofit.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.DialogFragment
import com.example.loginretrofit.R
import com.example.loginretrofit.databinding.FragmentNewUserBinding
import com.example.loginretrofit.myRetrofit.InterfaceRetroft
import com.example.loginretrofit.myRetrofit.dtLogin
import com.example.loginretrofit.myRetrofit.dtResponse
import com.example.loginretrofit.myRetrofit.myRetroft
import com.example.loginretrofit.utilities.myFragments
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class newUser : myFragments() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val views=FragmentNewUserBinding.inflate(inflater,container,false)

        views.apply {

            btnRegistrar.setOnClickListener {

                var nick=""
                var key=""

                if(verify(ctUi).equals(false))
                {
                    Toast.makeText(requireContext(),"Error, se detecto un vacio",Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                var largo=tilNick.str().length

                if(largo in 6..12) {

                    nick=tilNick.str()

                }else{
                    Toast.makeText(requireContext(),"Error, su cuenta debe tener entre 6 y 12 caracteres",Toast.LENGTH_LONG).show()
                }


                var largoKey=tilNKey.str().length

                if(largoKey in 6..12) {

                    if(!tilNKey.str().equals(tilNRKey.str())){
                        Toast.makeText(requireContext(),"Las claves no coinciden, intente otra vez",Toast.LENGTH_LONG).show()
                        return@setOnClickListener
                    }

                    key=tilNKey.str()

                }else{
                    Toast.makeText(requireContext(),"Error, su cuenta debe tener entre 6 y 12 caracteres",Toast.LENGTH_LONG).show()
                }

                CoroutineScope(Dispatchers.IO).launch {

                    var respuesta: Response<dtResponse> = myRetroft.retrofit().create(
                        InterfaceRetroft::class.java).registrar(
                        dtLogin(nick,key,tilNombres.str(),tilApellidos.str(),"U")
                    )

                    var data=respuesta.body()

                    Log.i("result",respuesta.toString())
                    Log.i("result",data.toString())

                    activity?.runOnUiThread{

                        if(respuesta.isSuccessful){

                            MaterialAlertDialogBuilder(it.context)
                                .setTitle("Registro Satisfactorio")
                                .setMessage("Registrado correctamente")
                                .setPositiveButton("Ok"){x,y->

                                }
                                .setNegativeButton("Salir"){x,y->

                                }
                                .show()
                        }

                    }

                }


                /*cuenta de usuario tenga entre 6 y 12 caracteres*/

                                                                    /*cuenta de usuario no exista*/
                /*las 2 claves coincidan(iguales)*/
                /*clave de usuario tenga entre 6 y 12 caracteres*/

            }

            btnSalir.setOnClickListener {
                dismiss()
            }

        }

        return views.root

    }


}