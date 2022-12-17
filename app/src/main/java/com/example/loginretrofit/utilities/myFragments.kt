package com.example.loginretrofit.utilities

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputLayout

open class myFragments:DialogFragment() {

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

    fun verify(cl: ConstraintLayout):Boolean{

        cl.forEach {

            if(it is TextInputLayout){

                if(it.vacio())
                {
                    it.on()
                    return false
                }

            }

        }

        return true

    }


}