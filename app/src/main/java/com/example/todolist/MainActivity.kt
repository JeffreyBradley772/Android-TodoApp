package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //lateinit -> promise to initialize it later
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //toDo adapter with Empty list
        todoAdapter = TodoAdapter(mutableListOf())

        //Assign our class to the recycle view adapter
        rvToDoItems.adapter = todoAdapter

        //use linear layout so the items are stacked on top of each other (as opposed to a grid view)
        rvToDoItems.layoutManager = LinearLayoutManager(this)

        //what happens when buttons are clicked
        btnAddTodo.setOnClickListener {
            val todoTitle = etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etToDoTitle.text.clear() // clears text written in text box

            }
        }
        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}