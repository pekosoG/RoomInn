package mx.devf.roominn.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_services.view.*
import mx.devf.roominn.Adapters.ServicesAdapter
import mx.devf.roominn.Interfaces.IMainNavigate
import mx.devf.roominn.R

/**
 * Created by Luis Rios on 07/11/2017.
 */
class ServicesFragment : Fragment(), View.OnClickListener
{

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
        mAdapter = ServicesAdapter()

        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = mAdapter
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
    }


}