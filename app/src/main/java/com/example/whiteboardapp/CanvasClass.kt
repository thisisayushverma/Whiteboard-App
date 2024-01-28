package com.example.whiteboardapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Path
import android.graphics.PathMeasure
import android.os.Build
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject

class CanvasClass(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var paint = Paint()
    private var allpath:ArrayList<Pathprop> = ArrayList()
    private var currpthcolor:Int
    private var currpthstroke:Int
    private lateinit var npath:Path

//    CanvasClass

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        paintSettings()
        currpthcolor=Color.WHITE
        currpthstroke=10

    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        for (x in allpath) {
            paint.color=x.ptcolor
            paint.strokeWidth= x.ptstroke.toFloat()
            canvas.drawPath(x.ptpath, paint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val pointX = event.x
        val pointY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Starts a new line in the path at the touched point
//                path.moveTo(pointX, pointY)
//                lastX = pointX
//                lastY = pointY
                justtouch(pointX,pointY)
            }
            MotionEvent.ACTION_MOVE -> {
                // Draws a line between the last point and the current touch point
                npath.lineTo(pointX,pointY)
            }
            else -> return false
        }

        // Invalidate the view to trigger a redraw
        invalidate()
        return true
    }
//    function to makeing the draw with the strokes
    fun justtouch(stx:Float,sty:Float){
        npath=Path()
        val curpropert = Pathprop(currpthcolor,currpthstroke,npath)
        allpath.add(curpropert)
        npath.moveTo(stx,sty)
    }
    fun changecolor(hexColor:String){
        try {
            val color = Color.parseColor(hexColor)
            paint.color = color
            invalidate() // Force a redraw
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }
    fun setpathcolor(hexColor: String){
        currpthcolor=Color.parseColor(hexColor)
    }
    fun setpathstroke(st:Int){
        currpthstroke=st.toInt()
    }
    private fun paintSettings() {
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        paint.isAntiAlias = true
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun savetojson():JSONObject{
        var storejson=JSONObject()   //yeh store krega paths:-
        var arrayjson=JSONArray()
        for(x in allpath){
            var jsonstore=JSONObject()
            var jsonArray= JSONArray()
            var color=x.ptcolor
            var stroke=x.ptstroke
            var path=x.ptpath
            val pathMeasure = PathMeasure(path, false)
            var points = FloatArray(2)
            for (i in 0 until (pathMeasure.length / 1f).toInt()) {
                pathMeasure.getPosTan(i.toFloat(), points, null)
                jsonArray.put(pointToJson(points[0], points[1]))
            }
            jsonstore.put("path",jsonArray)
            jsonstore.put("color",color)
            jsonstore.put("stroke",stroke)
            arrayjson.put(jsonstore)
        }
        storejson.put("paths",arrayjson)
        return storejson
    }

    private fun pointToJson(x: Float, y: Float): JSONObject {
        val pointObject = JSONObject()
        pointObject.put("x", x)
        pointObject.put("y", y)
        return pointObject
    }
}