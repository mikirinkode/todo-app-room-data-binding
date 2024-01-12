package com.mikirinkode.roomdatabinding.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mikirinkode.roomdatabinding.R
import com.mikirinkode.roomdatabinding.model.Todo
import com.mikirinkode.roomdatabinding.viewmodel.DetailViewModel


class CreateFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this)[DetailViewModel::class.java]
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val txtTitle = view.findViewById<EditText>(R.id.etTitle)
            val txtNotes = view.findViewById<EditText>(R.id.etNotes)
            val radioGroupPriority = view.findViewById<RadioButton>(R.id.radioGroupPriority)
            var radio = 1

            var todo = Todo(txtTitle.text.toString(),
                txtNotes.text.toString(), 1)

//            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString())
            val list = listOf(todo)
            viewModel.addTodo(list)

            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }

    }
}