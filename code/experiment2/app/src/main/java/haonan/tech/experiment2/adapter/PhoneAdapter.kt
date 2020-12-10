package haonan.tech.experiment2.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import haonan.tech.experiment2.R
import haonan.tech.experiment2.entity.Fruit
import haonan.tech.experiment2.entity.Phone

class PhoneAdapter (activity: Activity, val resourceId:Int, data:List<Phone>):
    ArrayAdapter<Phone>(activity, resourceId, data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
        }else
            view = convertView

        val phoneImage: ImageView = view.findViewById(R.id.phoneImage)
        val phoneName: TextView = view.findViewById(R.id.phoneName)
        val phoneDetail: TextView = view.findViewById(R.id.phoneDetail)
        val phone = getItem(position)

        if(phone != null){
            phoneImage.setImageResource(phone.imageid)
            phoneName.text = phone.phoneName
            phoneDetail.text = phone.phoneDetail
        }
        return view
    }

}

