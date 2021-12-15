package com.example.tanamgroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Constants
import com.example.tanamgroceryapp.Data.ShoppingCartData
import com.example.tanamgroceryapp.R


class ShoppingCartAdapter(private val itemList: MutableList<ShoppingCartData>): RecyclerView.Adapter<ShoppingCartAdapter.MyviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoppingcart_item, parent, false)
        return MyviewHolder(view)
    }



    override fun onBindViewHolder(holder: ShoppingCartAdapter.MyviewHolder, position: Int) {
        val data=itemList[position]
        data.itemImage?.let { holder.itemImage.setImageResource(it) }
        holder.productTitle.text=StringBuilder("").append(itemList[position].productTitle)
        holder.categoriesTitle.text=StringBuilder("").append(itemList[position].categoriesTitle)
        holder.discountPrice.text= StringBuilder("$").append(itemList[position].discountPrice).toString()
        holder.txtQuantity.text=StringBuilder("").append(itemList[position].txtQuantity)
        holder.btnMinus.setOnClickListener { minusCartItem(holder, itemList[position]) }
        holder.btnAdd.setOnClickListener { addCartItem(holder, itemList[position]) }

        val value:String=StringBuilder("$").append(itemList[position].originalPrice).toString()
        Constants.setStrike( holder.originalPrice,value)


    }


    private fun addCartItem(holder: ShoppingCartAdapter.MyviewHolder, shoppingCartData: ShoppingCartData) {
        shoppingCartData.txtQuantity = shoppingCartData.txtQuantity?.plus(1)
        holder.txtQuantity.text=StringBuilder("").append(shoppingCartData.txtQuantity)

    }

    private fun minusCartItem(holder: ShoppingCartAdapter.MyviewHolder, shoppingCartData: ShoppingCartData) {
        if(shoppingCartData.txtQuantity!! >1) {
            shoppingCartData.txtQuantity = shoppingCartData.txtQuantity?.minus(1)
            holder.txtQuantity.text = StringBuilder("").append(shoppingCartData.txtQuantity)
        }else{
            itemList.remove(shoppingCartData)
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyviewHolder(view: View):RecyclerView.ViewHolder(view) {
        var itemImage:ImageView
        var productTitle:TextView
        var categoriesTitle:TextView
        var discountPrice:TextView
        var originalPrice:TextView
        var txtQuantity:TextView
        var itemQty:LinearLayout
        var btnAdd:ImageButton
        var btnMinus:ImageButton


        init {
            itemImage=view.findViewById(R.id.slItemImage)
            productTitle=view.findViewById(R.id.productTitle)
            categoriesTitle=view.findViewById(R.id.categoriesTitle)
            discountPrice=view.findViewById(R.id.discountPrice)
            originalPrice=view.findViewById(R.id.originalPrice)
            itemQty=view.findViewById(R.id.itemQty)
            txtQuantity=view.findViewById(R.id.txtQuantity)
            btnMinus=view.findViewById(R.id.btnMinus)
            btnAdd=view.findViewById(R.id.btnAdd)
        }

    }

}