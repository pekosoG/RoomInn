package mx.devf.roominn.Interfaces

import android.support.v4.app.Fragment
import mx.devf.roominn.API.RoomInnService

/**
 * Created by Luis Rios on 08/11/2017.
 */
interface IMainNavigate
{
    fun navigate(fragment : Fragment)
    fun navigate(fragment : Fragment, tag :String)
    fun logout()
    fun showLoading()
    fun hideLoading()

    val service: RoomInnService?

}