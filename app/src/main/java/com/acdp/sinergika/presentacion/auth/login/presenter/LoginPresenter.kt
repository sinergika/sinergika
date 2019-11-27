package com.acdp.sinergika.presentacion.auth.login.presenter

import com.acdp.sinergika.dominio.interactors.auth.loginInteractor.SignInIterractot
import com.acdp.sinergika.presentacion.auth.login.LoginContract
import com.acdp.sinergika.presentacion.auth.login.exceptions.FireBaseLoginException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginPresenter(signInIterractot: SignInIterractot):LoginContract.LoginPrensenter,CoroutineScope {
    var view: LoginContract.View? = null
    var signInIterractot: SignInIterractot? = null

    //implementamos el CoroutineScopecontext
    private val job = Job()//el job mantiene la coroutine en segundo plano
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    init {
        this.signInIterractot = signInIterractot
    }


    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun dettachJob() {
        coroutineContext.cancel()//cancela la tarea asincrona en segundo plano
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ) {//funcion para loguearse logearse
        launch {

            view?.showProgressBar()

            try {
                signInIterractot?.SignInconEmailAndPassword(email, password)
                //se suspende y pasa al SignInconEmailAndPassword y lo lee luego regresa
                if (isViewAttached()) {//si terminaste de hacer la vista
                    view?.hideProgressBar()//ocultar el progres
                    view?.navigateToMain()//ahora navega en el menu

                }

            }catch (e:FireBaseLoginException){
                view?.showError(e.message)
                view?.hideProgressBar()

            }





        }
    }


    override fun checkEmptyFiels(email: String, password: String): Boolean {
        return email.isEmpty() || password.isEmpty()

    }
}