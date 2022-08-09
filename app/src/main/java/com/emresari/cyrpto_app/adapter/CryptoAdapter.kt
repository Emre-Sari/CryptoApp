package com.emresari.cyrpto_app.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emresari.cyrpto_app.databinding.RecyclerRowBinding
import com.emresari.cyrpto_app.model.CryptoModel
import java.lang.reflect.Array
import kotlin.random.Random

class CryptoAdapter (var array:ArrayList<CryptoModel>) : RecyclerView.Adapter<CryptoHolder>() {
     var color_array= arrayOf("#FFADE6","#F8B898","#00CED1","#896799","#E6D50A","#FF0076")




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        var binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.name.text=array.get(position).currency
        holder.binding.price.text=array.get(position).price
        holder.itemView.setBackgroundColor(Color.parseColor(color_array.get(position%6)))
    }

    override fun getItemCount(): Int {
        return array.size
    }
}