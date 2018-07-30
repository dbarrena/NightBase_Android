package expertosbd.gramosa.nightbasekotlinmvp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import expertosbd.gramosa.nightbasekotlinmvp.R

class IconFactory {
     fun createUnselectedIcon(): BitmapDescriptor {
        val diameter = 100
        val conf = Bitmap.Config.ARGB_8888
        val bmp = Bitmap.createBitmap(diameter, diameter, conf)
        val canvas = Canvas(bmp)
        val color = Paint()
        color.color = Color.WHITE
        color.style = Paint.Style.FILL
        color.alpha = 50

        canvas.drawCircle((diameter / 2).toFloat(), (diameter / 2).toFloat(),
                (diameter / 2).toFloat(), color)

        color.alpha = 250
        canvas.drawCircle((diameter / 2).toFloat(), (diameter / 2).toFloat(),
                (30 / 2).toFloat(), color)

        return BitmapDescriptorFactory.fromBitmap(bmp)
    }

    fun createSelectedIcon(context: Context): BitmapDescriptor {
        val diameter = 150
        val conf = Bitmap.Config.ARGB_8888
        val bmp = Bitmap.createBitmap(diameter, diameter, conf)
        val canvas = Canvas(bmp)
        val color = Paint()
        color.color = ContextCompat.getColor(context, R.color.selectedIconColor)
        color.style = Paint.Style.FILL
        color.alpha = 50

        canvas.drawCircle((diameter / 2).toFloat(), (diameter / 2).toFloat(),
                (diameter / 2).toFloat(), color)

        color.alpha = 250
        canvas.drawCircle((diameter / 2).toFloat(), (diameter / 2).toFloat(),
                (30 / 2).toFloat(), color)

        return BitmapDescriptorFactory.fromBitmap(bmp)
    }
}