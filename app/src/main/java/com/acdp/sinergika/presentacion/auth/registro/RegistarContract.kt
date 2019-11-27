package com.acdp.sinergika.presentacion.auth.registro

interface RegistarContract {
    interface RegisterView{//metodo del la vista
        fun navigateToMain()//envia al usurio ala pantalla principal
        fun SignUp()//registrase
        fun showProgress()//mostrar progreso
        fun hideProgres()//ocultar progreso
        fun showError(errormsg:String)//mostrar Error


    }
    interface RegistrarPresentador{//metodos del registro
        fun adjuntarVista(view:RegisterView)
        fun isVistaAdjuntada():Boolean
        fun separarView()
        fun checkEmpyName(fullName:String):Boolean
        fun checkValidEmail(email: String):Boolean
        fun checkVaciosPassword(pw1:String, pw2:String):Boolean
        fun comprobarConsidenciasContraseña(pw1:String, pw2:String):Boolean
        fun signUp(fullName: String,email: String,password:String)

    }
}