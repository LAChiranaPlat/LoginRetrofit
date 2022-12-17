package com.example.loginretrofit.myRetrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class myRetroft {

    companion object{

        fun retrofit():Retrofit{
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.11/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}