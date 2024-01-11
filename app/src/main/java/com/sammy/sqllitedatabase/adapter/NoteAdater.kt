package com.sammy.sqllitedatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sammy.sqllitedatabase.databinding.ItemRvBinding
import com.sammy.sqllitedatabase.model.User


class NoteAdater(val list: ArrayList<User>) :
    RecyclerView.Adapter<NoteAdater.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(user: User, position: Int) {
            itemRvBinding.nameText.text = user.name
            itemRvBinding.numberText.text = user.number

            /*itemRvBinding.root.setOnClickListener {
                click.onclick(position)
            }*/
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

    interface Click {
        fun onclick(position: Int)
    }

}