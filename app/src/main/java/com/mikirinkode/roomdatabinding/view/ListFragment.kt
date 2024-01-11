package com.mikirinkode.roomdatabinding.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mikirinkode.roomdatabinding.R
import com.mikirinkode.roomdatabinding.viewmodel.ListViewModel


class ListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val todoListAdapter  = TodoListAdapter(arrayListOf()) { item -> viewModel.clearTask(item) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refresh()
        var recViewTodo = view.findViewById<RecyclerView>(R.id.revViewTodo)
        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = todoListAdapter

        var fabAddTodo = view?.findViewById<FloatingActionButton>(R.id.fabAddTodo)
        fabAddTodo?.setOnClickListener {
            val action = R.id.actionCreateTodo
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            var txtEmpty = view?.findViewById<TextView>(R.id.tvEmpty)
            if(it.isEmpty()) {
                txtEmpty?.visibility = View.VISIBLE
            } else {
                txtEmpty?.visibility = View.GONE
            }
        })
    }

}