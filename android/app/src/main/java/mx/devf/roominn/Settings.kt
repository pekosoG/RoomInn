package mx.devf.roominn

import android.content.Context

/**
 * Created by Luis Rios on 09/11/2017.
 */
class Settings{
    companion object {
        val PREF_AUTH : String = "PREF_AUTH"
        val PREf_TOKEN_KEY = "PREF_AUTH_TOKEN"

        val PREF_USER : String = "PREF_USER"
        val PREF_USER_EMAIL : String = "PREF_USER_EMAIL"

        fun getEmail(context : Context) : String
        {
            val preference = context.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE)
            return preference.getString(PREF_USER_EMAIL, "")

        }

        fun saveEmail(context : Context, token:String)
        {
            val preference = context.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE)
            preference.edit()
                    .putString(PREF_USER_EMAIL,token)
                    .commit()
        }


        fun getToken(context : Context) : String
        {
            val preference = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE)
            return preference.getString(PREf_TOKEN_KEY, "")

        }

        fun saveToken(context : Context, token:String)
        {
            val preference = context.getSharedPreferences(PREF_AUTH, Context.MODE_PRIVATE)
            preference.edit()
                    .putString(PREf_TOKEN_KEY,token)
                    .commit()
        }


    }
}