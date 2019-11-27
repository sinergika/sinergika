package com.acdp.sinergika.presentacion.auth.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.acdp.sinergika.R
import com.acdp.sinergika.base.BaseActivity
import com.acdp.sinergika.dominio.interactors.auth.loginInteractor.SignInInteractorlmp
import com.acdp.sinergika.presentacion.auth.login.LoginContract
import com.acdp.sinergika.presentacion.auth.login.presenter.LoginPresenter
import com.acdp.sinergika.presentacion.auth.passwordRecover.view.PassworRecoveryActivity
import com.acdp.sinergika.presentacion.main.view.MenuPrincipal
import com.acdp.sinergika.presentacion.auth.registro.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity(),LoginContract.View {

    lateinit var presenter:LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=LoginPresenter(SignInInteractorlmp())
        presenter.attachView(this)

        btn_signIn.setOnClickListener {
            singIn()
        }

        //se conecta en la interfaz register
        text_resgistrarse.setOnClickListener{
            navigateToRegister()
        }

        txt_recovery_password.setOnClickListener{
            navigateToPasswordRecover()
        }
    }
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun showError(msgError: String?) {
        toast(this,msgError)//muetra un mensaje de error

    }

    override fun showProgressBar() {
       progressBar_signIn.visibility=View.VISIBLE //
        btn_signIn.visibility=View.GONE
    }

    override fun hideProgressBar() {
        progressBar_signIn.visibility=View.GONE
        btn_signIn.visibility=View.VISIBLE

    }

    override fun singIn() {//paso 1 se preciona el boton ingresar
        val email:String=etxt_email.text.toString().trim()//obtiene el email y la contraseña
        val password:String=etxt_password.text.toString().trim()
        if(presenter.checkEmptyFiels(email,password)){
            toast(this,"Uno o ambos campos son vacios")//si esta bacio muestra el error
        }else{
            presenter.signInUserWithEmailAndPassword(email,password)//paso2 viene al presenter
        }

    }

    override fun navigateToMain() {//Ingresamos a la panatalla pricipal MainActivity
        val intent= Intent(this,MenuPrincipal::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))

    }

    override fun navigateToPasswordRecover() {
        startActivity(Intent(this, PassworRecoveryActivity::class.java))
    }

    override fun onDestroy() {//cuando se de destruye el activity
        super.onDestroy()
        presenter.dettachView()//saco la vista
        presenter.dettachJob()//cancelo todas las cosas pendientes que se estan haciendo
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.dettachView()
        presenter.dettachJob()
    }

}
