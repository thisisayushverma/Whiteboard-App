package com.example.whiteboardapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.whiteboardapp.databinding.ActivityCreationCanvasBinding
import org.json.JSONException
import org.json.JSONObject

class CreationCanvas : AppCompatActivity() {
    private lateinit var ccvbinding:ActivityCreationCanvasBinding
    private var checkcolor:Boolean=false
    private var checkeraser:Boolean=false
    private var checkstroke:Boolean=false
    private var checksave:Boolean=false
    private lateinit var shared:Sharepref


//    private var cnvclass= CanvasClass(this,null)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        shared= Sharepref(this)
        var token=shared.getdata("token")
    Toast.makeText(this, token,Toast.LENGTH_LONG).show()
        ccvbinding=ActivityCreationCanvasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(ccvbinding.root)


        var tempo=intent.getStringExtra("proj")
        Toast.makeText(this,"{$tempo} no ",Toast.LENGTH_SHORT).show()
        ccvbinding.idpaintimg.setOnClickListener{
            if(checkcolor==false) checkcolor=true
            else checkcolor=false
            showcolormenu(checkcolor)
        }
        ccvbinding.eraseimgbtn.setOnClickListener{
            ccvbinding.drawarea.setpathcolor("#292A2C")
//            if(checkeraser==false){f
//                checkeraser=true
//                ccvbinding.drawarea.setpathcolor(Color.TRANSPARENT.toString())
//            }
        }
        ccvbinding.strokeimgbtn.setOnClickListener{
            Toast.makeText(this,"strokembnmbnm",Toast.LENGTH_LONG).show()
            if(checkstroke==false) checkstroke=true
            else checkstroke=false
            showstroke(checkstroke)
        }
        ccvbinding.greenbtn.setOnClickListener{
            checkeraser=false
            Toast.makeText(this,"helloooo",Toast.LENGTH_LONG).show()
            ccvbinding.drawarea.setpathcolor("#BCE885")
        }
        ccvbinding.whitebtn.setOnClickListener{
            Toast.makeText(this,"helloooo",Toast.LENGTH_LONG).show()
            ccvbinding.drawarea.setpathcolor("#ffffff")
        }
        ccvbinding.redbtn.setOnClickListener{
            Toast.makeText(this,"helloooo",Toast.LENGTH_LONG).show()
            ccvbinding.drawarea.setpathcolor("#fe8d8f")
        }
        ccvbinding.yellowbtn.setOnClickListener{
            Toast.makeText(this,"helloooo",Toast.LENGTH_LONG).show()
            ccvbinding.drawarea.setpathcolor("#F7E718")
        }
        ccvbinding.strokeslider.addOnChangeListener{ slider, values , fromuser->
            ccvbinding.drawarea.setpathstroke(values.toInt())
        }
        ccvbinding.saveimgbtn.setOnClickListener{
            var storejson:JSONObject=ccvbinding.drawarea.savetojson()
            val headermap=HashMap<String,String>()

            headermap["Authorization"]=token
            headermap["Content-Type"] = "application/json"
            //                         this is for the saving the data
            if(tempo!=null){
                val hashmap=HashMap<String,String>()
                hashmap.put("name",tempo)
                hashmap.put("path",storejson.toString())

                val apikey="https://whiteboard-server-pi.vercel.app/api/whiteboard/"

                val jsonreq=object : JsonObjectRequest(
                    Request.Method.POST,apikey,k
                    JSONObject(hashmap as Map<String, String>?),{
                    response->
                    try {
                        if(response.getBoolean("success")) Toast.makeText(this," New Data Formed Successfully",Toast.LENGTH_LONG).show()
                    }
                    catch (error:JSONException){
                        Toast.makeText(this," Error occur while the saving new contenst $error",Toast.LENGTH_LONG).show()
                    }
                },{
                    Toast.makeText(this,"Didn't able to upload file :- $it",Toast.LENGTH_LONG).show()
                }){
                    override fun getHeaders():Map<String,String>{
                        return headermap
                    }
                }

                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(jsonreq)
            }
            else{
               Toast.makeText(this,"Null",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showcolormenu(chkcolor: Boolean) {
        if(chkcolor) {
            ccvbinding.strokepallet.visibility=View.GONE
            ccvbinding.colorpallet.visibility = View.VISIBLE
            checkstroke=false
        }
        else ccvbinding.colorpallet.visibility=View.GONE
    }
    private fun showstroke(chkstroke:Boolean){
        Toast.makeText(this,"stroke",Toast.LENGTH_LONG).show()
        if(chkstroke){
            ccvbinding.colorpallet.visibility = View.GONE
            ccvbinding.strokepallet.visibility=View.VISIBLE
            checkcolor=false
        }
        else ccvbinding.strokepallet.visibility=View.GONE
    }
}