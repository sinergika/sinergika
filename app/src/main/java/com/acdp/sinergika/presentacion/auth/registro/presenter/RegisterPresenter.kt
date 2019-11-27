package com.acdp.sinergika.presentacion.auth.registro.presenter

import androidx.core.util.PatternsCompat
import com.acdp.sinergika.dominio.interactors.auth.registerInteractor.RegisterInteractor
import com.acdp.sinergika.presentacion.auth.registro.RegistarContract

class RegisterPresenter (registerInteractor: RegisterInteractor) :RegistarContract.RegistrarPresentador{


    //primero inicializamos la vista
    var view:RegistarContract.RegisterView?=null
    var registerInteractor:RegisterInteractor?=null
    init {
       this.registerInteractor=registerInteractor
   }
    override fun adjuntarVista(view: RegistarContract.RegisterView) {
        this.view=view
    }
    override fun isVistaAdjuntada(): Boolean {
        return view!=null

    }
    override fun separarView() {
        view= null
    }
    override fun checkEmpyName(fullName: String): Boolean {
        return fullName.isEmpty()
    }
    override fun checkValidEmail(email: String): Boolean {
        //permite validar el email
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }
    override fun checkVaciosPassword(pw1: String, pw2: String): Boolean {
        return pw1 .isEmpty() or pw2.isEmpty()
    }

    override fun comprobarConsidenciasContraseña(pw1: String, pw2: String): Boolean {
        //se compara la contraseña
       return pw1==pw2

      //  modo java
        //return pw1.equals(pw2)
    }
    override fun signUp(fullName: String, email: String, password: String) {
        view?.showProgress()//antes de iniciar muestra el dialogo de progreso

        //invoca al registerInteractor con su signUp y el signUp se implementa en RegisterInteractor
        registerInteractor?.signUp(fullName,email,password,object:RegisterInteractor.RegisterCallBack{

            override fun onRegisterSucces() {//registro exitoso
                view?.navigateToMain()
                view?.hideProgres()
            }
            override fun onRegisterFailure(errorMsg: String) {
                view?.showError(errorMsg)
                view?.hideProgres()
            }
        })

    }
}