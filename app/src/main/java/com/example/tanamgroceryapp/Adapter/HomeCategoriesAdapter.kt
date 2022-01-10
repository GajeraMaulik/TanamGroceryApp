package com.example.tanamgroceryapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.Data.HomeCategoriesData

class HomeCategoriesAdapter(private val homeCategoriesList: MutableList<HomeCategoriesData>,var clickListener:ClickListener):RecyclerView.Adapter<HomeCategoriesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_home_categories,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataModal=homeCategoriesList[position]
        holder.catIcon.setImageResource(dataModal.catIcon)
        holder.catName.text=dataModal.catName
        holder.catItems.text= dataModal.catItems.toString()

        holder.itemView.setOnClickListener {
            clickListener.clickedItem(dataModal)
        }

    }


    override fun getItemCount(): Int {
        return homeCategoriesList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val catIcon:ImageView=itemView.findViewById(R.id.cat_icon)
        val catName:TextView =itemView.findViewById(R.id.cat_name)
        val catItems:TextView=itemView.findViewById(R.id.cat_items)
       // val hcCardView:CardView=itemView.findViewById(R.id.hcCardView)
    }
    interface ClickListener{
        fun clickedItem(homeCategoriesData: HomeCategoriesData)
    }

}