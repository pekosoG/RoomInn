package mx.devf.roominn.Activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import mx.devf.roominn.R
import android.content.DialogInterface



class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUp?.setOnClickListener(this)
        btnImagen?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnImagen -> capturePicture()
            R.id.btnSignUp ->signUp()
        }
    }

    fun signUp(){
        validateSignUp()
    }

    fun validateSignUp(){
        //var error : String = ""
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show()
    }

    fun capturePicture(){


        val dialog = MaterialDialog.Builder(this)
                .title("Take Picture?")
                .positiveText("Gallery")
                .negativeText("Camera")
                .onPositive { dialog, which -> dialog.dismiss(); findPicture()  }
                .onNegative { dialog, which -> dialog.dismiss(); takePicture() }
                .canceledOnTouchOutside(true)
                .build()

        //dialog.dismiss()
        dialog.show()
    }


    fun takePicture()
    {
        val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camaraIntent, 1032) //codigo que asigne a mi camara
    }

    fun findPicture()
    {
        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhoto, 1234)//one can be replaced with any action code
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        //1032 - codigo que asigne para la camara
        when (requestCode) {
            1032 -> {
                if (resultCode == Activity.RESULT_OK) {
                    val photo: Bitmap = data?.extras?.get("data") as Bitmap
                    imgUser?.setImageBitmap(photo)
                }
                //else if (resultCode == Activity.RESULT_CANCELED)
                //    Toast.makeText(this, "Se cancelo la captura de camara, no se tomo la foto", Toast.LENGTH_SHORT)
            }
            1234 -> {
                if (resultCode == Activity.RESULT_OK) {
                    val uriImage = data?.data as Uri
                    imgUser?.setImageURI(uriImage)
                }
                //else if (resultCode == Activity.RESULT_CANCELED)
                //    Toast.makeText(this, "Se cancelo la captura de camara, no se tomo la foto", Toast.LENGTH_SHORT)
            }


        }
    }

}
