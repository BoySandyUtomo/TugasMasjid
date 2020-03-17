package com.example.fanexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONObject

class PengumumanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        val context = this

        back.setOnClickListener{
            val intent = Intent(context,MenuActivity::class.java)
            startActivity(intent)
        }

        getpengumuman()
    }

    fun getpengumuman(){
        AndroidNetworking.get("http://192.168.100.92/WilkySholat/pengumuman.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_pengumuman"))
                        Log.e("_kotlinTitle", jsonObject.optString("isi_pengumuman"))

                        isi.setText(jsonObject.optString("isi_pengumuman"))
                        judul.setText(jsonObject.optString("judul_pengumuman"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun getIsi(){
        AndroidNetworking.get("http://192.168.100.92/jamSholat/pengumuman-json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("isi_pengumuman"))

                        isi.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }

//    fun getAktif(){
//        AndroidNetworking.get("http://10.35.136.97/jamSholat/pengumuman-json.php")
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener {
//                override fun onResponse(response: JSONObject) {
//                    Log.e("_kotlinResponse", response.toString())
//
//                    val jsonArray = response.getJSONArray("result")
//                    for (i in 0 until jsonArray.length()){
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        Log.e("_kotlinTitle", jsonObject.optString("aktif"))
//
//                        aktif.setText(jsonObject.optString("aktif"))
//                    }
//                }
//
//                override fun onError(anError: ANError) {
//                    Log.i("_err", anError.toString())
//                }
//            })
//    }
}
