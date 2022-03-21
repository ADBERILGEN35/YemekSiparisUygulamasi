package com.example.yemeksiparis.repo

import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import com.example.yemeksiparis.databinding.*
import com.example.yemeksiparis.fragment.GirisFragmentDirections
import com.example.yemeksiparis.fragment.KayitFragmentDirections
import com.example.yemeksiparis.fragment.ProfilFragmentDirections
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class AuthRepository {
    private var auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
    }

    fun kayit(email: String, password: String, name: String, view: FragmentKayitBinding) {
        view.progressBarKayit.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name).build()

                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.e("Kayıt", "Geçerli kullanıcı adı")
                            }
                        }
                    Log.e("Kayıt", "Başarılı")
                    view.progressBarKayit.visibility = View.GONE
                    val route = KayitFragmentDirections.kayitAnasayfaGecis()
                    Navigation.findNavController(view.root).navigate(route)
                } else {
                    Log.e("Kayıt", "${task.exception}")
                    view.progressBarKayit.visibility = View.GONE
                    Snackbar.make(
                        view.root,
                        "Kayıt oluşturulken bir hata oluştu. Lütfen tekrar deneyin",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun girisYap(email: String, password: String, view: FragmentGirisBinding) {
        view.progressBarGiris.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Log.e("Login", "Success")
                    view.progressBarGiris.visibility = View.GONE
                    val route = GirisFragmentDirections.girisAnasayfaGecis()
                    Navigation.findNavController(view.root).navigate(route)
                } else {
                    Log.e("Login", "${task.exception}")
                    view.progressBarGiris.visibility = View.GONE
                    Snackbar.make(view.root, "Email veya parola hatalı", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun sifreDegistir(
        current_password: String,
        new_password: String,
        view: FragmentSifreDegisimBinding
    ) {
        val user = auth.currentUser

        val credential = EmailAuthProvider
            .getCredential("${user!!.email}", current_password)

        user!!.reauthenticate(credential)
            .addOnCompleteListener {
                user!!.updatePassword(new_password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Snackbar.make(view.root, "Parola güncellendi", Snackbar.LENGTH_SHORT)
                                .show()
                        } else {
                            Log.e("Password", "${task.exception}")
                            Snackbar.make(view.root, "${task.exception}", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
    }

    fun profilGuncelle(
        name: String,
        email: String,
        password: String,
        view: FragmentProfilGuncellemeBinding
    ) {
        val user = auth.currentUser

        val credential = EmailAuthProvider
            .getCredential("${user!!.email}", password)

        user!!.reauthenticate(credential)
            .addOnCompleteListener {

                user!!.updateEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user!!.reload()
                            Snackbar.make(view.root, "Bilgiler güncellendi", Snackbar.LENGTH_SHORT)
                                .show()
                        } else {
                            Snackbar.make(view.root, "${task.exception}", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build()

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user!!.reload()
                        } else {
                            Snackbar.make(view.root, "${task.exception}", Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }

            }
    }

    fun cikisYap(view: FragmentProfilBinding) {
        FirebaseAuth.getInstance().signOut()
        val route = ProfilFragmentDirections.profilGirisGecis()
        Navigation.findNavController(view.root).navigate(route)
    }
}