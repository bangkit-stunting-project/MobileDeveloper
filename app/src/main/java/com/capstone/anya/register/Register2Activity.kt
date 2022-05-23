package com.capstone.anya.register


import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityRegister2Binding
import java.text.SimpleDateFormat
import java.util.*

class Register2Activity : AppCompatActivity() {

    private lateinit var register2Binding: ActivityRegister2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        register2Binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(register2Binding.root)


//      Dropdown menu
        val jk_text =  resources.getStringArray(R.array.kelamin_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, jk_text)
        register2Binding.autoCompleteTextView.setAdapter(arrayAdapter)


//      Date Picker function
        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateCalendar(myCalendar)
        }

        register2Binding.datePickerLayout.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
        }
    }

    private fun updateCalendar(myCalendar: Calendar){
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        register2Binding.datePickerText.setText(sdf.format(myCalendar.time))
    }
}