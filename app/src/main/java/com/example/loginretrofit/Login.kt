package com.example.loginretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginretrofit.databinding.ActivityLoginBinding
import com.example.loginretrofit.fragments.recuperacion
import com.example.loginretrofit.utilities.myActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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


               Toast.makeText(this@Login,"Ok",Toast.LENGTH_LONG).show()

           }

           txtLostPass.setOnClickListener {
                val recuperacion=recuperacion()

               recuperacion.show(supportFragmentManager.beginTransaction(),"xx")
           }

        }

        setContentView(views.root)



    }

}