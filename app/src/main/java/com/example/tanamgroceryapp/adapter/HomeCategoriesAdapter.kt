package com.example.tanamgroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.Data.HomeCategoriesData

class HomeCategoriesAdapter(private val homeCategoriesList: MutableList<HomeCategoriesData>):RecyclerView.Adapter<HomeCategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.home_categories_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.catIcon.setImageResource(homeCategoriesList[position].catIcon)
        holder.catName.text=homeCategoriesList[position].catName
        holder.catItems.text= homeCategoriesList[position].catItems.toString()

    }


    override fun getItemCount(): Int {
        return homeCategoriesList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val catIcon:ImageView=itemView.findViewById(R.id.cat_icon)
        val catName:TextView =itemView.findViewById(R.id.cat_name)
        val catItems:TextView=itemView.findViewById(R.id.cat_items)
    }

}