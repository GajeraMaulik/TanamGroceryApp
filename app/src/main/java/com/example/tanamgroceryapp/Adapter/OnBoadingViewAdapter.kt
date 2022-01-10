package com.example.tanamgroceryapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.tanamgroceryapp.Data.OnBoadingData
import com.example.tanamgroceryapp.R

class OnBoadingViewAdapter(private var context:Context,private var OnBoadingDataList:List<OnBoadingData>): PagerAdapter() {
    override fun getCount(): Int {
        return  OnBoadingDataList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view:View=LayoutInflater.from(context).inflate(R.layout.activity_sreen,null)
        val imageView:ImageView = view.findViewById(R.id.imageView)
        val  title:TextView = view.findViewById(R.id.title)
        val desc:TextView = view.findViewById(R.id.desc)

        imageView.setImageResource(OnBoadingDataList[position].imageUrl)
        title.text=OnBoadingDataList[position].title
        desc.text=OnBoadingDataList[position].desc

        container.addView(view)
        return view
    }
}
