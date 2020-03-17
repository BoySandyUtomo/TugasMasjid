package com.example.fanexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidNetworking.initialize(getApplicationContext());

        val context = this

        back.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java)
            startActivity(intent)
        }



        getdariserver()

    }

    fun getdariserver(){
        AndroidNetworking.get("http://192.168.100.92/WilkySholat/jadwal.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject) {
                        Log.e("_kotlinResponse", response.toString())

                        val jsonArray = response.getJSONArray("result")
                        for (i in 0 until jsonArray.length()){
                            val jsonObject = jsonArray.getJSONObject(i)
                            Log.e("_kotlinTitle", jsonObject.optString("Shubuh"))
                            Log.e("_kotlinTitle", jsonObject.optString("Dzuhur"))
                            Log.e("_kotlinTitle", jsonObject.optString("Ashar"))
                            Log.e("_kotlinTitle", jsonObject.optString("Maghrib"))
                            Log.e("_kotlinTitle", jsonObject.optString("Isya"))

                            subuh.setText(jsonObject.optString("shubuh"))
                            dhuha.setText(jsonObject.optString("dhuha"))
                            dhuhur.setText(jsonObject.optString("dhuhur"))
                            ashar.setText(jsonObject.optString("ashar"))
                            maghrib.setText(jsonObject.optString("maghrib"))
                            isya.setText(jsonObject.optString("isha"))
                        }
                    }

                    override fun onError(anError: ANError) {
                        Log.i("_err", anError.toString())
                    }
                })
    }

}
