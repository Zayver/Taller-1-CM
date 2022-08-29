package zayver.taller1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import entity.Country
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class CountryActivityDetailed : AppCompatActivity() {
    private lateinit var countryName: TextView
    private lateinit var countryNameInt: TextView
    private lateinit var capitalName: TextView
    private lateinit var initials: TextView
    private lateinit var countryFlag: ImageView
    private lateinit var errorText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detailed)
        inflate()
        initialize()
    }
    private fun inflate(){
        countryName = findViewById(R.id.country_name_text)
        countryNameInt = findViewById(R.id.country_name_int_text)
        capitalName = findViewById(R.id.capital_text)
        initials = findViewById(R.id.initials_text)
        countryFlag = findViewById(R.id.country_flag_imageview)
        errorText = findViewById(R.id.error_text)
    }
    private fun initialize(){
        val thisIntent = intent
        val country = thisIntent.getSerializableExtra("country") as Country
        countryName.text = country.name
        countryNameInt.text = country.intName
        capitalName.text = country.capital
        initials.text = country.initials


        CoroutineScope(Dispatchers.Main).launch {
            try{
                countryFlag.setImageBitmap(retrieveFile(country.initials))
            }catch (e: Exception){
                countryFlag.visibility = View.INVISIBLE
                errorText.visibility= View.VISIBLE
            }
        }
    }

     private suspend fun retrieveFile(countryCode: String): Bitmap = withContext(Dispatchers.IO) {
         withTimeout(5000){
             val url = URL(getString(R.string.country_api, countryCode))
             val con = url.openConnection() as HttpURLConnection
             val rawImg = con.inputStream
             return@withTimeout BitmapFactory.decodeStream(rawImg)
         }
     }
}
