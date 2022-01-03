package com.example.tanamgroceryapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.ApplicationInitialize
import com.example.tanamgroceryapp.Constants
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.interfaces.ItemClickListner


class ShoppingCartAdapter(private val cartItemList: MutableList<CardData>, val context: Context, val mItemClickListner: ItemClickListner) :

    RecyclerView.Adapter<ShoppingCartAdapter.MyviewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shoppingcart, parent, false)
        return MyviewHolder(view)
    }


    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val data = cartItemList[position]
        data.itemImage.let { holder.itemImage.setImageResource(it) }
        holder.productTitle.text = StringBuilder("").append(data.itemName)
        holder.categoriesTitle.text = StringBuilder("").append(data.categoriesTitle)
        holder.discountPrice.text = StringBuilder("$").append(data.discountPrice).toString()
        holder.originalPrice.text = java.lang.StringBuilder("$").append(data.originalPrice).toString()
        holder.tvQuantity.text = StringBuilder("").append(data.quantity)
        holder.btnMinus.setOnClickListener { removeCart(holder, data)
          mItemClickListner.onClickItem(0)}
        holder.btnAdd.setOnClickListener {
            addCart(holder, data)
            mItemClickListner.onClickItem(0)
        }

        val value: String =
            StringBuilder("$").append(cartItemList[position].originalPrice).toString()
        Constants.setStrike(holder.originalPrice, value)


    }


    private fun addCart(holder: MyviewHolder, cardData: CardData) {
        cardData.quantity = cardData.quantity + 1
        holder.tvQuantity.text = StringBuilder("").append(cardData.quantity)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun removeCart(holder: MyviewHolder, cardData: CardData) {

        if (cardData.quantity > 1) {
            cardData.quantity = cardData.quantity - 1
            holder.tvQuantity.text = StringBuilder("").append(cardData.quantity)
        } else {
            Toast.makeText(context, "Remove ${cardData.itemName}", Toast.LENGTH_LONG).show()
            ApplicationInitialize.mShoppingCart.remove(cardData.id)
            cartItemList.remove(cardData)
            notifyDataSetChanged()
        }

    }



    override fun getItemCount(): Int {
        return cartItemList.size
    }




    class MyviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.slItemImage)
        var productTitle: TextView = view.findViewById(R.id.productTitle)
        var categoriesTitle: TextView = view.findViewById(R.id.categoriesTitle)
        var discountPrice: TextView = view.findViewById(R.id.discountPrice)
        var originalPrice: TextView = view.findViewById(R.id.originalPrice)
        var tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        var itemQty: LinearLayout = view.findViewById(R.id.itemQty)
        var btnAdd: ImageButton = view.findViewById(R.id.scAddBtn)
        var btnMinus: ImageButton = view.findViewById(R.id.scMinusBtn)


    }

}