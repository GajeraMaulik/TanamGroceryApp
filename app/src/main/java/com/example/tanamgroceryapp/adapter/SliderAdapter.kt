package com.example.tanamgroceryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.Data.SliderData
import com.makeramen.roundedimageview.RoundedImageView

class SliderAdapter internal constructor(
    sliderData: MutableList<SliderData>,
    viewPager2:ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    private val sliderData: List<SliderData>
    private val viewPager2:ViewPager2

    init {
        this.sliderData = sliderData
        this.viewPager2=viewPager2
    }

    class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: RoundedImageView = itemView.findViewById(R.id.imageSlide)

        fun image(sliderData: SliderData) {
            imageView.setImageResource(sliderData.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_slider, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.image(sliderData[position])
        if (position == sliderData.size - 2) {
            viewPager2.post(runnable)
        }
    }
    override fun getItemCount(): Int {
        return sliderData.size
    }
    private val runnable= Runnable {
        sliderData.addAll(sliderData)
        notifyDataSetChanged()
    }



}





