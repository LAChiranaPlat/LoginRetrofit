package com.example.loginretrofit.utilities

import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

open class myActivity:AppCompatActivity() {

    fun TextInputLayout.vacio():Boolean{
        return this.editText?.text!!.isEmpty()
    }

    fun TextInputLayout.cls(){
        this.editText?.text!!.clear()
    }

    fun TextInputLayout.str():String{
        return this.editText?.text.toString()
    }

    fun TextInputLayout.txt(str:String){
        this.editText?.setText(str)
    }

    fun TextInputLayout.on(){
        this.editText?.requestFocus()
    }

    fun verify(cl:ConstraintLayout):Boolean{

        cl.forEach {

            if(it is TextInputLayout){

                if(it.vacio())
                    return false

            }

        }

        return true

    }

}