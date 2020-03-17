package com.example.fanexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas.*
import org.json.JSONObject

class IdentitasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)

        val context = this

        back.setOnClickListener {
            val intent = Intent(context, MenuActivity::class.java)
            startActivity(intent)

        }

            update.setOnClickListener{
                val intent = Intent(context,Update::class.java)
                startActivity(intent)


        }

        getindentitas()
    }

    fun getindentitas(){
        AndroidNetworking.get("http://192.168.100.92/WilkySholat/identitas.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("nama_masjid"))
                        Log.e("_kotlinTitle", jsonObject.optString("alamat_masjid"))

                        nama.setText(jsonObject.optString("nama_masjid"))
                        alamat.setText(jsonObject.optString("alamat_masjid"))
                    }
                }

                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
