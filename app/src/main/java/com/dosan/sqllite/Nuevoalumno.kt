package com.dosan.sqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Nuevoalumno : AppCompatActivity() {

    var crud: AlumnoCrud? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevoalumno)
        val id = findViewById<EditText>(R.id.edId)
        val nombre = findViewById<EditText>(R.id.edNombre)
        val btn_add = findViewById<Button>(R.id.btn_add)

        crud = AlumnoCrud(this)
        btn_add.setOnClickListener {
            crud?.newAlumno(Alumno(id.text.toString(),nombre.text.toString()))
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}