package com.example.bookmarker

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class BookmarkDetailDialogFragment(private val bookmark: Bookmark) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val view = requireActivity().layoutInflater.inflate(R.layout.fragment_bookmark_detail_dialog, null)

        val tvUrl = view.findViewById<TextView>(R.id.tvUrl)
        tvUrl.text = bookmark.url

        // To open the URL in the internal browser by clicking the url(?)
        tvUrl.setOnClickListener {
            var url = bookmark.url
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }


        view.findViewById<TextView>(R.id.tvTitle).text = bookmark.tittle
        //view.findViewById<TextView>(R.id.tvUrl).text = bookmark.url
        view.findViewById<TextView>(R.id.tvDescription).text = "Description : ${bookmark.description}"
        view.findViewById<TextView>(R.id.tvCategory).text = "Category : ${bookmark.category}"
        view.findViewById<TextView>(R.id.tvDate).text = "Date: ${bookmark.date}"




        val customTitle = createDialogTitle(requireContext(), "Bookmark Details")

        return AlertDialog.Builder(requireContext())
            .setCustomTitle(customTitle)
            .setView(view)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
    }//onCreate


    fun createDialogTitle(context: Context, titleText: String): View {
        val customTitle = LayoutInflater.from(context).inflate(R.layout.dialog_title, null)
        val titleTextView = customTitle.findViewById<TextView>(R.id.dialogTitleText)
        titleTextView.text = titleText
        return customTitle
    }

}//main
