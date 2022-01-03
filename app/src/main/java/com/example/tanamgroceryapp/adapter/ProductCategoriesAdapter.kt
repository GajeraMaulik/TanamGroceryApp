package com.example.tanamgroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.R

class ProductCategoriesAdapter(private val productCategoriesList: MutableList<HomeCategoriesData>,var clickListener:ClickListener): RecyclerView.Adapter<ProductCategoriesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoriesAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_categories, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataModal=productCategoriesList[position]
        holder.catIcon.setImageResource(dataModal.catIcon)
        holder.catName.text=dataModal.catName
        holder.catItems.text= dataModal.catItems.toString()

        holder.itemView.setOnClickListener {
            clickListener.clickedItem(dataModal)
        }

    }

    override fun getItemCount(): Int {
        return productCategoriesList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val catIcon: ImageView =itemView.findViewById(R.id.categoriesIcon)
        val catName: TextView =itemView.findViewById(R.id.categoriesTitle)
        val catItems: TextView =itemView.findViewById(R.id.categoriesItems)
        val catCardview:CardView=itemView.findViewById(R.id.categoriesCardView)

    }
    interface ClickListener{
        fun clickedItem(homeCategoriesData: HomeCategoriesData)
    }
}


