package com.example.whiteboardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.whiteboardapp.databinding.ActivityLoginBinding
import org.json.JSONObject

class Login_Activity : AppCompatActivity() {
    private lateinit var shared:Sharepref
    private  lateinit var lpvbinding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        lpvbinding= ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(lpvbinding.root)
        shared= Sharepref(this)
        lpvbinding.loginbtn.setOnClickListener{
            if(lpvbinding.inpemail.text.toString()=="" || lpvbinding.inppassword.toString()=="")
                lpvbinding.errortxt.text="Invalid Credential"
            else{
                val hshmap= HashMap<String,String>()
                hshmap.put("email",lpvbinding.inpemail.text.toString())
                hshmap.put("password",lpvbinding.inppassword.text.toString())
                val apikey:String="https://whiteboard-server-pi.vercel.app/api/whiteboard/auth/login"

                var jsonreq= JsonObjectRequest(Request.Method.POST,apikey, JSONObject(hshmap as Map<String, String>?),{
                    if(it.getBoolean("success")){
                        shared.setdata("token",it.getString("token"))
                        Toast.makeText(this,"Login Successfully ${shared.getdata("token")}",Toast.LENGTH_LONG).show()
                        val intent = Intent(this,ContentActivity::class.java)
                        startActivity(intent)
                    }
                    else Toast.makeText(this,"Opps Data not Found",Toast.LENGTH_LONG).show()
                },{
                    Toast.makeText(this,"Something is Wrong while Login",Toast.LENGTH_LONG).show()
                })
                val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
                queue.add(jsonreq)
            }
        }
        lpvbinding.signtxt.setOnClickListener{
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}