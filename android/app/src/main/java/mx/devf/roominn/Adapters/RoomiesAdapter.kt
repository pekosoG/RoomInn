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
class RoomiesAdapter(val roomies: List<RoomInnService.RoominnRoomie>?) : RecyclerView.Adapter<RoomiesAdapter.RoomieHolder>()
{
    override fun getItemCount(): Int {
        return roomies?.count() ?: 0
    }

    override fun onBindViewHolder(holder: RoomieHolder?, position: Int) {
        val element = roomies!![position]
        holder?.tvTitle?.text = element.name
        holder?.tvTotalMine?.text = element.email
        val bitmap = RoomInnUtils.toBitmap(element.phone)
        if(bitmap!= null)
            holder?.imgIcon?.setImageBitmap(bitmap)
        //TODO handler event checked all's
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RoomieHolder {
        var rowView = LayoutInflater.from(parent?.context).inflate(R.layout.row_rommies, parent, false)
        return RoomieHolder(rowView)
    }


    class RoomieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle : TextView? = null
        var tvTotalMine : TextView? = null
        var imgIcon: ImageView? = null
        var tvTotalAll : TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.txtTitle)
            tvTotalMine = itemView.findViewById(R.id.txtDescription)
            tvTotalAll = itemView.findViewById(R.id.txtTotal)
            imgIcon = itemView.findViewById(R.id.imgIcon)
        }
    }
}