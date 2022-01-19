package com.example.tanamgroceryapp.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.ApplicationInitialize
import com.example.tanamgroceryapp.Constants
import com.example.tanamgroceryapp.Data.CardData
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.databinding.ItemProductsBinding


class ProductsAdapter(private val productslist: MutableList<CardData>,var clickListener: ProductsAdapter.ClickListener): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    lateinit var mContext: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductsBinding.inflate(inflater, parent, false)

        mContext = parent.context
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productslist[position], position)
    }

    override fun getItemCount(): Int = productslist.size

    inner class ViewHolder(val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CardData, position: Int) {

            binding.pItemImage.setImageResource(model.itemImage)
            binding.pItemName.text = model.itemName
            binding.pDiscountPrice.text = model.discountPrice.toString()
            binding.pOriginalPrice.text = model.originalPrice.toString()
            binding.pDiscountLabel.setImageResource(R.drawable.ic_price_tag)
            binding.pDiscount.text = model.discount.toString()
            binding.favItem.isChecked = model.itemRating
            binding.tvQuantity.text = model.quantity.toString()
            binding.pItemView.getViewById(R.id.pItemView)

            if (ApplicationInitialize.mShoppingCart.size > 0) {
                val getSingleData = ApplicationInitialize.mShoppingCart[model.id]
                if (getSingleData != null) {
                    binding.tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                    if (getSingleData.quantity > 0) {
                        binding.pCartBtn.visibility = View.INVISIBLE
                        binding.itemQty.visibility = View.VISIBLE
                    } else {
                        binding.pCartBtn.visibility = View.VISIBLE
                        binding.itemQty.visibility = View.INVISIBLE
                    }
                }
            } else {
                binding.tvQuantity.text = StringBuilder("").append(model.quantity)
            }


            binding.pCartBtn.setOnClickListener {
                binding.pCartBtn.visibility = View.INVISIBLE

                binding.itemQty.visibility = View.VISIBLE
                Toast.makeText(mContext, "Add To Cart ${model.itemName} ", Toast.LENGTH_LONG).show()
                addCart(binding,model)
            }
            val value: String = StringBuilder("$").append(binding.pOriginalPrice).toString()
            Constants.setStrike(binding.pOriginalPrice, value)

            binding.btnAdd.setOnClickListener { addCart(binding,model) }

            binding.btnMinus.setOnClickListener { removeCart(binding,model) }

            binding.pItemView.setOnClickListener {
                clickListener.clickedItem(model)
            }


        }

        private fun addCart(binding: ItemProductsBinding,cardData: CardData) {

            if (ApplicationInitialize.mShoppingCart.size == 0) {
                cardData.quantity = cardData.quantity + 1
                binding.tvQuantity.text = StringBuilder("").append(cardData.quantity)

                ApplicationInitialize.mShoppingCart[cardData.id] = cardData


            } else {
                val getSingleData = ApplicationInitialize.mShoppingCart[cardData.id]
                if (getSingleData != null) {
                    getSingleData.quantity = getSingleData.quantity + 1
                    binding.tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                    ApplicationInitialize.mShoppingCart[getSingleData.id] = getSingleData
                } else {
                    cardData.quantity = cardData.quantity + 1
                    binding.tvQuantity.text = StringBuilder("").append(cardData.quantity)
                    ApplicationInitialize.mShoppingCart[cardData.id] = cardData
                    Log.d("maulik", "error")
                }
            }


        }

        private fun removeCart(binding: ItemProductsBinding,cardData: CardData) {
            if (ApplicationInitialize.mShoppingCart.size > 0) {
                val getSingleData = ApplicationInitialize.mShoppingCart[cardData.id]
                if (getSingleData != null) {
                    getSingleData.quantity = getSingleData.quantity - 1
                    if (getSingleData.quantity < 1) {
                        Log.d("maulik", "full  0 ")
                        binding.pCartBtn.visibility = View.VISIBLE
                        binding.itemQty.visibility = View.INVISIBLE
                        Toast.makeText(mContext, "Remove the ${cardData.itemName} From Cart", Toast.LENGTH_LONG).show()
                        ApplicationInitialize.mShoppingCart.remove(getSingleData.id)
                    } else {
                        Log.d("maulik", "full > 0 ")
                        binding.tvQuantity.text = StringBuilder("").append(getSingleData.quantity)
                        ApplicationInitialize.mShoppingCart[getSingleData.id] = getSingleData
                    }

                }
            } else {
                if (cardData.quantity > 0) {
                    Log.d("maulik", "null >0 ")
                    cardData.quantity = cardData.quantity - 1
                    binding.tvQuantity.text = StringBuilder("").append(cardData.quantity)
                    ApplicationInitialize.mShoppingCart[cardData.id] = cardData
                } else {
                    Log.d("maulik", "null  0 ")
                    binding.pCartBtn.visibility = View.VISIBLE
                    binding.itemQty.visibility = View.INVISIBLE
                    ApplicationInitialize.mShoppingCart.remove(cardData.id)
                }
            }

        }
    }
    interface ClickListener{
        fun clickedItem(cardData: CardData)
        fun notifyDataSetChanged()
    }
}


