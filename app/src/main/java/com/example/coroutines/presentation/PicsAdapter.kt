package com.example.coroutines.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.R
import com.squareup.picasso.Picasso


class PicsAdapter(var data : List<PicView> = ArrayList()) : RecyclerView.Adapter<PicsAdapter.PicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_pic, parent, false)

        return PicViewHolder(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    class PicViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        fun bind(element : PicView) {
            Picasso.Builder(itemView.context).build().load(element.url).into(this.itemView.findViewById<ImageView>(R.id.imgPic))
        }
    }
}