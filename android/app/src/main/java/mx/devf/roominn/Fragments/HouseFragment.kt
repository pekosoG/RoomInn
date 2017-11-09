package mx.devf.roominn

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by David on 11/7/2017.
 */
class HouseFragment : Fragment() {

    companion object {
        //val ARG_HOUSE = "ARG_HOUSE"

        //fun newIntances (houseId : String) : ServicesFragment
        fun newIntances () : HouseFragment
        {
            val fragment = HouseFragment()
            val args = Bundle()
            //args.putString(ARG_HOUSE, houseId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_house, container,false)
        return view
    }
}