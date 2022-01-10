package com.example.tanamgroceryapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.ApplicationInitialize
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.R

class PopularDealsAdapter(private val popularDealsList: MutableList<CardData>) :
    RecyclerView.Adapter<PopularDealsAdapter.MyviewHolder>() {

    lateinit var cardData: CardData
    lateinit var context:Context
    var id: Int = 0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_populardeals, parent, false)
        return MyviewHolder(view)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val data = popularDealsList[position]
        data.itemImage.let { holder.itemImage.setImageResource(it) }
        holder.itemName.text = StringBuilder().append(data.itemName)
        holder.originalPrice.text = StringBuilder("$").append(data.originalPrice)
        holder.ratingCounter.text = StringBuilder("").append(data.ratingCounter).toString()

        if (ApplicationInitialize.mShoppingCart.size > 0) {
            val getSingleData = ApplicationInitialize.mShoppingCart[data.id]
            if (getSingleData != null) {
                holder.tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                if (getSingleData.quantity > 0) {
                    holder.itemAdd.visibility = View.INVISIBLE
                    holder.itemQty.visibility = View.VISIBLE
                } else {
                    holder.itemAdd.visibility = View.VISIBLE
                    holder.itemQty.visibility = View.INVISIBLE
                }
            }
        } else {
            holder.tvQuantity.text = StringBuilder("").append(data.quantity)
        }

        holder.itemAdd.setOnClickListener {
            holder.itemAdd.visibility = View.INVISIBLE

            holder.itemQty.visibility = View.VISIBLE
            addCart(holder, data)
        }


        holder.btnAdd.setOnClickListener { addCart(holder, data) }

        holder.btnMinus.setOnClickListener { removeCart(holder, data) }
    }

    private fun addCart(holder: MyviewHolder, cardData: CardData) {

        if (ApplicationInitialize.mShoppingCart.size == 0) {
            cardData.quantity = cardData.quantity + 1
            holder.tvQuantity.text = StringBuilder("").append(cardData.quantity)

            ApplicationInitialize.mShoppingCart[cardData.id] = cardData
          //  total(holder)

        } else {
            val getSingleData = ApplicationInitialize.mShoppingCart[cardData.id]
            if (getSingleData != null) {
                getSingleData.quantity = getSingleData.quantity + 1
                holder.tvQuantity.text = StringBuilder("").append(getSingleData.quantity)

                ApplicationInitialize.mShoppingCart[getSingleData.id] = getSingleData

            } else {
                cardData.quantity = cardData.quantity + 1
                holder.tvQuantity.text = StringBuilder("").append(cardData.quantity)
                ApplicationInitialize.mShoppingCart[cardData.id] = cardData
                Log.d("maulik", "error")
            }
        }


    }

    private fun removeCart(holder: MyviewHolder, cardData: CardData) {
        if (ApplicationInitialize.mShoppingCart.size > 0) {
            val getSingleData = ApplicationInitialize.mShoppingCart[cardData.id]
            if (getSingleData != null) {
                getSingleData.quantity = getSingleData.quantity - 1
                if (getSingleData.quantity <= 0) {
                    Log.d("maulik", "full  0 ")
                    holder.itemAdd.visibility = View.VISIBLE
                    holder.itemQty.visibility = View.INVISIBLE
                    ApplicationInitialize.mShoppingCart.remove(getSingleData.id)
                } else {
                    Log.d("maulik", "full > 0 ")
                    holder.tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                    ApplicationInitialize.mShoppingCart[getSingleData.id] = getSingleData
                }

            }
        } else {
            if (cardData.quantity > 0) {
                Log.d("maulik", "null >0 ")
                cardData.quantity = cardData.quantity - 1
                holder.tvQuantity.text = StringBuilder("").append(cardData.quantity)
                ApplicationInitialize.mShoppingCart[cardData.id] = cardData
            } else {
                Log.d("maulik", "null  0 ")
                holder.itemAdd.visibility = View.VISIBLE
                holder.itemQty.visibility = View.INVISIBLE
                ApplicationInitialize.mShoppingCart.remove(cardData.id)
            }
        }


    }

    override fun getItemCount(): Int {
        return popularDealsList.size
    }

    class MyviewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemImage: ImageView = view.findViewById(R.id.itemImage)
        var itemName: TextView = view.findViewById(R.id.itemName)
        var originalPrice: TextView = view.findViewById(R.id.itemPrice)
        var itemQty: LinearLayout = view.findViewById(R.id.itemQty)
        var ratingCounter: TextView = view.findViewById(R.id.ratingCounter)
        var tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        var itemAdd: Button = view.findViewById(R.id.itemAdd)
        var itemFavorite: CheckBox = view.findViewById(R.id.itemFavorite)
        var btnAdd: ImageButton = view.findViewById(R.id.btnAdd)
        var btnMinus: ImageButton = view.findViewById(R.id.btnMinus)


    }
}






