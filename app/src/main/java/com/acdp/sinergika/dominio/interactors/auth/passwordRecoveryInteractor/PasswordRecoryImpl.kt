package com.acdp.sinergika.dominio.interactors.auth.passwordRecoveryInteractor

import com.acdp.sinergika.presentacion.auth.passwordRecover.exception.PasswordRecoverExcption
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import  kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class PasswordRecoryImpl:PasswordRecovery {
    override suspend fun sendPasswordResetEmail(email:String):Unit= suspendCancellableCoroutine {continuation->
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
            if (it.isSuccessful){
                continuation.resume(Unit)
            }else{
                continuation.resumeWithException(PasswordRecoverExcption(it.exception?.message!!))

            }
        }
    }
}