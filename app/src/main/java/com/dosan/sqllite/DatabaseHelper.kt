package com.dosan.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        AlumnoContract.Companion.Entrada.NOMBRE_TABLA,
        null,
        AlumnoContract.Companion.VERSION
    ) {

    companion object {
        val CREATE_ALUMNO_TABLA = "CREATE TABLE " + AlumnoContract.Companion.Entrada.NOMBRE_TABLA +
                "(" + AlumnoContract.Companion.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY , " +
                AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE + " Text )"

        val REMOVE_ALUMNOS_TABLA =
            "DROP TABLE IF EXISTS " + AlumnoContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_ALUMNO_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_ALUMNOS_TABLA)
        onCreate(db)
    }

}