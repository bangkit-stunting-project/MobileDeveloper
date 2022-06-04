package com.capstone.anya.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.anya.databinding.ActivityLoginBinding
import com.capstone.anya.main.MainActivity
import com.capstone.anya.main.ViewModelFactory
import com.capstone.anya.model.UserPreference
import com.capstone.anya.register.RegisterActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "authLogin")

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        setupView()
        setupViewModel()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.clearToken()

        loginViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        loginViewModel.getToken().observe(this) { user ->
            if (user.token.toString().isNotEmpty()) {
                intentMain()
                finish()
            }
        }

    }

    private fun setupAction(){
        loginBinding.registerButtonNavigation.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginBinding.loginButton.setOnClickListener {
            loginValidation()
        }
    }

    private fun loginValidation(){
        val email = loginBinding.emailEditTextLogin.text.toString()
        val password = loginBinding.passwordEditTextLogin.text.toString()
        when {
            email.isEmpty() -> {
                loginBinding.emailEditTextLayoutLogin.error = "Email tidak boleh kosong"
            }
            !email.isValidEmail() -> {
                loginBinding.emailEditTextLayoutLogin.error = "Email Format Salah"
            }
            password.isEmpty() -> {
                loginBinding.emailEditTextLayoutLogin.error = null
                loginBinding.passwordEditTextLayoutLogin.error = "Password tidak boleh kosong"
            }
            else -> {
                loginBinding.passwordEditTextLayoutLogin.error = null
                loginViewModel.postLogin(email, password)
            }
        }
    }

    private fun String.isValidEmail() =
        isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun showLoading(isLoading: Boolean) {
        loginBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun intentMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}