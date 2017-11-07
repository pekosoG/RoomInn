package mx.devf.roominn.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import mx.devf.roominn.R

/**
 * Created by Luis Rios on 07/11/2017.
 */
class RoomiesAdapter : RecyclerView.Adapter<RoomiesAdapter.RoomieHolder>()
{
    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: RoomieHolder?, position: Int) {
        //TODO assign properties extras
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RoomieHolder {
        var rowView = LayoutInflater.from(parent?.context).inflate(R.layout.row_rommies, parent, false)
        return RoomieHolder(rowView)
    }


    class RoomieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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