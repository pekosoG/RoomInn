package mx.devf.roominn.Components

import android.widget.ImageView
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.provider.MediaStore.Images.Media.getBitmap
import android.graphics.drawable.BitmapDrawable




/**
 * Created by Luis Rios on 09/11/2017.
 */
object RoomInnUtils {
    fun toBase64(imv: ImageView): String {
        imv.buildDrawingCache()
        if(imv.drawable is BitmapDrawable) {
            val drawable = imv.drawable as BitmapDrawable
            val bitmap = drawable.bitmap

            val bos = ByteArrayOutputStream()
            bitmap.compress(CompressFormat.PNG, 100, bos)
            val bb = bos.toByteArray()
            return Base64.encodeToString(bb, 0)
        }
        else
            return ""
    }

}