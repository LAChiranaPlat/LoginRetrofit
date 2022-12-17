package com.example.loginretrofit.myRetrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InterfaceRetroft {

    @POST("/login/save.php")
    suspend fun registrar(@Body user:dtLogin):Response<dtResponse>

    @POST("/login/list.php")
    suspend fun listar(@Body user:dtLogin):Response<dtResponse>

    @POST("/login/index.php")
    suspend fun login(@Body user:dtLogin):Response<dtResponse>

    @POST("/login/searchUser.php")
    suspend fun verify(@Body user:dtLogin):Response<dtResponse>

    @POST("/login/updatePassword.php")
    suspend fun upKey(@Body user:dtLogin):Response<dtResponse>

}