package com.acdp.sinergika.dominio.interactors.auth.registerInteractor

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterInteractorImpl:RegisterInteractor {
    override fun signUp( fullName: String, email: String, password: String, listener: RegisterInteractor.RegisterCallBack) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{itSignUp->

            if (itSignUp.isSuccessful){//se puede registrar el ususario

                val     profileUpdates:UserProfileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(fullName)
                    .build()
                FirebaseAuth.getInstance().currentUser?.updateProfile(profileUpdates)?.addOnCompleteListener{
                    if (it.isSuccessful){//termino de registrar completamente al usurio
                        listener.onRegisterSucces()
                    }
                }
            }else{
                listener.onRegisterFailure(itSignUp.exception?.message.toString())
            }
        }

    }

}