package com.box.talana.presentation.features.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.box.talana.R
import com.box.talana.core.extension.dateFormat
import com.box.talana.databinding.ItemFeedBinding
import com.box.talana.domian.model.Feed
import com.box.talana.presentation.util.ConfigUI
import com.bumptech.glide.Glide

class FeedAdapter constructor(
    private val feeds: List<Feed>,
    private val onClickListener: (Feed) -> Unit
) : RecyclerView.Adapter<FeedAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(feeds[position], onClickListener)
    }

    override fun getItemCount(): Int = feeds.size


    inner class CustomViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemFeedBinding.bind(view)


        fun bind(model: Feed, onClickListener: (Feed) -> Unit) {

            binding.titleText.text  = model.title

            val dateTimeFormatter = model.published.dateFormat(ConfigUI.DATE_FORMAT_EIGHTEEN).dateFormat(ConfigUI.DATE_FORMAT_NINETEEN)

            binding.dateText.text= "${itemView.context.getString(R.string.published)}: $dateTimeFormatter"

            Glide
                .with(itemView.context)
                .load(model.image)
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .into(binding.feedImage);


            itemView.setOnClickListener {
                onClickListener(model)
            }

        }

    }
}