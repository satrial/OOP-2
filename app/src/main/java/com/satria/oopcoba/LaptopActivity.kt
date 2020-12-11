package com.satria.oop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.satria.oop.Database.AppRoomDB
import kotlinx.android.synthetic.main.activity_laptop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaptopActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptop)
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val allLaptop = db.LaptopDao().getAllLaptop()
            Log.d("Laptopctivity", "dbResponse: $allLaptop")
        }
    }

    fun setupListener() {
        btn_createLaptop.setOnClickListener {
            startActivity(Intent(this, EditLaptopActivity::class.java))
        }
    }
}