package com.acdp.sinergika.presentacion.auth.passwordRecover.view

import android.content.Intent

import android.os.Bundle
import android.view.View
import com.acdp.sinergika.R
import com.acdp.sinergika.base.BaseActivity
import com.acdp.sinergika.dominio.interactors.auth.passwordRecoveryInteractor.PasswordRecoryImpl
import com.acdp.sinergika.presentacion.auth.login.view.LoginActivity
import com.acdp.sinergika.presentacion.auth.passwordRecover.PasswordRecoverContrac
import com.acdp.sinergika.presentacion.auth.passwordRecover.presenter.PasswordRecorerPrensenter
import kotlinx.android.synthetic.main.activity_passwor_recovery.*

class PassworRecoveryActivity : BaseActivity(), PasswordRecoverContrac.PasswordRecoverView {


    lateinit var presenter: PasswordRecorerPrensenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PasswordRecorerPrensenter(PasswordRecoryImpl())
        presenter.attachView(this)
        //aqui presiona el boton de recoverpassword
        btn_enviar_recovey.setOnClickListener {
            recoverPassword()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_passwor_recovery
    }

    override fun showError(msgError: String?) {
        toast(this, msgError)
    }

    override fun showProgress() {
        progressBar_recovery.visibility = View.VISIBLE
        btn_enviar_recovey.visibility = View.GONE

    }

    override fun hideProgress() {
        progressBar_recovery.visibility = View.GONE
        btn_enviar_recovey.visibility = View.VISIBLE


    }

    override fun recoverPassword() {
        val email: String = etxt_email_recovery.text.trim().toString()
        if(!email.isEmpty()) presenter.sendPasswordRecover(email)else toast(this,"Ingrese Email")
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        toast(this,"Mensaje de recuperacion enviado")
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.detachJob()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
        presenter.detachJob()
    }
}
