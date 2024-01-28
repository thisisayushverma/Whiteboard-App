package com.example.whiteboardapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
//    private lateinit var homeBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    val view =inflater.inflate(R.layout.fragment_home, container, false)
        Toast.makeText(requireContext(),"sdfdsf",Toast.LENGTH_LONG).show()

//        newbtn.setOnClickListener{
//            Toast.makeText(requireContext(),"Hello",Toast.LENGTH_LONG).show()
//
//            var infi:View=inflater.inflate(R.layout.createdialogbox,null,false)
//
//            var dialog=AlertDialog.Builder(requireActivity()).setView(infi).setNegativeButton("Cancel",null).create()
//
//           dialog.setOnShowListener() {
//
//           }
//
//
//        }

//
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newbtn:FloatingActionButton=view.findViewById(R.id.newbtn)
        newbtn.setOnClickListener{
            val showpopup=dialogfrag()
            showpopup.show((activity as AppCompatActivity).supportFragmentManager,"Showing Dialog Frag")
        }
    }

}