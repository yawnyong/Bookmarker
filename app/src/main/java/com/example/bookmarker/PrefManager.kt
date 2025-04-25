package com.example.bookmarker

import android.content.Context
import android.content.SharedPreferences
import com.example.bookmarker.Bookmark
import java.io.File.separator
import java.util.Scanner

class PrefManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("BookmarkPrefs", Context.MODE_PRIVATE)

    // Add or Update Bookmark
    fun addBookmark(bookmark: Bookmark) {
        val bookmarks = getBookmarks().toMutableList()
        val existingIndex = bookmarks.indexOfFirst { it.id == bookmark.id }

        if (existingIndex != -1) {
            bookmarks[existingIndex] = bookmark // update existing
        } else {
            bookmarks.add(bookmark) // add new
        }

        saveBookmarks(bookmarks)
    }

    // Get Bookmark by ID
    fun getBookmarkById(id: String): Bookmark? {
        return getBookmarks().find { it.id == id }
    }

    // Delete Bookmark by ID
    fun deleteBookmarkById(id: String) {
        val bookmarks = getBookmarks().toMutableList()
        bookmarks.removeAll { it.id == id }
        saveBookmarks(bookmarks)
    }

    // Save list
    private fun saveBookmarks(bookmarks: List<Bookmark>) {

        val bookmarksString = bookmarks.joinToString(separator = "\n") { bookmark ->
            "${bookmark.id},${bookmark.tittle},${bookmark.url},${bookmark.description},${bookmark.category},${bookmark.date}"
        }
        prefs.edit().putString(KEY_BOOKMARKS, bookmarksString).apply()

    }

    // Get All Bookmarks
    fun getBookmarks(): List<Bookmark> {

        val data = prefs.getString(KEY_BOOKMARKS, null) ?: return emptyList()
        val bookmarks = mutableListOf<Bookmark>()
        val scanner = Scanner(data)

        while (scanner.hasNextLine()) {

            val line = scanner.nextLine()
            val parts = line.split(",")

            if (parts.size >= 6) {
                val id = parts[0]
                val title = parts[1]
                val url = parts[2]
                val description = parts[3]
                val category = parts[4]
                val date = parts[5]

                bookmarks.add(Bookmark(id, title, url, description, category, date))
            }
        }
        scanner.close()
        return bookmarks

    }

    // Clear All
    fun clearBookmarks() {
        prefs.edit().remove(KEY_BOOKMARKS).apply()
    }

    // Splash State
    fun setSplashEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_SPLASH, enabled).apply()
    }

    fun isSplashEnabled(): Boolean {
        return prefs.getBoolean(KEY_SPLASH, true) // Default true
    }

    //constant object
    companion object{
        private val KEY_BOOKMARKS = "bookmarks_list"
        private val KEY_SPLASH = "splash_enabled"
    }
}