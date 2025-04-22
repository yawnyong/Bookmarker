package com.example.bookmarker

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.bookmarker.Bookmark

class PrefManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("BookmarkPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

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
        val json = gson.toJson(bookmarks)
        prefs.edit().putString(KEY_BOOKMARKS, json).apply()
    }

    // Get All Bookmarks
    fun getBookmarks(): List<Bookmark> {
        val json = prefs.getString(KEY_BOOKMARKS, null) ?: return emptyList()
        val type = object : TypeToken<List<Bookmark>>() {}.type
        return gson.fromJson(json, type)
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