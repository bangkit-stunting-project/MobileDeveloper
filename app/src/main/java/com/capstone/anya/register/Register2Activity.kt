package com.capstone.anya.register



import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.capstone.anya.R
import com.capstone.anya.databinding.ActivityRegister2Binding
import java.text.SimpleDateFormat
import java.util.*


class Register2Activity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var register2Binding: ActivityRegister2Binding
    private val myCalendar = Calendar.getInstance()
    private val simpleDate = SimpleDateFormat("MMM, dd, yyyy", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        register2Binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(register2Binding.root)

//      Dropdown menu
        val jk_text =  resources.getStringArray(R.array.kelamin_array)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, jk_text)
        register2Binding.autoCompleteTextView.setAdapter(arrayAdapter)

//      Date Picker function
        register2Binding.dateText.setOnClickListener {
            DatePickerDialog(
                this,
                this,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myCalendar.set(year, month, dayOfMonth)
        updateCalendar(myCalendar.timeInMillis)
    }

    private fun updateCalendar(mCalendar: Long){
        register2Binding.dateText.setText(simpleDate.format(mCalendar))
    }
}