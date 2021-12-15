package com.example.tanamgroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Constants
import com.example.tanamgroceryapp.Data.PopularDealsData
import com.example.tanamgroceryapp.Data.ShoppingCartData
import com.example.tanamgroceryapp.R

class PopularDealsAdapter(private val popularDealsList: MutableList<PopularDealsData>,):RecyclerView.Adapter<PopularDealsAdapter.MyviewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.populardeals_item,parent,false)
        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val data=popularDealsList[position]
        data?.itemImage?.let { holder.itemImage.setImageResource(it) }
        holder.itemName.text=StringBuilder().append(popularDealsList[position].itemName)
        holder.itemPrice.text=StringBuilder("$").append(popularDealsList[position].itemPrice)
        holder.ratingCounter.text= StringBuilder("").append(popularDealsList[position].ratingCounter).toString()
        holder.txtQuantity.text=StringBuilder("").append(popularDealsList[position].txtQuantity)

        holder.itemAdd.setOnClickListener {
            holder.itemAdd.visibility=View.INVISIBLE
            holder.itemQty.visibility=View.VISIBLE


        }
        holder.btnMinus.setOnClickListener { minusCartItem(holder,popularDealsList[position]) }
        holder.btnAdd.setOnClickListener { addCartItem(holder,popularDealsList[position]) }
    }

    private fun addCartItem(holder:MyviewHolder, popularDealsData: PopularDealsData) {
        popularDealsData.txtQuantity = popularDealsData.txtQuantity?.plus(1)
        holder.txtQuantity.text=StringBuilder("").append(popularDealsData.txtQuantity)

    }

    private fun minusCartItem(holder:MyviewHolder, popularDealsData: PopularDealsData) {
        if (popularDealsData.txtQuantity!! >1){
            popularDealsData.txtQuantity = popularDealsData.txtQuantity?.minus(1)
            holder.txtQuantity.text=StringBuilder("").append(popularDealsData.txtQuantity)
        }else{
            holder.itemAdd.visibility=View.VISIBLE
        }
    }


    override fun getItemCount():Int {
        return popularDealsList.size
    }
    class MyviewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        var itemImage:ImageView
        var itemName:TextView
        var itemPrice:TextView
        var itemFavorite:CheckBox
        var ratingCounter:TextView
        var txtQuantity:TextView
        var itemAdd:Button
        var btnAdd:ImageButton
        var btnMinus:ImageButton
        var itemQty:LinearLayout

        init {
            itemImage=itemview.findViewById(R.id.itemImage)
            itemName=itemview.findViewById(R.id.itemName)
            itemPrice=itemview.findViewById(R.id.itemPrice)
            ratingCounter=itemview.findViewById(R.id.ratingCounter)
            txtQuantity=itemview.findViewById(R.id.txtQuantity)
            itemQty=itemview.findViewById(R.id.itemQty)
            itemFavorite=itemview.findViewById(R.id.itemFavorite)
            itemAdd=itemview.findViewById(R.id.itemAdd)
            btnMinus=itemview.findViewById(R.id.btnMinus)
            btnAdd=itemview.findViewById(R.id.btnAdd)
        }

    }
}