package zayver.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import utils.MathFunction

class FibonacciActivity : AppCompatActivity() {
    private lateinit var fibImgButton: ImageButton
    private lateinit var fibScrollView: ScrollView
    private lateinit var fibLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fibonacci)
        inflate()
        setListeners()
        initialize()
    }

    private fun inflate() {
        fibImgButton = findViewById(R.id.fib_img_button)
        fibScrollView = findViewById(R.id.fib_scroll_view)
        fibLinearLayout = findViewById(R.id.fib_linear_layout)
    }
    private fun setListeners(){
        fibImgButton.setOnClickListener{
            val newIntent = Intent(baseContext, WebViewActivity::class.java)
            newIntent.putExtra("url_web", getString(R.string.fibonacci_bio))
            startActivity(newIntent)
        }
    }
    private fun initialize(){
        //init Fibonacci sequence
        val bound = intent.getIntExtra("fib_bound", 0)
        val fib = MathFunction.fibonacciSequence(bound)
        for(item in fib){
            val add = TextView(baseContext)
            add.text = item.toString()
            fibLinearLayout.addView(add)
        }
    }

}