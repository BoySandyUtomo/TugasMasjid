package com.example.fanexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_slideshow.*
import org.json.JSONObject

class SlideshowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        val context = this

        back.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java)
            startActivity(intent)
        }

        getpengumuman()
    }

    fun getpengumuman(){
        AndroidNetworking.get("http://192.168.100.92/WilkySholat/slideshow.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))
                        Log.e("_kotlinTitle", jsonObject.optString("url_slideshow"))

                        url.setText(jsonObject.optString("url_slideshow"))
                        judul.setText(jsonObject.optString("judul_slideshow"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

}
