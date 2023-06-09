package com.task.jobzella.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.domain.entity.Result
import com.task.jobzella.adapter.ImagesAdapter.ImagesViewHolder
import com.task.jobzella.databinding.ImageItemBinding
import com.task.jobzella.helper.io
import com.task.jobzella.helper.ui
import com.task.jobzella.helper.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL


class ImagesAdapter : RecyclerView.Adapter<ImagesViewHolder>() {
    private var list: List<Result> = ArrayList()
    fun setList(list: List<Result>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(list[holder.absoluteAdapterPosition])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ImagesViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result?) {
            /* With Glide Library*/
              binding.ivImage.url(item?.urls?.regular)
            /* With out Third party libraries*/
//            if (item?.urls?.bitMapValue==null)
//            CoroutineScope(Dispatchers.IO).launch {
//
//                val bitmap = downloadBitmap(item?.urls?.regular ?: "")
//                withContext(Dispatchers.Main) {
//                    if (bitmap != null) {
//                        item?.urls?.bitMapValue=bitmap
//                    }
//                    binding.ivImage.setImageBitmap(bitmap)
//                }
//            }
//          else binding.ivImage.setImageBitmap(item.urls.bitMapValue)

        }
    }

    private fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val conn = URL(imageUrl).openConnection()
            conn.connect()
            val inputStream = conn.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: Exception) {
            Log.e("error", "Exception $e")
            null
        }
    }
}