package com.satria.oopcoba
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.satria.oopcoba.Database.AppRoomDB
import com.satria.oopcoba.Database.Constant
import com.satria.oopcoba.Database.User
import com.satria.opp.Database.Laptop
import kotlinx.android.synthetic.main.activity_laptop.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaptopActivity : AppCompatActivity() {

    val db by lazy { AppRoomDB(this) }
    lateinit var laptopAdapter: LaptopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptop)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadLaptop()
    }

    fun loadLaptop() {
        CoroutineScope(Dispatchers.IO).launch {
            val allLaptop = db.LaptopDao().getAllLaptop()
            Log.d("Laptopctivity", "dbResponse: $allLaptop")
            withContext(Dispatchers.Main) {
                laptopAdapter.setData(allLaptop)
            }
        }
    }

    fun setupListener() {
        btn_createLaptop.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun setupRecyclerView() {
        laptopAdapter = LaptopAdapter(arrayListOf(), object: LaptopAdapter.OnAdapterListener {
            override fun onClick(laptop: Laptop) {
                intentEdit(laptop.id, Constant.TYPE_READ)
            }

            override fun onDelete(laptop: Laptop) {
                deleteDialog(laptop)
            }

        })
        list_laptop.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = laptopAdapter
        }
    }

    fun intentEdit(laptopId: Int, intentType: Int ) {
        startActivity(
            Intent(applicationContext, EditLaptopActivity::class.java)
                .putExtra("intent_id", laptopId)
                .putExtra("intent_type", intentType)
        )
    }

    private fun deleteDialog(laptop: Laptop) {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus data ini?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                dialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.LaptopDao().deleteLaptop(laptop)
                    loadLaptop()
                }
            }
        }
        alertDialog.show()
    }
}