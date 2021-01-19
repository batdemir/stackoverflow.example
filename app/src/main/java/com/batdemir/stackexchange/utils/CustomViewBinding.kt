package com.batdemir.stackexchange.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class CustomViewBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("app:setImage")
        fun ShapeableImageView.bindImage(res: String?) {
            if (res.isNullOrEmpty())
                return
            Glide
                    .with(this.context)
                    .load(res)
                    .into(this)
        }

        @JvmStatic
        @BindingAdapter("app:setAdapter")
        fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("app:setAdapterViewPagerRecyclerView")
        fun ViewPager2.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
            this.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("app:setAdapterViewPagerFragmentState")
        fun ViewPager2.bindFragmentStateAdapter(adapter: FragmentStateAdapter?) {
            if (adapter == null)
                return
            this.adapter = adapter
        }
    }
}