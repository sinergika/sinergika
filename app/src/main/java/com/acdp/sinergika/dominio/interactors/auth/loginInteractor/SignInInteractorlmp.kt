package com.acdp.sinergika.dominio.interactors.auth.loginInteractor

import com.acdp.sinergika.presentacion.auth.login.exceptions.FireBaseLoginException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SignInInteractorlmp:SignInIterractot {

    //el SignInconEmailAndPassword le avisa al presenter que termino
    override suspend fun SignInconEmailAndPassword(email: String, password: String): Unit =
        suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    // aqui termina de logarse

                    if (it.isSuccessful) {
                        continuation.resume(Unit)//aqui continua despues de loguearse

                    } else {
                        continuation.resumeWithException(FireBaseLoginException(it.exception?.message))
                    }
                }
        }
}