package zayver.taller1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import entity.Country
import org.json.JSONObject

class CountryActivity : AppCompatActivity() {
    private lateinit var scrollCountryActivity: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        inflate()
        initialize()
        setListeners()
    }
    private fun inflate(){
        scrollCountryActivity = findViewById(R.id.country_list_view)

    }
    private fun setListeners(){
        scrollCountryActivity.setOnItemClickListener{ _: AdapterView<*>, _: View,
                                                      i: Int, _: Long ->
            run {
                val selected = scrollCountryActivity.getItemAtPosition(i) as Country
                val next = Intent(baseContext, CountryActivityDetailed::class.java)
                next.putExtra("country", selected)
                startActivity(next)
            }
        }
    }
    private fun initialize(){
        //listview de paises
        val countries = deserializeCountries()
        val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1,
            android.R.id.text1, countries)
        scrollCountryActivity.adapter = adapter

    }
    //Preferiría hacer la deserialización en otra clase pero para obtener el Asset es necesario el
    //contexto
    private fun deserializeCountries() : ArrayList<Country>{
        val asset = assets.open("paises.json")
        val jsonCountry = asset.bufferedReader().use {
            it.readText()
        }
        val json = JSONObject(jsonCountry)
        val arrList = ArrayList<Country>()
        val arr = json.getJSONArray("paises")
        for (i in 0 until arr.length()) {
            val name = arr.getJSONObject(i).getString("nombre_pais")
            val nameInt = arr.getJSONObject(i).getString("nombre_pais_int")
            val capital = arr.getJSONObject(i).getString("capital")
            val initials = arr.getJSONObject(i).getString("sigla")
            arrList.add(Country(name, nameInt, initials, capital))
        }
        return arrList
    }


}