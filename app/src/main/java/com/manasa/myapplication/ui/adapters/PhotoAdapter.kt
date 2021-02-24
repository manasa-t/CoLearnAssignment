package com.manasa.myapplication.ui.adapters

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.manasa.myapplication.R
import com.manasa.myapplication.data.network.NetworkHelper
import com.manasa.myapplication.domain.entities.Photo
import com.manasa.myapplication.ui.common.OnPhotoClickedListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PhotoAdapter(val context: Context, val onPhotoClickedListener: OnPhotoClickedListener) :
   PagedListAdapter<Photo,PhotoAdapter.BaseViewHolder>(PhotoDiffCallback) {

    companion object {
        val PhotoDiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }

   /* val VIEW_TYPE_LOADING = 0
    val VIEW_TYPE_NORMAL = 1*/


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.layout_photo_grid, parent, false)
        return PhotoHolder(view)
    }


    override fun getItemCount(): Int {
        return super.getItemCount()
    }

   /* override fun getItemViewType(position: Int): Int {
        if (position == super.getItemCount() - 1) return VIEW_TYPE_LOADING
        else return VIEW_TYPE_NORMAL

    }*/

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        GlobalScope.launch {
            var bitmap = NetworkHelper.getImageBitmap(getItem(position)!!.thumbnailUrl)

            (context as Activity).runOnUiThread {
                if (holder is PhotoHolder) {
                    setPhoto(bitmap, holder.imageView)
                }
            }
        }
        ( holder as PhotoHolder).imageView.setOnClickListener {
            onPhotoClickedListener.onPhotoClicked(getItem(position)?.id!!)
        }

    }


    fun setPhoto(bitmap: Bitmap?, imageView: ImageView) {
        if (bitmap != null) imageView.setImageBitmap(bitmap)
        else imageView.setImageResource(R.drawable.ic_launcher_foreground)

    }

  /*  fun addPhotos(newPhotos: List<Photo>) {
        photos.toMutableList().addAll(newPhotos)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = false
        photos.toMutableList().add(Photo("", "null", "null"))
        notifyItemInserted(photos.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        var ph = photos.get(photos.size - 1)
        var list = photos.toMutableList()
        if (ph != null) {
            list.removeAt(photos.size - 1)
            notifyItemRemoved(photos.size - 1)
        }

    }*/

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class PhotoHolder(itemView: View) : BaseViewHolder(itemView) {
        var imageView = itemView.findViewById<AppCompatImageView>(R.id.iv_photo)
    }

    class ProgressViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var progress_bar = itemView.findViewById<ProgressBar>(R.id.progress_bar_grid_item)
    }
}

