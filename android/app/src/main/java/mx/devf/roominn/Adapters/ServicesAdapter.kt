package mx.devf.roominn.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import mx.devf.roominn.API.RoomInnService
import mx.devf.roominn.Components.RoomInnUtils
import mx.devf.roominn.R

/**
 * Created by Luis Rios on 07/11/2017.
 */
class  ServicesAdapter(val services: List<RoomInnService.RoominnService>?) : RecyclerView.Adapter<ServicesAdapter.ServiceHolder>()
{

    override fun getItemCount(): Int {
        return services?.size ?: 0
    }

    override fun onBindViewHolder(holder: ServiceHolder?, position: Int) {
        val element = services!![position]
        holder?.tvTitle?.text = element.name
        holder?.tvDescription?.text = "$ %.2f".format(element.cost.div(6))
        val resourceIcon = RoomInnUtils.toDrawerRadioService(element.icon)
        holder?.imgIcon?.setImageResource(resourceIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ServiceHolder {
        var rowView = LayoutInflater.from(parent?.context).inflate(R.layout.row_services, parent, false)
        return ServiceHolder(rowView)
    }


    class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle : TextView? = null
        var tvDescription : TextView? = null
        var imgIcon: ImageView? = null

        init {
            tvTitle = itemView.findViewById(R.id.txtTitle)
            tvDescription = itemView.findViewById(R.id.txtDescription)
            imgIcon = itemView.findViewById(R.id.imgIcon)
        }
    }


}