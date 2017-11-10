package mx.devf.roominn.Components

import android.widget.ImageView
import android.graphics.Bitmap.CompressFormat
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.provider.MediaStore.Images.Media.getBitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.BitmapFactory
import mx.devf.roominn.R


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

    fun toBitmap(encodedImage: String) : Bitmap?
    {
        if(encodedImage == "")
            return null
        try {
            var decodedString = Base64.decode(encodedImage, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        }catch(ex:Exception){
            return null
        }
    }

    fun toDrawerRadioService(item : String) : Int
    {
        return when(item)
        {
            "Rent" -> R.drawable.ic_servicerent
            "Electricity" -> R.drawable.ic_serviceelectricity
            "Water" -> R.drawable.ic_servicewater
            "Internet" -> R.drawable.ic_serviceinternet
            "Gas" -> R.drawable.ic_servicegas
            "Cable" -> R.drawable.ic_servicecable
            "Phone"  -> R.drawable.ic_servicephone
            "Cleaning" -> R.drawable.ic_servicecleaning
            else -> R.drawable.ic_serviceothers

        }
    }

    fun toStringRadioService(itemId : Int) : String
    {
        return when(itemId)
        {
            R.id.radioRent -> "Rent"
            R.id.radioElectricity -> "Electricity"
            R.id.radioWater -> "Water"
            R.id.radioInternet -> "Internet"
            R.id.radioGas -> "Gas"
            R.id.radioCable -> "Cable"
            R.id.radioPhone -> "Phone"
            R.id.radioCleaning -> "Cleaning"
            R.id.radioOthers -> "Others"
            else -> ""
        }
    }
}
