package com.example.myapplication

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class EmailAdapter(context: Context, private val emails: List<EmailItem>) :
    ArrayAdapter<EmailItem>(context, 0, emails) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val holder: ViewHolder

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(
                R.layout.item_email,
                parent,
                false
            )

            holder = ViewHolder(
                initial = itemView.findViewById(R.id.initialTextView),
                sender = itemView.findViewById(R.id.senderTextView),
                subject = itemView.findViewById(R.id.subjectTextView),
                preview = itemView.findViewById(R.id.previewTextView),
                time = itemView.findViewById(R.id.timeTextView),
                star = itemView.findViewById(R.id.starIcon)
            )

            itemView.tag = holder
        } else {
            holder = itemView.tag as ViewHolder
        }

        val email = emails[position]

        // Set initial circle background
        holder.initial.apply {
            backgroundTintList = ColorStateList.valueOf(email.backgroundColor)
            setText(email.initial)
        }

        // Set other views
        holder.sender.setText(email.sender)
        holder.subject.setText(email.subject)
        holder.preview.setText(email.preview)
        holder.time.setText(email.time)
        holder.star.visibility = if (email.isStarred) View.VISIBLE else View.INVISIBLE

        return itemView!!
    }

    private data class ViewHolder(
        val initial: TextView,
        val sender: TextView,
        val subject: TextView,
        val preview: TextView,
        val time: TextView,
        val star: ImageView
    )
}