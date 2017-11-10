package mx.devf.roominn.Fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_service_add.*
import kotlinx.android.synthetic.main.fragment_service_add.view.*
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Components.RoomInnUtils
import mx.devf.roominn.Interfaces.IMainNavigate
import mx.devf.roominn.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Luis Rios on 08/11/2017.
 */
class ServiceAddFragment: Fragment(), View.OnClickListener, Callback<RoomInnService.ResponseService> {
    companion object {
        fun newIntances () : ServiceAddFragment
        {
            val fragment = ServiceAddFragment()
            val args = Bundle()
            //args.putString(ARG_HOUSE, houseId)
            fragment.arguments = args
            return fragment
        }
    }

    protected var mainNavigate : IMainNavigate? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_service_add, container, false)
        view?.btnSave?.setOnClickListener (this)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is IMainNavigate)
            mainNavigate = context
    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.btnSave -> sendSave()
        }
    }

    private fun sendSave() {
        val service = validate()
        if(service != null) {
            mainNavigate?.showLoading()
            val req = RoomInnService.RequestService(service)
            mainNavigate?.service!!.postService(req)
                    .enqueue(this)
        }
    }

    override fun onResponse(call: Call<RoomInnService.ResponseService>?, response: Response<RoomInnService.ResponseService>?) {
        mainNavigate?.hideLoading()
        if(response?.code() == 201) {

            val body = response?.body()
            Log.e("myLog", "success insert - $body")
            navegateServices()
        }
        else {
            val error = response?.errorBody()?.string()

            btnLogIn?.visibility = View.VISIBLE
            login_progress?.visibility = View.INVISIBLE
            Snackbar.make(tvPassword, error ?: "Problem in the services", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onFailure(call: Call<RoomInnService.ResponseService>?, t: Throwable?) {
        mainNavigate?.hideLoading()
        Log.e("myLog", "Valio madres...")
        t?.printStackTrace()
        Snackbar.make(tvPassword, t?.message ?: "Problem in the services", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    private fun validate() :RoomInnService.RoominnServicePost?
    {
        val name = etServiceName.text.trim().toString()
        val icon = getItemSelected()
        val cost = etCost.text.toString().toDoubleOrNull()
        val paymentDue = etPaymentDur.text.toString().toIntOrNull()
        val house : Int? = null


        if(name  == "") {
            Snackbar.make(etServiceName, "Name required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        if(icon == "") {
            Snackbar.make(radGroup1, "icon required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        if(cost == null) {
            Snackbar.make(etCost, "cost required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        if(paymentDue == null) {
            Snackbar.make(etPaymentDur, "Payment required", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            return null
        }
        return RoomInnService.RoominnServicePost(name, icon, cost, paymentDue, house)
    }

    private fun navegateServices()
    {
        mainNavigate?.navigate(ServicesFragment.newIntances())
    }

    private fun getItemSelected() : String{
        val itemSelect = radGroup1.checkedRadioButtonId
        return RoomInnUtils.toStringRadioService(itemSelect)
    }

}