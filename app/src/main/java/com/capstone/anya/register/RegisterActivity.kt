package com.capstone.anya.register

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityRegisterBinding
import com.capstone.anya.login.LoginActivity
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var registerBinding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel>()

    private val myCalendar = Calendar.getInstance()
    private val simpleDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        val jkText =  resources.getStringArray(R.array.kelamin_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, jkText)
        registerBinding.genderTextView.setAdapter(arrayAdapter)

        setupAction()
        setupViewModel()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myCalendar.set(year, month, dayOfMonth)
        updateCalendar(myCalendar.timeInMillis)
    }

    private fun setupViewModel() {
        registerViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        registerViewModel.isDone.observe(this) {
            intentLogin(it)
        }

    }

    private fun setupAction() {
        registerBinding.dateText.setOnClickListener {
            setDatePicker()
        }

        registerBinding.registerButton.setOnClickListener {
            hideWarning()
            registerValidation()
        }
    }

    private fun registerValidation(){
        val name = registerBinding.nameEditText.text.toString()
        val email = registerBinding.emailEditTextRegister.text.toString()
        val password = registerBinding.passwordEditTextLogin.text.toString()
        val passwordConfirm = registerBinding.konfirmasiPasswordEditTextRegister.text.toString()
        val address = registerBinding.tempatLahirEditText.text.toString()
        val date = registerBinding.dateText.text.toString()
        var gender: String = registerBinding.genderTextView.text.toString()
        gender = if(gender == "Laki-Laki") "M" else "F"

        when {
            name.isEmpty() -> {
                registerBinding.nameEditTextLayoutRegister.error = "Masukkan nama lengkap"
            }
            email.isEmpty() -> {
                registerBinding.emailEditTextLayoutRegister.error = "Masukkan email"
            }
            !email.isValidEmail() -> {
                registerBinding.emailEditTextLayoutRegister.error = "Alamat email salah"
            }
            password.isEmpty() -> {
                registerBinding.passwordEditTextLayoutRegister.error = "Masukkan sebuah password"
            }
            password.length < 8 -> {
                registerBinding.passwordEditTextLayoutRegister.error = "Gunakan 8 karakter atau lebih untuk sandi"
            }
            password.isEmpty() -> {
                registerBinding.konfirmasiPasswordEditTextLayoutRegister.error = "Masukkan konfirmasi password"
            }
            passwordConfirm != password -> {
                registerBinding.konfirmasiPasswordEditTextLayoutRegister.error = "Password tersebut tidak cocok. Coba lagi."
            }
            address.isEmpty() -> {
                registerBinding.tempatLahirEditTextLayoutRegister2.error = "Masukkan tempat tinggal atau lahir"
            }
            date.isEmpty() -> {
                registerBinding.datePickerLayout.error = "Masukkan tanggal lahir"
            }
            gender.isEmpty() -> {
                registerBinding.datePickerLayout.error = "Pilih jenis kelamin"
            }
            else -> {
                Log.d("RegisterActivity", "$name, $email, $password, $passwordConfirm, $address, $date, $gender")
                registerViewModel.postRegister(name, email, password, passwordConfirm, address, date, gender)
            }
        }
    }

    private fun hideWarning() {
        registerBinding.nameEditTextLayoutRegister.error = null
        registerBinding.emailEditTextLayoutRegister.error = null
        registerBinding.passwordEditTextLayoutRegister.error = null
        registerBinding.konfirmasiPasswordEditTextLayoutRegister.error = null
        registerBinding.tempatLahirEditTextLayoutRegister2.error = null
    }

    private fun setDatePicker() {
        DatePickerDialog(
            this,
            this,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateCalendar(mCalendar: Long){
        registerBinding.dateText.setText(simpleDate.format(mCalendar))
    }

    private fun String.isValidEmail() =
        isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }

    private fun showLoading(isLoading: Boolean) {
        registerBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun intentLogin(isDone: Boolean) {
        if(isDone){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}