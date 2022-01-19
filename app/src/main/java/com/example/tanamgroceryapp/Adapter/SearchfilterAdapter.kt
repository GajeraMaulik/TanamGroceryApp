package com.example.tanamgroceryapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.HomeCategoriesData
import com.example.tanamgroceryapp.R


class SearchfilterAdapter(private val categorieslist:MutableList<HomeCategoriesData>,var clickListener: HomeCategoriesAdapter.ClickListener): RecyclerView.Adapter<SearchfilterAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchfilterAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_searchcategories, parent, false)
        return SearchfilterAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchfilterAdapter.MyViewHolder, position: Int) {
        holder.catIcon.setImageResource(categorieslist[position].catIcon)
        holder.catName.text=categorieslist[position].catName
        holder.catItems.text=categorieslist[position].catItems.toString()
        holder.catbtn.setOnClickListener {
            clickListener.clickedItem(categorieslist[position])
        }
    }

    override fun getItemCount(): Int {
      return categorieslist.size
    }




    class MyViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val catIcon: ImageView =view.findViewById(R.id.itemIcon)
        val catName: TextView =view.findViewById(R.id.itemName)
        val catItems: TextView =view.findViewById(R.id.qtyItem)
        val catbtn:ImageButton=view.findViewById(R.id.nextBtn)

    }
    interface ClickListener{
        fun clickedItem(homeCategoriesData: HomeCategoriesData)
    }
}

