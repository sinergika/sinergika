package com.acdp.sinergika.dominio.interactors.auth.loginInteractor

interface SignInIterractot {



    //El suspend: Dice este metodo(SignInconEmailAndPassword)se puede ejecutar en otro threp aparte


    suspend  fun SignInconEmailAndPassword(email:String,password:String)
}