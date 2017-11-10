package mx.devf.roominn.Activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Components.ServiceGenerator
import mx.devf.roominn.R
import mx.devf.roominn.Settings
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import mx.devf.roominn.Components.KeyboardUtils


class LoginActivity : AppCompatActivity(), View.OnClickListener, Callback<RoomInnService.ReponseAuth> {


    var service : RoomInnService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val token = Settings.getToken(this)
        if(token != "")
        {
            sendMainPrincipal()
            return
        }

        tvEmail?.setText(Settings.getEmail(this))
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
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(p0?.getWindowToken(), 0)
    }

    fun validate() : RoomInnService.RoominnUser? {
        val email = tvEmail!!.text.trim().toString()
        val password = tvPassword!!.text.trim().toString()

        Settings.saveEmail(this, email)

        if(email == "") {
            Snackbar.make(tvEmail, "Login required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }

        if(password == "") {
            Snackbar.make(tvPassword, "Password required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }

        return RoomInnService.RoominnUser(email, password);
    }

    fun login(){
        val auth = validate()
        if(auth != null)
        {
            btnLogIn?.visibility = View.INVISIBLE
            login_progress?.visibility = View.VISIBLE
            KeyboardUtils.hideSoftInput(this)
            service?.postAuth(RoomInnService.RequestAuth(auth))!!.enqueue(this)
        }

    }

    override fun onFailure(call: Call<RoomInnService.ReponseAuth>?, t: Throwable?) {
        btnLogIn?.visibility = View.VISIBLE
        login_progress?.visibility = View.INVISIBLE

        Log.e("myLog", "Valio madres...")
        t?.printStackTrace()

        Snackbar.make(tvPassword, t?.message ?: "Problem in the services", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onResponse(call: Call<RoomInnService.ReponseAuth>?, response: Response<RoomInnService.ReponseAuth>?) {
        btnLogIn?.visibility = View.VISIBLE
        login_progress?.visibility = View.INVISIBLE
        if(response?.code() == 200) {
            val body = response?.body()
            Log.e("myLog", "Tu token es {${body?.token}}")
            Settings.saveToken(this, body?.token ?: "")
            sendMainPrincipal()
        }
        else {
            val error = response?.errorBody()?.string()

            btnLogIn?.visibility = View.VISIBLE
            login_progress?.visibility = View.INVISIBLE
            Snackbar.make(tvPassword, error ?: "Problem in the services", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
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
