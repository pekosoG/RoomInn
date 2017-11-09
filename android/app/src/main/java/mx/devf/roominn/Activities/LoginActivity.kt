package mx.devf.roominn.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Components.ServiceGenerator
import mx.devf.roominn.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(), View.OnClickListener, Callback<RoomInnService.tokenData> {


    var service : RoomInnService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogIn?.setOnClickListener(this)
        tvSignUp?.setOnClickListener(this)
        setupServices()
    }

    private fun setupServices() {
         service = ServiceGenerator.createService(RoomInnService::class.java)
    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnLogIn -> login()
            R.id.tvSignUp -> sendSingUp()
        }
    }

    fun validate() : Boolean{
        val email = tvEmail!!.text.trim().toString()
        val password = tvPassword!!.text.trim().toString()

        if(email == "") {
            Snackbar.make(tvEmail, "Login required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return false
        }

        if(password == "") {
            Snackbar.make(tvPassword, "Password required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return false
        }

        return true;
    }

    fun login(){
        if(validate())
        {
            btnLogIn?.visibility = View.INVISIBLE
            login_progress?.visibility = View.VISIBLE

            val email = tvEmail!!.text.trim().toString()
            val password = tvPassword!!.text.trim().toString()

            val auth = RoomInnService.authLogin(email, password)

            service?.postAuth(RoomInnService.RequestAuth(auth))!!.enqueue(this)
        }

    }

    override fun onFailure(call: Call<RoomInnService.tokenData>?, t: Throwable?) {
        btnLogIn?.visibility = View.VISIBLE
        login_progress?.visibility = View.INVISIBLE

        Log.e("myLog", "Valio madres...")
        t?.printStackTrace()
    }

    override fun onResponse(call: Call<RoomInnService.tokenData>?, response: Response<RoomInnService.tokenData>?) {
        btnLogIn?.visibility = View.VISIBLE
        login_progress?.visibility = View.INVISIBLE
        if(response?.code() == 200) {
            val body = response?.body()
            Log.e("myLog", "Tu token es {${body?.token}}")
        }
    }

    fun sendMainPrincipal(){
        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra(Constants.INTENT_KEY_USERNAME, username)
        startActivity(intent)
        finish()
    }

    fun sendSingUp(){
        val intent = Intent(this, SignUpActivity::class.java)
        //intent.putExtra(Constants.INTENT_KEY_USERNAME, username)
        startActivity(intent)
    }

}
