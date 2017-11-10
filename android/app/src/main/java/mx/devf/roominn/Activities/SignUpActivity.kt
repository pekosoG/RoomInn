package mx.devf.roominn.Activities

import android.Manifest
import android.app.Activity
import android.content.Context
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
import android.content.pm.PackageManager
import android.inputmethodservice.KeyboardView
import android.support.v13.app.ActivityCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Components.KeyboardUtils
import mx.devf.roominn.Components.RoomInnUtils
import mx.devf.roominn.Components.ServiceGenerator
import mx.devf.roominn.Settings
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity(), View.OnClickListener, Callback<RoomInnService.ResponseRoomie> {


    var service : RoomInnService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btnSignUp?.setOnClickListener(this)
        btnImagen?.setOnClickListener(this)
        setupServices()
    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnImagen -> capturePicture()
            R.id.btnSignUp ->signUp()
        }
    }

    private fun setupServices() {
        service = ServiceGenerator.createService(RoomInnService::class.java)
    }


    private fun signUp(){
        val roomie = validate()
        if(roomie != null) {
            content?.visibility = View.INVISIBLE
            progress?.visibility = View.VISIBLE
            KeyboardUtils.hideSoftInput(this)
            val req = RoomInnService.RequestRoomie(roomie)
            service?.postRoomie(req)!!.enqueue(this)
        }
    }
    override fun onResponse(call: Call<RoomInnService.ResponseRoomie>?, response: Response<RoomInnService.ResponseRoomie>?) {
        content?.visibility = View.VISIBLE
        progress?.visibility = View.INVISIBLE
        val email = etEmail?.text.toString().trim()
        if(response?.code() == 201) {

            val body = response?.body()
            Log.e("myLog", "success insert - $body.id")
            //Settings.saveEmail(this, body!!.roomie.email)
            //sendLogin()
            onBackPressed()
        }
        else {
            val error = response?.errorBody()?.string()

            btnLogIn?.visibility = View.VISIBLE
            login_progress?.visibility = View.INVISIBLE
            Snackbar.make(tvPassword, error ?: "Problem in the services", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onFailure(call: Call<RoomInnService.ResponseRoomie>?, t: Throwable?) {
        content?.visibility = View.VISIBLE
        progress?.visibility = View.INVISIBLE
        Log.e("myLog", "Valio madres...")
        t?.printStackTrace()

        Snackbar.make(tvPassword, t?.message ?: "Problem in the services", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    fun validate() : RoomInnService.RoominnRoomiePost?{
        //var error : String = ""
        val fullname = etFullname?.text.toString().trim()
        val email = etEmail?.text.toString().trim()
        val password = etPassword?.text.toString().trim()
        val phone = etPhone?.text.toString().trim()

        if(fullname == "") {
            Snackbar.make(etFullname, "Name required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        if(email == "") {
            Snackbar.make(etEmail, "Email required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        if(password == "") {
            Snackbar.make(etEmail, "Password required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        if(phone == "") {
            Snackbar.make(etEmail, "phone required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }

        val imagen = RoomInnUtils.toBase64(imgUser!!)

        return RoomInnService.RoominnRoomiePost(fullname, email, phone, imagen, password)
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show()
    }

    fun capturePicture(){


        val dialog = MaterialDialog.Builder(this)
                .title("Take Pic")
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


    private fun sendLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY;
        //intent.putExtra(Constants.INTENT_KEY_USERNAME, username)
        startActivity(intent)
        finish()

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
