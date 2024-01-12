package com.mikirinkode.roomdatabinding.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mikirinkode.roomdatabinding.R
import com.mikirinkode.roomdatabinding.viewmodel.DetailViewModel


class EditFragment : Fragment() {
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
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val title = view.findViewById<TextView>(R.id.tvTitle)
        val etTitle = view.findViewById<TextView>(R.id.etTitle)
        val etNotes = view.findViewById<TextView>(R.id.etNotes)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        title.text = "Edit Todo"
        btnAdd.text = "Save Changes"

        val uuid = EditFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            etTitle.text = it.title
            etNotes.text = it.notes
//            when (it.priority) {
//                1 -> radioLow.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioHigh.isChecked = true
//            }

        })
    }

    fun observeViewModel() {

    }

}