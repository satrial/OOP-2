package com.satria.oop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.satria.oop.Database.AppRoomDB
import com.satria.opp.Database.Laptop
import kotlinx.android.synthetic.main.activity_edit_laptop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditLaptopActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_laptop)
        setupListener()
    }

    fun setupListener(){
        btn_saveLaptop.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.LaptopDao().addHLaptop(
                    Laptop(0, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                )
                finish()
            }
        }
    }
}