package com.acdp.sinergika.dominio.interactors.auth.passwordRecoveryInteractor

interface PasswordRecovery {

    suspend fun sendPasswordResetEmail(email:String)
}