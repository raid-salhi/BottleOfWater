package com.example.bottleofwater

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.dp

@Composable
fun BottleOfWater(
    modifier: Modifier,
    maxVolume:Int=2400,
    currentVolume:Int=0,
    waterColor:Color=Color(0xFF03A9F4),
    capColor:Color= Color(0xFF3F51B5),
    bottleColor:Color= Color.White
) {
    Box(modifier = modifier
        .width(200.dp)
        .height(600.dp)){
        Canvas(modifier = Modifier.fillMaxSize()){
            val width = size.width
            val height= size.height
            val bodyBottlePath = Path().apply {
                moveTo(x = width*0.3f,y=height *0.1f)
                lineTo(
                    x = width*0.3f , y=height * 0.2f
                )
                quadraticBezierTo(
                    x1  =0f, y1 = height * 0.3f,
                    x2 = 0f , y2 = height *0.4f
                )
                lineTo(
                    x = 0f , y=height * 0.95f
                )
                quadraticBezierTo(
                    x1  = 0f, y1 = height ,
                    x2 = width*0.05f , y2 = height
                )
                lineTo(
                    x = width *0.95f , y=height
                )
                quadraticBezierTo(
                    x1  = width, y1 = height ,
                    x2 = width , y2 = height*0.95f
                )
                lineTo(
                    x = width , y=height *0.4f
                )
                quadraticBezierTo(
                    x1  = width, y1 = height * 0.3f,
                    x2 = width *0.7f , y2 = height *0.2f
                )
                lineTo(
                    x = width*0.7f , y=height * 0.1f
                )
                close()
            }
            clipPath(
                bodyBottlePath
            ){
                drawRect(
                    color=bottleColor,
                    size=size
                )
            }
        }
    }
}