package com.example.tanamgroceryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.R

class ProductCategoriesAdapter(private val productCategoriesList: MutableList<HomeCategoriesData>): RecyclerView.Adapter<ProductCategoriesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoriesAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_categories_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.catIcon.setImageResource(productCategoriesList[position].catIcon)
        holder.catName.text=productCategoriesList[position].catName
        holder.catItems.text= productCategoriesList[position].catItems.toString()

    }

    override fun getItemCount(): Int {
        return productCategoriesList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val catIcon: ImageView =itemView.findViewById(R.id.pccatIcon)
        val catName: TextView =itemView.findViewById(R.id.pccatName)
        val catItems: TextView =itemView.findViewById(R.id.pccatItems)
        val pcCardview:CardView=itemView.findViewById(R.id.pcCardView)

    }
}


