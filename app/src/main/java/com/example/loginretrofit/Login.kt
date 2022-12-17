package com.example.loginretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.loginretrofit.databinding.ActivityLoginBinding
import com.example.loginretrofit.fragments.newUser
import com.example.loginretrofit.fragments.recuperacion
import com.example.loginretrofit.myRetrofit.InterfaceRetroft
import com.example.loginretrofit.myRetrofit.dtLogin
import com.example.loginretrofit.myRetrofit.dtResponse
import com.example.loginretrofit.myRetrofit.myRetroft
import com.example.loginretrofit.utilities.myActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class Login : myActivity() {

    lateinit var views:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivityLoginBinding.inflate(layoutInflater)

        views.apply {

           btnLogin.setOnClickListener {

               if(verify(clContent)==false)
               {
                   MaterialAlertDialogBuilder(this@Login)
                       .setTitle("Error de Identificación")
                       .setMessage("Debe agregar sus credenciales para realizar el login")
                       .setPositiveButton("Ok"){x,y->

                           tilUser.on()

                       }
                       .show()

                   return@setOnClickListener
               }

               CoroutineScope(Dispatchers.IO).launch {

                   var respuesta: Response<dtResponse> = myRetroft.retrofit().create(InterfaceRetroft::class.java).login(
                       dtLogin(tilUser.str(),tilKey.str())
                   )

                   var data=respuesta.body()

                   Log.i("result",respuesta.toString())
                   Log.i("result",data.toString())

                    runOnUiThread{

                        if(respuesta.isSuccessful){

                            if(data?.status.equals("OK")){

                                startActivity(Intent(this@Login,System::class.java))
                                return@runOnUiThread
                            }

                            MaterialAlertDialogBuilder(this@Login)
                                .setTitle("Error en Login")
                                .setMessage(data?.message.toString())
                                .setPositiveButton("Entiendo"){x,y->
                                    tilUser.editText?.text!!.clear()
                                    tilKey.editText?.text!!.clear()

                                    tilUser.editText?.requestFocus()

                                }.show()

                        }

                    }

               }

           }

           txtLostPass.setOnClickListener {
                val recuperacion=recuperacion()

               recuperacion.show(supportFragmentManager.beginTransaction(),"xx")
           }

           txtNewUser.setOnClickListener {

               val fr=newUser()
               fr.show(supportFragmentManager,"usuarios")

           }

        }

        setContentView(views.root)



    }

}