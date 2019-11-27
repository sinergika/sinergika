package com.acdp.sinergika.presentacion.auth.passwordRecover.presenter

import com.acdp.sinergika.dominio.interactors.auth.passwordRecoveryInteractor.PasswordRecovery
import com.acdp.sinergika.presentacion.auth.passwordRecover.PasswordRecoverContrac
import com.acdp.sinergika.presentacion.auth.passwordRecover.exception.PasswordRecoverExcption
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PasswordRecorerPrensenter(passwordRecoverInterctor: PasswordRecovery) :
    PasswordRecoverContrac.PasswordRecoverPresenter, CoroutineScope {

    //el attachView tiene su vista
    var view: PasswordRecoverContrac.PasswordRecoverView? = null//en kotlin todo se inicializa
    var passwordRecoverInterctor: PasswordRecovery? = null
    //job el que corre las routines y mandar el email el job es una instancia var job=job()
    var job = Job()
    //metodo del attachview
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    //el init es el inicializador del constructor
    init {
        this.passwordRecoverInterctor = passwordRecoverInterctor
    }


    override fun attachView(passwordRecoverView: PasswordRecoverContrac.PasswordRecoverView) {
        //asignamos la vista
        this.view = passwordRecoverView
    }

    override fun detachView() {
        view = null
    }

    override fun detachJob() {
        //cancela todos los job que se estan implentando en ese momento
        coroutineContext.cancel()
    }

    override fun isViewAttach(): Boolean {
        return view != null
    }

    override fun sendPasswordRecover(email: String) {
        launch {

            try {
                view?.showProgress()//antes de regresar la contraseña muestrame un progresbar
                passwordRecoverInterctor?.sendPasswordResetEmail(email)//manda el email, para ver si se puede enviar un mail dde recuperacion
                view?.hideProgress()//si se mando saco el progres
                view?.navigateToLogin()//mando al usurio al login de vuelta

            } catch (e: PasswordRecoverExcption) {
                view?.hideProgress()
                view?.showError(e.message)

            }

        }
    }
}