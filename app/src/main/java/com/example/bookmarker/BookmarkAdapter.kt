package com.example.bookmarker

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Random

class BookmarkAdapter(
    private val context: Context,
    private val bookmarks: List<Bookmark>,
    val listner: onClickListerner

) : BaseAdapter() {

    override fun getCount(): Int = bookmarks.size

    override fun getItem(position: Int): Any = bookmarks[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.bookmark_item, parent, false)
        val item = bookmarks[position]
        val title = view.findViewById<TextView>(R.id.tvTitle)
        val description = view.findViewById<TextView>(R.id.tvDes)
        val url = view.findViewById<TextView>(R.id.tvUrl)
        val date = view.findViewById<TextView>(R.id.tvDate)
        val category = view.findViewById<TextView>(R.id.tvCategory)
        val tvIcon = view.findViewById<TextView>(R.id.tvIcon)
        val btnEdit = view.findViewById<ImageButton>(R.id.btnEdit)
        val btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)
        val parentLayout = view.findViewById<LinearLayout>(R.id.parentLayout)


        title.text = item.tittle
        description.text = item.description
        url.text = item.url
        date.text = item.date
        category.text = "Category : ${item.category}"

        setRandomBackgroundWithInitial(tvIcon, item.tittle)

        btnEdit.setOnClickListener {
            listner.onEdit(item)
        }
        btnDelete.setOnClickListener {
            listner.onDelete(item)
        }
        parentLayout.setOnLongClickListener {
            listner.onLongPress(item)
            true
        }
        parentLayout.setOnClickListener {
            listner.onClick(item)
        }




        return view
    }

    private fun setRandomBackgroundWithInitial(textView: TextView, input: String) {
        val firstChar = input.trim().firstOrNull()?.uppercaseChar() ?: '?'
        textView.text = firstChar.toString()

        val bgColor = getRandomPastelColor()  // Light colors look better with black text

        val shape = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(bgColor)
        }

        textView.background = shape
        textView.setTextColor(Color.BLACK)
    }

    private fun getRandomPastelColor(): Int {
        val random = Random()
        val baseColor = 150  // Avoid too light or too dark colors
        val red = baseColor + random.nextInt(105)
        val green = baseColor + random.nextInt(105)
        val blue = baseColor + random.nextInt(105)
        return Color.rgb(red, green, blue)
    }

    interface onClickListerner {
        fun onEdit(bookmark: Bookmark)
        fun onDelete(bookmark: Bookmark)
        fun onClick(bookmark: Bookmark)
        fun onLongPress(bookmark: Bookmark)

    }

}