package mx.devf.roominn.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import mx.devf.roominn.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogIn?.setOnClickListener(this)
        tvSignUp?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnLogIn -> login()
            R.id.tvSignUp -> sendSingUp()
        }
    }

    fun login(){
        //ToDo Agregar logica de logeo
        sendMainPrincipal()
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
