package com.example.bottleofwater

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottleOfWater(
    modifier: Modifier,
    maxVolume:Int=2400,
    currentVolume:Int=0,
    waterColor:Color=Color(0xFF03A9F4),
    capColor:Color= Color(0xFF3F51B5),
    bottleColor:Color= Color.White
) {
    val waterPercentage = animateFloatAsState(
        targetValue = currentVolume.toFloat() / maxVolume.toFloat(),
        animationSpec = tween(1000)
    ).value

    Box(modifier = modifier
        .width(200.dp)
        .height(600.dp)){
        Canvas(modifier = Modifier.fillMaxSize()){
            val width = size.width
            val height= size.height
            val capWidth = width *0.6f
            val capHeight= height *0.1f
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
            val waterWavesYPosition = (1-waterPercentage) *height
            val waterPath = Path().apply {
                moveTo(
                    x= 0f, y=waterWavesYPosition
                )
                lineTo(
                    x = width , y = waterWavesYPosition
                )
                lineTo(
                    x = width , y = height
                )
                lineTo(
                    x = 0f , y = height
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
                drawPath(waterPath, color = waterColor)
            }

            drawRoundRect(
                color = capColor,
                size= Size(capWidth,capHeight),
                topLeft = Offset(
                    x= width/2 - capWidth/2f , 0f
                ),
                cornerRadius = CornerRadius(45f,45f)
            )
        }
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center){
            Text(
                text = "$currentVolume ml",
                fontSize = 20.sp,
                color = if (currentVolume <= maxVolume/2) waterColor else Color.White
            )
        }
    }
}