package zayver.taller1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.widget.addTextChangedListener
import java.text.DateFormat.getDateTimeInstance
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    companion object{
        private const val LOWER_BOUND = 1
        private const val UPPER_BOUND = 15
        private var fibonacciUtilization = 0
        private var factorialUtilization = 0
        private var fibonacciUtilizationDate = "Nunca"
        private var factorialUtilizationDate = "Nunca"
    }

    private lateinit var fibButton: Button
    private lateinit var facButton: Button
    private lateinit var countryButton: Button
    private lateinit var fibNumber: EditText
    private lateinit var facNumber: Spinner
    private lateinit var infoScreen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inflate()
        initialize()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        infoScreen.text = getString(R.string.info_activity_main, fibonacciUtilization,
            fibonacciUtilizationDate, factorialUtilization, factorialUtilizationDate)

    }
    private fun inflate(){
        fibButton = findViewById(R.id.fibonacci_button)
        facButton = findViewById(R.id.factorial_button)
        countryButton = findViewById(R.id.countries_button)
        fibNumber = findViewById(R.id.fibonacci_number)
        facNumber = findViewById(R.id.factorial_number)
        infoScreen = findViewById(R.id.info_activities_text)
    }
    private fun initialize(){
        //Restricción del enunciado, el spinner solo puede ir de 1 a 15
        //en java es necesario explicitamente el generic, el kotlin no
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            listOf(LOWER_BOUND..UPPER_BOUND).flatten())
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        facNumber.adapter = arrayAdapter

        //el fibonacci podría estar vacio, hasta que el usuario no teclee algo no activar el botón
        fibButton.isEnabled = false
    }
    private fun setListeners(){
        fibButton.setOnClickListener{
            fibonacciUtilization++
            fibonacciUtilizationDate = getDateHour()
            val send = Intent(baseContext, FibonacciActivity::class.java)
            send.putExtra("fib_bound", fibNumber.text.toString().toInt())
            startActivity(send)
        }
        facButton.setOnClickListener {
            factorialUtilization++
            factorialUtilizationDate = getDateHour()
            val send = Intent(baseContext, FactorialActivity::class.java)
            send.putExtra("fac_bound", facNumber.selectedItem as Int)
            startActivity(send)
        }
        countryButton.setOnClickListener {
            val send = Intent(baseContext, CountryActivity::class.java)
            startActivity(send)
        }
        fibNumber.addTextChangedListener {
            fibButton.isEnabled = it.toString() != ""
        }
    }
    private fun getDateHour(): String {
        return getDateTimeInstance().format(Date())
    }


}