package com.satria.oopcoba

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_pembeli.*

class PembeliActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembeli)

        val database = FirebaseDatabase.getInstance()

        var  myRef : DatabaseReference? = database.getReference("items")

        // Read Data
        myRef?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // looping ketika mengambil data
                val dataArray = ArrayList<Pembeli>()
                for (i in dataSnapshot.children){
                    val data = i.getValue(Pembeli::class.java)
                    data?.key = i.key
                    data?.let { dataArray.add(it) }
                }
                rvListPembeli.adapter = PembeliAdapter(dataArray, object : PembeliAdapter.OnClick {
                    override fun edit(pembeli: Pembeli?) {
                        val intent = Intent(this@PembeliActivity, FormPembeliActivity::class.java)
                        intent.putExtra("pembeli", pembeli)
                        startActivity(intent)
                    }

                    override fun delete(key: String?) {
                        AlertDialog.Builder(this@PembeliActivity).apply {
                            setTitle("Hapus ?")
                            setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                                myRef?.child(key.toString())?.removeValue()
//                                Toast.makeText(this@MainActivity, key, Toast.LENGTH_SHORT).show()
                            }
                            setNegativeButton("Tidak", { dialogInterface: DialogInterface, i: Int -> })
                        }.show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("tag", "Failed to read value.", error.toException())
            }
        })

        btAddPembeli.setOnClickListener {
            startActivity(Intent(this@PembeliActivity, FormPembeliActivity::class.java))
        }
    }
}