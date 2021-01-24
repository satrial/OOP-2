package com.satria.oopcoba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_form_pembeli.*

class FormPembeliActivity : AppCompatActivity() {
    var pembeli: Pembeli? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pembeli)

        val data = intent.getSerializableExtra("pembeli")
        var edit = true

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("items")

        if (data != null) {
            pembeli = data as Pembeli
            etPembeliNameEdit.setText(pembeli?.nama)
            etPembeliDescriptionEdit.setText(pembeli?.alamat)

            btActForm.setText("Edit")
        } else {
            btActForm.setText("Tambah")
            edit = false
        }

        btActForm.setOnClickListener {
            if (edit) {
                val changeData = HashMap<String, Any>()
                changeData.put("nama", etPembeliNameEdit.text.toString())
                changeData.put("alamat", etPembeliDescriptionEdit.text.toString())

                myRef.child(pembeli?.key.toString()).updateChildren(changeData)
                finish()
                startActivity(Intent(this, PembeliActivity::class.java))
            } else {
                val key = myRef.push().key

                val newPembeli = Pembeli()
                newPembeli.nama = etPembeliNameEdit.text.toString()
                newPembeli.alamat = etPembeliDescriptionEdit.text.toString()

                myRef.child(key.toString()).setValue(newPembeli)
                finish()
                startActivity(Intent(this, PembeliActivity::class.java))
            }
        }
    }
}