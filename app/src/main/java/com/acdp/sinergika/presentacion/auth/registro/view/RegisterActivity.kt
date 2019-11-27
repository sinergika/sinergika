package com.acdp.sinergika.presentacion.auth.registro.view

import android.os.Bundle
import com.acdp.sinergika.R
import com.acdp.sinergika.base.BaseActivity
import com.acdp.sinergika.presentacion.auth.registro.RegistarContract

import android.content.Intent
import android.view.View
import com.acdp.sinergika.dominio.interactors.auth.registerInteractor.RegisterInteractorImpl
import com.acdp.sinergika.presentacion.main.view.MenuPrincipal
import com.acdp.sinergika.presentacion.auth.registro.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(),RegistarContract.RegisterView {

    lateinit var presenter: RegisterPresenter //lateinit asume inicializar tarde si no es null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter= RegisterPresenter(RegisterInteractorImpl())//inyectamos el interactor de4l presenter
        presenter.adjuntarVista(this)

        btn_registrar.setOnClickListener{
            SignUp()

        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_register

    }

    override fun navigateToMain() {
        val intent=Intent(this,MenuPrincipal::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)


    }

    override fun SignUp() {
        val fullName:String=etxt_fullName.text.toString().trim()//obtenemos el nombre
        val email:String=etxt_Email_Registro.text.toString().trim()
        val pw1:String=etxt_pw1.text.toString().trim()
        val pw2:String=etxt_pw2.text.toString().trim()

        if (presenter.checkEmpyName(fullName)){//si el nombre es vacio
            etxt_fullName.error="El nombre esta vacio"//retorna
            return
        }
        if (!presenter.checkValidEmail(email)){//si el check es invalido
            etxt_Email_Registro.error="El correo esta vacio"//retorna
            return
        }
        if (presenter.checkVaciosPassword(pw1,pw2)){
            etxt_pw1.error="La contraseña esta vacio"
            etxt_pw2.error="La contraseña esta vacio"
            return

        }
        if (!presenter.comprobarConsidenciasContraseña(pw1,pw2)) {
            etxt_pw1.error = "Las contraseñas no coinciden"
            etxt_pw2.error = "Las contraseñas no coinciden"
            return
        }
        presenter.signUp(fullName,email ,pw1) //le pasa el nom, el mail y la contraseña si todo lo anterior esta correcto
        //luego que registra va la RegisterPrsenter

    }

    override fun showProgress() {
        progres_registrar.visibility=View.VISIBLE
        btn_registrar.visibility=View.GONE

    }

    override fun hideProgres() {

        progres_registrar.visibility=View.GONE
        btn_registrar.visibility=View.VISIBLE

    }

    override fun showError(errormsg: String) {
        toast(this, errormsg)


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.separarView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.separarView()
    }
}
