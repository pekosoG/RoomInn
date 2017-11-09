package mx.devf.roominn.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_services.view.*
import mx.devf.roominn.Adapters.ServicesAdapter
import mx.devf.roominn.Interfaces.IMainNavigate
import mx.devf.roominn.R

/**
 * Created by Luis Rios on 08/11/2017.
 */
class ServiceAddFragment: Fragment() {


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
        //TODO agregar accion boton
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


}