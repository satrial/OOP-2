package com.satria.oopcoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.satria.oopcoba.Database.AppRoomDB
import com.satria.oopcoba.Database.Constant
import com.satria.opp.Database.Laptop
import kotlinx.android.synthetic.main.activity_edit_laptop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditLaptopActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    private var laptopId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_laptop)
        setupListener()
        setupView()
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
            btn_updateLaptop.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    db.LaptopDao().updateLaptop(
                        Laptop(laptopId, txt_merk.text.toString(), Integer.parseInt(txt_stok.text.toString()), Integer.parseInt(txt_harga.text.toString()) )
                    )
                    finish()
                }
            }

    }

    fun setupView() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                btn_updateLaptop.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btn_saveLaptop.visibility = View.GONE
                btn_updateLaptop.visibility = View.GONE
                getLaptop()
            }
            Constant.TYPE_UPDATE -> {
                btn_saveLaptop.visibility = View.GONE
                getLaptop()
            }
        }
    }

    fun getLaptop() {
        laptopId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val laptops =  db.LaptopDao().getLaptop( laptopId )[0]
            txt_merk.setText( laptops.merk )
            txt_stok.setText( laptops.stok.toString() )
            txt_harga.setText( laptops.harga.toString() )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}