package com.dosan.sqllite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var lista: RecyclerView? = null
    var adaptador: AdaptadorCustom? = null
    var layout_Manager: RecyclerView.LayoutManager? = null
    var alumnos: ArrayList<Alumno>? = null

    var crud: AlumnoCrud? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        // para poder ponder las celdas una debajo de la otra
        layout_Manager = LinearLayoutManager(this)
        lista?.layoutManager = layout_Manager
        fab.setOnClickListener {
            startActivity(Intent(this, Nuevoalumno::class.java))
        }
        //Asignando a nuestra arrayList de alumnos
        crud = AlumnoCrud(this)
        alumnos = crud?.getAlumnos()
        adaptador = AdaptadorCustom(alumnos!!, object : ClickListener {
            override fun onclick(vista: View, index: Int) {
                //CLick
                val intent = Intent(applicationContext, AlumnoDetalle::class.java)
                intent.putExtra("ID", alumnos!!.get(index).id)
                startActivity(intent)
            }
        }, object : LongClickListener {
            override fun longClik(vista: View, index: Int) {
            }
        })
        lista?.adapter = adaptador


    }
}