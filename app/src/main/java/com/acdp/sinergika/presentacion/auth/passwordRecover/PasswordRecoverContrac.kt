package com.acdp.sinergika.presentacion.auth.passwordRecover

interface PasswordRecoverContrac {
    interface PasswordRecoverView {
        fun showError(msgError: String?)
        fun showProgress()
        fun hideProgress()
        fun recoverPassword()
        fun navigateToLogin()

    }

    interface PasswordRecoverPresenter {
        fun attachView(passwordRecoverView: PasswordRecoverView)
        fun detachView()//separa la vista
        fun detachJob()
        fun isViewAttach(): Boolean//pregunta si esta tachado o no la vista
        fun sendPasswordRecover(email: String)//encargado de actualizar la vista c
    }
}