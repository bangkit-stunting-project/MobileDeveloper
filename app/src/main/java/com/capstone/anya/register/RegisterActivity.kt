package com.capstone.anya.register

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityRegister2Binding
import com.capstone.anya.databinding.ActivityRegisterBinding
import com.capstone.anya.login.LoginActivity
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        //      Dropdown menu
        val jk_text =  resources.getStringArray(R.array.kelamin_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, jk_text)
        registerBinding.autoCompleteTextView.setAdapter(arrayAdapter)


//      Date Picker function
        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateCalendar(myCalendar)
        }

        registerBinding.registerButtonNext.setOnClickListener {
            startActivity(Intent(this, Register2Activity::class.java))
        }
    }

    private fun updateCalendar(myCalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        registerBinding.datePickerText.setText(sdf.format(myCalendar.time))
    }
}