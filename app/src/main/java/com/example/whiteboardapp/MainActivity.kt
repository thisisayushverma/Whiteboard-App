package com.example.whiteboardapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.whiteboardapp.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var mvbinding:ActivityMainBinding
    private lateinit var share:Sharepref
    override fun onCreate(savedInstanceState: Bundle?) {
        mvbinding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mvbinding.root)
        share= Sharepref(this)
        mvbinding.signbtn.setOnClickListener{
            if(mvbinding.inpname.text.toString()=="" || mvbinding.inpemail.text.toString()=="") {
                Toast.makeText(this,"invalid", Toast.LENGTH_LONG).show()
                mvbinding.errortxt.text="Invalid credentials"
            }
            else{
                val passo=mvbinding.inppassword.text.toString()
                val passt=mvbinding.inpcpassword.text.toString()
                if(passo!=passt) {
                    mvbinding.errortxt.text="Password didn't matched"
                } else{
                    mvbinding.errortxt.text=""
                    Toast.makeText(this,"Perfect", Toast.LENGTH_LONG).show()
                    val hshmap = HashMap<String,String>()
                    hshmap.put("name",mvbinding.inpname.text.toString())
                    hshmap.put("email",mvbinding.inpemail.text.toString())
                    hshmap.put("password",mvbinding.inppassword.text.toString())
                    val apikey="https://whiteboard-server-pi.vercel.app/api/whiteboard/auth/register"

                    val jsonreq= JsonObjectRequest(Request.Method.POST,apikey,
                        JSONObject((hshmap as Map<String, String>?)!!),{ response->
                            try{
                                if(response.getBoolean("success")){
                                    val token=response.getString("token")
                                    share.setdata("token",token);
                                    Toast.makeText(this,"Data Send ${response}, ${share.getdata("token")}",Toast.LENGTH_LONG).show()
                                    val intent = Intent(this,ContentActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                            catch (error:JSONException){
                                Toast.makeText(this,"Error after getting data form signup ${error}",Toast.LENGTH_LONG).show()
                            }

                    },{
                            Toast.makeText(this,"Error while signup :- ${it}",Toast.LENGTH_LONG).show()
                    })

                    val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
                    queue.add(jsonreq)
                }
            }
            hideSoftKeyboard()
        }
        mvbinding.logintxt.setOnClickListener{
            val intent = Intent(this,Login_Activity::class.java)
            startActivity(intent)
        }
        mvbinding.tempid.setOnClickListener{
            val intent = Intent(this,CreationCanvas::class.java)
            startActivity(intent)
        }
    }
    fun AppCompatActivity.hideSoftKeyboard() {
        val view: View? = currentFocus

        // Check if the view is not null
        view?.let {
            // Create an InputMethodManager instance
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            // Hide the soft keyboard
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}