package com.example.todolist
/*
-kotlin class for items in list
-data class only stores data of an object
-list of these todo items are passed to the Todo adapter class
*/
data class Todo(
    val title: String,
    var isChecked: Boolean = false
)