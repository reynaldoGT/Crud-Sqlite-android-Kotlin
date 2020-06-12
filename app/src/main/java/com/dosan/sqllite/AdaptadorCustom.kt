package com.dosan.sqllite

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(
    items: ArrayList<Alumno>,
    var listener: ClickListener,
    var longClickListener: LongClickListener
) :
    RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items: ArrayList<Alumno>? = null
    var multiSeleccion = false
    var itemsSeleccionados: ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.template_alumno, parent, false)
        viewHolder = ViewHolder(vista, listener, longClickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.nombre?.text = item?.nombre
        holder.id?.text = item?.id


        if(itemsSeleccionados?.contains(position)!!){
            holder.vista.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarActionMode() {
        multiSeleccion = true
    }

    fun destruirActionMode() {
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode() {

        for (item in itemsSeleccionados!!) {
            itemsSeleccionados?.remove(item)
        }

        //Elimar elemento selccioado
        multiSeleccion = false
    }
    fun seleccionarItem(index:Int){
        if(multiSeleccion){
            if(itemsSeleccionados?.contains(index)!!){
                itemsSeleccionados?.remove(index)
            }else{
                itemsSeleccionados?.add(index)
            }
            notifyDataSetChanged()
        }
    }
    fun obtenerItemsSeleccionados():Int{
        return itemsSeleccionados?.count()!!+1
    }

    fun eliminarSeccionados() {
        if(itemsSeleccionados?.count()!!>0){
            var itemsEliminados =ArrayList<Alumno>()

            for (index in itemsSeleccionados!!){
                itemsEliminados.add(items?.get(index)!!)
            }
            items?.removeAll(itemsEliminados)
            itemsSeleccionados?.clear()
        }
    }

    class ViewHolder(
        vista: View,
        listener: ClickListener,
        longClickListener: LongClickListener
    ) : RecyclerView.ViewHolder(vista),
        View.OnClickListener, View.OnLongClickListener {
        var vista = vista

        var nombre: TextView? = null
        var id: TextView? = null

        var listener: ClickListener? = null
        var longListener: LongClickListener? = null

        init {

            nombre = vista.findViewById(R.id.tv_nombre)
            id = vista.findViewById(R.id.tv_id)

            // agregando la variable para poder hacerle click
            this.listener = listener
            this.longListener = longClickListener
            // asignar el evento click normal
            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onclick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClik(v!!, adapterPosition)
            return true
        }

    }

}