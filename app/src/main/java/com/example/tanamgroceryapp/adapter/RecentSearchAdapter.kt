package com.example.tanamgroceryapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamgroceryapp.Data.RecentSearchData
import com.example.tanamgroceryapp.R
import com.example.tanamgroceryapp.databinding.ItemRecentsearchBinding


class RecentSearchAdapter(
        private val recentlist: MutableList<RecentSearchData>,
        // val mOnItemClickListener: ItemClickListener
) :
        RecyclerView.Adapter<RecentSearchAdapter.ViewHolder>() {
    lateinit var mContext: Context
    // var mItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecentsearchBinding.inflate(inflater,parent,false)

        mContext = parent.context
        return ViewHolder(binding,recentlist)
    }

    override fun getItemCount(): Int = recentlist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  mItemClickListener = mOnItemClickListener
        holder.bind(recentlist[position],position)
    }

    inner class ViewHolder(val binding: ItemRecentsearchBinding, private val recentSearchData: MutableList<RecentSearchData>) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RecentSearchData,position: Int) {

           binding.timeIc.setImageResource(R.drawable.ic_recentsearch_time)
            binding.rsItemname.text=model.itemname
            binding.removeItemBtn.setImageResource(R.drawable.ic_recentsearch_close)
            binding.removeItemBtn.setOnClickListener {deleteItem(position)}


        }
    }
  //  @SuppressLint("NotifyDataSetChanged")
    private fun deleteItem(index: Int) {
        recentlist.removeAt(index)
        notifyDataSetChanged()

    }
}
