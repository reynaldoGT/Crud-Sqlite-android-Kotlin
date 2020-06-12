package com.dosan.sqllite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class AlumnoCrud(context: Context) {
    private var helper: DatabaseHelper? = null

    init {
        helper = DatabaseHelper(context)
    }

    fun newAlumno(item: Alumno) {
        //Funcion que nso permite escribir en una base de datos
        val db: SQLiteDatabase = helper?.writableDatabase!!
        //Mapeo con los datos a insertar
        val values = ContentValues()
        values.put(AlumnoContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        //Insertamos una fila en la tabla
        val newRowId = db.insert(AlumnoContract.Companion.Entrada.NOMBRE_TABLA, null, values)
        db.close()
    }

    fun getAlumnos(): ArrayList<Alumno> {
        var items: ArrayList<Alumno> = ArrayList()

        // Abrir la cbase de datos en mode de lectura
        var db: SQLiteDatabase = helper?.readableDatabase!!

        //Especifiar columnas que quiero consultar
        var columnas = arrayOf(
            AlumnoContract.Companion.Entrada.COLUMNA_ID,
            AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        //Crear un cros para recorre la tabla
        var c: Cursor = db.query(
            AlumnoContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )
        // Hacer un recorrido del cursor en la tabla
        while (c.moveToNext()) {
            items.add(
                Alumno(
                    c.getString((c.getColumnIndexOrThrow(AlumnoContract.Companion.Entrada.COLUMNA_ID))),
                    c.getString((c.getColumnIndexOrThrow(AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE)))
                )
            )
        }
        //Cerrar DB
        db.close()
        return items
    }

    fun getAlumno(id: String): Alumno {
        var item: Alumno? = null
        // Abrir la cbase de datos en mode de lectura
        var db: SQLiteDatabase = helper?.readableDatabase!!

        //Especifiar columnas que quiero consultar
        val values = ContentValues()
        var columnas = arrayOf(
            AlumnoContract.Companion.Entrada.COLUMNA_ID,
            AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE
        )
        //Crear un cros para recorre la tabla
        var c: Cursor = db.query(
            AlumnoContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " id = ?",
            arrayOf(id),
            null,
            null,
            null
        )
        // Hacer un recorrido del cursor en la tabla
        while (c.moveToNext()) {
            item = Alumno(
                c.getString((c.getColumnIndexOrThrow(AlumnoContract.Companion.Entrada.COLUMNA_ID))),
                c.getString((c.getColumnIndexOrThrow(AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE)))
            )

        }
        //Cerrar DB
        db.close()
        return item!!
    }

    fun updateAlumno(item: Alumno) {
        var db: SQLiteDatabase = helper?.readableDatabase!!

        //Especifiar columnas que quiero consultar
        val values = ContentValues()
        values.put(AlumnoContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnoContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)
        db.update(
            AlumnoContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            " id = ?",
            arrayOf(item.id)
        )
        db.close()
    }

    fun deleteAlumno(item: Alumno) {
        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(
            AlumnoContract.Companion.Entrada.NOMBRE_TABLA,
            " id = ?",
            arrayOf(item.id)
        )
        db.close()
    }
}