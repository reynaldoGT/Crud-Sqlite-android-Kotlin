package com.dosan.sqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AlumnoDetalle : AppCompatActivity() {

    var crud: AlumnoCrud? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno_detalle)

        val id = findViewById<EditText>(R.id.edId)
        val nombre = findViewById<EditText>(R.id.edNombre)
        val btn_add = findViewById<Button>(R.id.btn_add)
        val btn_actualizar = findViewById<Button>(R.id.btnActualizar)
        val btn_aeliminar = findViewById<Button>(R.id.btneliminar)

        val index = intent.getStringExtra("ID")
        crud = AlumnoCrud(this)
        val alumno = crud?.getAlumno(index)
        id.setText(alumno!!.id, TextView.BufferType.EDITABLE)
        nombre.setText(alumno.nombre, TextView.BufferType.EDITABLE)

        btn_actualizar.setOnClickListener {
            crud?.updateAlumno(Alumno(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
        }
        btn_aeliminar.setOnClickListener {
            crud?.deleteAlumno(Alumno(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}