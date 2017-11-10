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
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Adapters.RoomiesAdapter
import mx.devf.roominn.Interfaces.IMainNavigate
import mx.devf.roominn.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Luis Rios on 07/11/2017.
 */
class RoomiesFragment : Fragment(), Callback<List<RoomInnService.RoominnRoomie>> {


    companion object {
        //val ARG_HOUSE = "ARG_HOUSE"

        //fun newIntances (houseId : String) : ServicesFragment
        fun newIntances () : RoomiesFragment
        {
            val fragment = RoomiesFragment()
            val args = Bundle()
            //args.putString(ARG_HOUSE, houseId)
            fragment.arguments = args
            return fragment
        }
    }

    protected var mRecyclerView: RecyclerView? = null
    protected var mAdapter: RoomiesAdapter? = null
    protected var mLayoutManager: RecyclerView.LayoutManager? = null
    protected var mainNavigate : IMainNavigate? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_roomies, container, false)

        mRecyclerView = view?.findViewById(R.id.recyclerView)
        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.itemAnimator = DefaultItemAnimator()
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is IMainNavigate)
            mainNavigate = context
        getRoomies()
    }

    private fun getRoomies() {
        mainNavigate?.showLoading()
        mainNavigate?.service?.getRoomie()?.enqueue(this)
    }


    fun setupAdapter(roomies : List<RoomInnService.RoominnRoomie>?)
    {
        mAdapter = RoomiesAdapter(roomies)
        mRecyclerView?.adapter = mAdapter
    }

    override fun onFailure(call: Call<List<RoomInnService.RoominnRoomie>>?, t: Throwable?) {
        mainNavigate?.hideLoading()

        Log.e("myLog", "Valio madres...")
        t?.printStackTrace()
        Snackbar.make(tvPassword, t?.message ?: "Problem in the services", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }

    override fun onResponse(call: Call<List<RoomInnService.RoominnRoomie>>?, response: Response<List<RoomInnService.RoominnRoomie>>?) {
        mainNavigate?.hideLoading()
        if(response?.code() == 200) {
            val body : List<RoomInnService.RoominnRoomie>? = response?.body()
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
}