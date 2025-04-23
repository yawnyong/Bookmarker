package com.example.bookmarker

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var llNoData: LinearLayout
    private lateinit var adapter: BookmarkAdapter
    lateinit var context: Context
    lateinit var prefManager: PrefManager
    private var bookmarksList: MutableList<Bookmark> = mutableListOf()
    private lateinit var spinnerFilter: Spinner
    private lateinit var itemList: Array<String>
    private var selectedCategory: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //add in the tool bar
        var toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //setup GUI
        setupGUI()

        //adapter
        setupBookmarkAdapter()

        //Category filter
        setupSpinnerFilter()

    }//onCreate

    private fun setupSpinnerFilter(){

        itemList = resources.getStringArray(R.array.category_options)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFilter.adapter = adapter

        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedCategory = if (position == 0) "" else itemList[position]
                filterBookmarksByCategory()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    private fun filterBookmarksByCategory() {
        val allBookmarks = prefManager.getBookmarks()
        bookmarksList.clear()

        if (selectedCategory.isEmpty()) {
            bookmarksList.addAll(allBookmarks)
        } else {
            bookmarksList.addAll(allBookmarks.filter { it.category == selectedCategory })
        }

        llNoData.visibility = if (bookmarksList.isEmpty()) View.VISIBLE else View.GONE
        listView.visibility = if (bookmarksList.isEmpty()) View.GONE else View.VISIBLE

        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        bookmarksList.clear()
        filterBookmarksByCategory()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        //splash screen
        val splashItem = menu?.findItem(R.id.menu_enable_splash)
        splashItem?.isChecked = prefManager.isSplashEnabled()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            //Add Bookmark
            R.id.menu_add_bookmark -> {
                startActivity(Intent(context, AddBookmark::class.java))
                return true
            }

            //Clear All
            R.id.menu_clear_all -> {
                bookmarksList.clear()
                llNoData.visibility = View.VISIBLE
                listView.visibility = View.GONE
                prefManager.clearBookmarks()
                adapter.notifyDataSetChanged()
                return true
            }

            //About
            R.id.menu_about -> {
                val dialog = AboutDialogFragment()
                dialog.show(supportFragmentManager, "AboutDialog")
                return true
            }

            //Enable Splash
            R.id.menu_enable_splash -> {
                val newState = !item.isChecked
                item.isChecked = newState
                prefManager.setSplashEnabled(newState)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBookmarkAdapter(){
        adapter = BookmarkAdapter(this, bookmarksList, object : BookmarkAdapter.onClickListerner {
            override fun onEdit(bookmark: Bookmark) {
                val intent = Intent(context, AddBookmark::class.java)
                intent.putExtra("BOOKMARK_ID", bookmark.id)
                startActivity(intent)
            }

            override fun onDelete(bookmark: Bookmark) {
                deleteBookMark(context,bookmark)
            }

            override fun onClick(item: Bookmark) {

                var url = item.url
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://$url"  // Add http:// if no scheme is present
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            override fun onLongPress(bookmark: Bookmark) {
                deleteBookMark(context,bookmark)
            }

        })
        listView.adapter = adapter
    }

    private fun deleteBookMark(context: Context, bookmark: Bookmark) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Are you sure want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, _ ->
                prefManager.deleteBookmarkById(bookmark.id)
                bookmarksList.clear()
                bookmarksList.addAll(prefManager.getBookmarks())
                if (bookmarksList.isEmpty()) {
                    llNoData.visibility = View.VISIBLE
                    listView.visibility = View.GONE
                }


                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    private fun setupGUI(){
        context = this@MainActivity
        listView = findViewById(R.id.listViewBookmarks)
        llNoData = findViewById(R.id.llNoData)
        spinnerFilter = findViewById(R.id.spinnerFilter)
        prefManager = PrefManager(context)
    }
}//main