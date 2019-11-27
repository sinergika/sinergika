package com.acdp.sinergika.presentacion.auth.login

interface LoginContract {

    interface  View{
        fun showError(msgError: String?)//muetra la notificacion de error
        fun showProgressBar()//muestra el progres dialog
        fun hideProgressBar()//oculta el pogres dialog cuando termina de cargar
        fun singIn()//ingresa el email
        fun navigateToMain()
        fun navigateToRegister()
        fun navigateToPasswordRecover()
    }

    interface   LoginPrensenter{

        fun  attachView(view: View)
        fun dettachView()
        fun dettachJob()
        fun isViewAttached():Boolean
        fun signInUserWithEmailAndPassword(email: String,password:String)
        fun checkEmptyFiels(email: String, password: String):Boolean
    }
}