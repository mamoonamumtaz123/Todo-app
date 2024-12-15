package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var items: ArrayList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var listView: ListView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        listView = findViewById(R.id.ListView)
        button = findViewById(R.id.button)

        // Set up button click listener
        button.setOnClickListener { addItem() }

        // Initialize data structures
        items = ArrayList()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = itemsAdapter

        // Set up long click listener
        setUpListViewListener()
    }

    private fun addItem() {
        val input = findViewById<EditText>(R.id.editTextText)
        val itemText = input.text.toString()

        if (itemText.isNotBlank()) {
            itemsAdapter.add(itemText)
            input.text.clear()
        } else {
            Toast.makeText(this, "Please enter text...", Toast.LENGTH_LONG).show()
        }
    }

    private fun setUpListViewListener() {
        listView.setOnItemLongClickListener { _, _, position, _ ->
            Toast.makeText(this, "Item Removed", Toast.LENGTH_LONG).show()
            items.removeAt(position)
            itemsAdapter.notifyDataSetChanged()
            true
        }
    }
}
