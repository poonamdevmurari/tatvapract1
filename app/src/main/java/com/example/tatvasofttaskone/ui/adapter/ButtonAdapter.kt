package com.example.tatvasofttaskone.ui.adapter

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tatvasofttaskone.R
import com.example.tatvasofttaskone.data.model.CustomButton
import com.example.tatvasofttaskone.databinding.ListItemBinding
import com.example.tatvasofttaskone.ui.`interface`.OnItemClickListener

class ButtonAdapter(private val context: Context, private var buttons: MutableList<CustomButton>, private val onItemClickListener :OnItemClickListener ) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ButtonAdapter.ViewHolder, position: Int) {
        val button = buttons[position]
        if(button.isClickable){
            val handler = Handler()
            var random: Long = (0..1000).random().toLong()
            handler.postDelayed({
                holder.binding.button.background.colorFilter = BlendModeColorFilter(button.buttonColor, BlendMode.MULTIPLY)

            }, random)
        }else{
            holder.binding.button.background.colorFilter = BlendModeColorFilter(button.buttonColor, BlendMode.MULTIPLY)

        }
        holder.binding.button.isClickable = button.isClickable



    }

    override fun getItemCount(): Int =
        buttons.size


    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        init{
            binding.button.setOnClickListener{
                onItemClickListener.onItemClick(buttons[adapterPosition], adapterPosition)
            }
        }

    }
}