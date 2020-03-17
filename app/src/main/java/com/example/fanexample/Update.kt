package com.example.fanexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_update.*
import org.json.JSONArray

class Update : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val context = this

        simpan.setOnClickListener{

            var data_namamasjid :String = data1.text.toString()
            var data_alamatmasjid :String = data2.text.toString()

            postkeserver(data_namamasjid, data_alamatmasjid)

            val intent = Intent(context,MenuActivity::class.java)
            startActivity(intent)

        }
    }

    fun postkeserver(data1:String, data2:String)
    {
        AndroidNetworking.post("http://192.168.100.92/WilkySholat/update_identitas.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError?) {

                }
            })
    }
}
