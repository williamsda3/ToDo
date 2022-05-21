package com.example.testtodolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*



class TodoAdapter (private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent,false))
    }

    // basic add helper-method
    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    // helper method to remove the checked task(todos)
    fun deleteDoneTodos(){
        todos.removeAll{todo -> todo.isChecked
        }
        notifyDataSetChanged()
    }

    // method to add the 'line-through' effect on completed tasks(todos)
    private fun toggleStrkeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked == true){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //method checks if the task is completed (isChecked), if so it will add the 'line-through' effect ^^
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       val curTodo = todos[position]
        holder.itemView.apply {
            tvTodoTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrkeThrough(tvTodoTitle,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked -> toggleStrkeThrough(tvTodoTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked }
        }
    }

    // to keep track of size in list
    override fun getItemCount(): Int {
        return todos.size
    }


}