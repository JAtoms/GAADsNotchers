package com.example.gadsnotchers.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gadsnotchers.R
import com.example.gadsnotchers.domain.HoursDataClass
import com.example.gadsnotchers.domain.IQDataClass

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("hoursDataClass")
fun bindHourRecyclerView(recyclerview: RecyclerView, hoursDataClass: List<HoursDataClass>?) {
    val adapter = recyclerview.adapter as HoursRecyclerViewAdapter
    adapter.notifyDataSetChanged()
    adapter.submitList(hoursDataClass)
}

@BindingAdapter("iQDataClass")
fun iQRecyclerView(recyclerview: RecyclerView, iqDataClass: List<IQDataClass>?){
    val adapter = recyclerview.adapter as IQRecyclerViewAdapter
    adapter.notifyDataSetChanged()
    adapter.submitList(iqDataClass)
}