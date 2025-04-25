package com.example.bookmarker

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.webkit.URLUtil.isValidUrl
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import java.util.Calendar
import java.util.UUID

class AddBookmark : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etUrl: EditText
    private lateinit var etDate: EditText
    private lateinit var ivBack: ImageView
    private lateinit var btnAdd: MaterialButton
    private lateinit var context: Context
    private lateinit var prefManager: PrefManager
    private var selectedCategory = ""
    private var isEdit = false
    private var id = ""
    private lateinit var itemList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_bookmark)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //call the GUI
        setupGUI()

        //spinner
        setupSpinner()
        //date picker
        setupDatePicker()
        //click listener
        setupClickListener()
        //Edit
        checkIfEditMode()

    }//onCreate

    private fun setupClickListener(){
        //back button
        ivBack.setOnClickListener { finish() }

        //edit button
        btnAdd.setOnClickListener{ addOrEdit() }
    }

    private fun checkIfEditMode(){
        if (intent.hasExtra("BOOKMARK_ID")) {
            isEdit = true
            id = intent.getStringExtra("BOOKMARK_ID").toString()
            val obj = prefManager.getBookmarkById(id) ?: return
            etTitle.setText(obj.tittle)
            etDescription.setText(obj.description)
            etUrl.setText(obj.url)
            etDate.setText(obj.date)
            spinner.setSelection(itemList.indexOf(obj.category))
            selectedCategory = obj.category
            btnAdd.text = "Edit"

        }
    }

    //Add or Edit
    private fun addOrEdit(){
        val tittle = etTitle.text.toString()
        val des = etDescription.text.toString()
        val url = etUrl.text.toString()
        val date = etDate.text.toString()

        if (tittle.isEmpty() || des.isEmpty() || url.isEmpty() || date.isEmpty() || selectedCategory.isEmpty()) {
            Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
        } else if (!isValidUrl(url)) {
            Toast.makeText(context, "Oops! your url is not valid", Toast.LENGTH_SHORT).show()
        } else {
            if (!isEdit)
                id = UUID.randomUUID().toString();

            val bookmark = Bookmark(id, tittle, url, des, selectedCategory, date)

            if(isEdit)
                showAlert(context,"Are you sure want to Edit?",bookmark)
            else
                showAlert(context,"Are you sure want to Add?",bookmark)

        }
    }

    private fun setupDatePicker(){
        etDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "${String.format("%02d", selectedDay)}/" +
                        "${String.format("%02d", selectedMonth + 1)}/" +
                        "$selectedYear"
                etDate.setText(formattedDate)
            }, year, month, day)

        //To prevent choosing the past dates (I think it's not necessary to prevent the past date,
        //as it used to record the url
        //datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    //set up the spinner
    private fun setupSpinner(){
        itemList = resources.getStringArray(R.array.category_options)

        //adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                if (position == 0)
                    selectedCategory = ""
                else
                    selectedCategory = itemList[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    //URL validation
    private fun isValidUrl(url: String): Boolean {
        return Patterns.WEB_URL.matcher(url).matches()
    }

    //reset the fields
    private fun resetFields() {
        etTitle.setText("")
        etDescription.setText("")
        etDate.setText("")
        etUrl.setText("")
        selectedCategory = ""
        spinner.setSelection(0)
    }

    //Alert for Add or Edit the Bookmark
    private fun showAlert(context: Context, message: String, bookmark :Bookmark) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, _ ->
                prefManager.addBookmark(bookmark)
                if (isEdit) {
                    Toast.makeText(context, "Bookmark Edit successfully!", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                } else
                    Toast.makeText(context, "Bookmark Added successfully!", Toast.LENGTH_SHORT)
                        .show()
                resetFields()
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

        val alert = builder.create()
        alert.show()
    }

    private fun setupGUI(){
        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDes)
        etUrl = findViewById(R.id.etUrl)
        etDate = findViewById(R.id.etDate)
        spinner = findViewById(R.id.categorySpinner)
        ivBack = findViewById(R.id.ivBack)
        btnAdd = findViewById(R.id.btnAdd)
        context = this@AddBookmark
        prefManager = PrefManager(context)
    }
}//main