package com.example.whiteboardapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class dialogfrag : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogfrag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subbtn=view.findViewById<Button>(R.id.submitbtn)
        val cancelbtn=view.findViewById<Button>(R.id.cancelbtn)
        val inputbtn=view.findViewById<EditText>(R.id.projectinput)
        subbtn.setOnClickListener{
//            Toast.makeText(context,"submit",Toast.LENGTH_SHORT).show()
            if(inputbtn.text.toString()!=""){
                val title=inputbtn.text.toString()
//                Toast.makeText(context,"${title}",Toast.LENGTH_LONG).show()
                var intent=Intent(context,CreationCanvas::class.java)
                intent.putExtra("proj",title)
                startActivity(intent)
                dismiss()
            }
            else
            {
                Toast.makeText(context,"Set the title of project",Toast.LENGTH_SHORT).show()
            }

        }

        cancelbtn.setOnClickListener{
            Toast.makeText(context,"cancel",Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

}