package mx.devf.roominn.Fragments

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_services.*
import kotlinx.android.synthetic.main.fragment_services.view.*
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Adapters.ServicesAdapter
import mx.devf.roominn.Interfaces.IMainNavigate
import mx.devf.roominn.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Luis Rios on 07/11/2017.
 */
class ServicesFragment : Fragment(), View.OnClickListener, Callback<List<RoomInnService.RoominnService>> {


    companion object {
        //val ARG_HOUSE = "ARG_HOUSE"

        //fun newIntances (houseId : String) : ServicesFragment
        fun newIntances () : ServicesFragment
        {
            val fragment = ServicesFragment()
            val args = Bundle()
            //args.putString(ARG_HOUSE, houseId)
            fragment.arguments = args
            return fragment
        }
    }

    protected var mRecyclerView: RecyclerView? = null
    protected var mAdapter: ServicesAdapter? = null
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected var mainNavigate : IMainNavigate? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_services, container, false)

        mRecyclerView = view?.recyclerView
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.itemAnimator = DefaultItemAnimator()

        view?.fab?.setOnClickListener(this)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.fab -> {
                mainNavigate?.navigate(ServiceAddFragment.newIntances())
            }
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is IMainNavigate)
            mainNavigate = context
        getServices()
    }

    fun getServices()
    {
        mainNavigate?.showLoading()
        mainNavigate?.service?.getServices()?.enqueue(this)
    }

    private fun setupAdapter(services : List<RoomInnService.RoominnService>?) {

        var cost = services?.sumByDouble{it.cost}?.div(6) ?: 0

        //cost = cost/6f;

        tvTotalMine?.text = "$ %.2f".format(cost)

        mAdapter = ServicesAdapter(services)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onResponse(call: Call<List<RoomInnService.RoominnService>>?, response: Response<List<RoomInnService.RoominnService>>?) {
        mainNavigate?.hideLoading()
        if(response?.code() == 200) {
            val body = response?.body()
            setupAdapter(body)
        }
        else {
            val error = response?.errorBody()?.string()

            btnLogIn?.visibility = View.VISIBLE
            login_progress?.visibility = View.INVISIBLE
            Snackbar.make(tvPassword, error ?: "Problem in the services", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onFailure(call: Call<List<RoomInnService.RoominnService>>?, t: Throwable?) {
        mainNavigate?.hideLoading()
        Log.e("myLog", "Valio madres...")
        t?.printStackTrace()
        Snackbar.make(tvPassword, t?.message ?: "Problem in the services", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}