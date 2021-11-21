package br.usp.ime.coffee_shop_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import br.usp.ime.coffee_shop_kotlin.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val requestCodeSignIn = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("542085710204-jgu96bdmckbc5ap1ko0gkqc0kn8r7aus.apps.googleusercontent.com")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleBtn.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent

        showLoading()
        startActivityForResult(
            signInIntent, requestCodeSignIn
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestCodeSignIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )

            val googleFirstName = account?.givenName ?: ""
            val googleLastName = account?.familyName ?: ""
            val googleEmail = account?.email ?: ""
            val googleProfilePicURL = account?.photoUrl.toString()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("firstName", googleFirstName)
            intent.putExtra("lastName", googleLastName)
            intent.putExtra("email", googleEmail)
            intent.putExtra("picURL", googleProfilePicURL)

            removeLoading()
            startActivity(intent)

        } catch (e: ApiException) {
            removeLoading()
            Log.e("Failure code:", e.statusCode.toString())
        }
    }

    private fun showLoading() {
        binding.loadingWrapper.visibility = View.VISIBLE
        binding.siginWrapper.visibility = View.GONE
    }

    private fun removeLoading() {
        binding.loadingWrapper.visibility = View.GONE
        binding.siginWrapper.visibility = View.VISIBLE
    }
}