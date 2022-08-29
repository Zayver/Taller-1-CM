package zayver.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import utils.MathFunction

class FactorialActivity : AppCompatActivity() {
    private lateinit var facResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factorial)
        inflate()
        initialize()
    }
    private fun inflate(){
        facResult = findViewById(R.id.fac_result)
    }
    private fun initialize(){
        //init facResult
        val info = intent
        val res = MathFunction.factorialWithStr(info.getIntExtra("fac_bound", 0))
        facResult.text = getString(R.string.fac_result, res.first, res.second)
        facResult.background.alpha = 200
    }
}