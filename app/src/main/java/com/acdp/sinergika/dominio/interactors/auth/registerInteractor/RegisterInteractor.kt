package com.acdp.sinergika.dominio.interactors.auth.registerInteractor

interface RegisterInteractor {
    interface RegisterCallBack{
            fun onRegisterSucces()
            fun onRegisterFailure(errorMsg:String)

    }
    fun signUp(fullName:String,email:String,password:String,listener:RegisterCallBack)
}