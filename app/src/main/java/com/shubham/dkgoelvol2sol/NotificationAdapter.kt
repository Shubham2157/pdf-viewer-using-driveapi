package com.shubham.dkgoelvol2sol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_list_layout.view.*


class NotificationAdapter (private val notificationData: Array<DataMem>) : RecyclerView.Adapter<NotificationAdapter.myViewHolder>() {

    var onClickListener: (String) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_list_layout, parent, false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationData.size
    }

    override fun onBindViewHolder(holder: myViewHolder, i: Int) {
//        var i = i + 2
//        i++
        val title = notificationData[0].getTitle()
        val link = notificationData[0].getLink()
        holder.bind(title!!,link!!, onClickListener)
    }

    class myViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(title: String, link: String, onclick: (String) -> Unit) {
            itemView.title.text = title
            itemView.pdfbtn.setOnClickListener {
                onclick(link)
            }
        }
    }
}