package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    //takes in a mutableList
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    //view holder
    //class wrapped around specific view, allows recycle view list to only load items in view -> better performance
    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    //required implementation for RecyclerView
    //takes an xml file and converts it to a view we can work with in kotlin
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                //inflate makes xml into an actual view
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    //add items to list on button click
    fun addTodo(todo: Todo){
        //println(todos)
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    //removes checked items from list on button click
    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        //notify adapter that something was changed
        notifyDataSetChanged()
    }

    //Uses paint flags to make text have a strike through when isChecked is true
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{

            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //required implementation for RecyclerView
    //set the data of the toDos list
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var curTodo = todos[position]
        //println(position)
        //println(curTodo)
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTodoTitle,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    //required by Recycle view so it knows how many items to display
    override fun getItemCount(): Int {
        return todos.size
    }
}