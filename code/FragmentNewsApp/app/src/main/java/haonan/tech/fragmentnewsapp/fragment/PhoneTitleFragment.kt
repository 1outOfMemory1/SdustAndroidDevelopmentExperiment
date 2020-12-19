package haonan.tech.fragmentnewsapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import haonan.tech.fragmentnewsapp.R
import haonan.tech.fragmentnewsapp.activity.PhoneContentActivity
import haonan.tech.fragmentnewsapp.entity.Phone
import kotlinx.android.synthetic.main.activity_main_phone.*
import kotlinx.android.synthetic.main.phone_title_frag.*
import java.lang.StringBuilder



import com.alibaba.fastjson.JSON



class PhoneTitleFragment:Fragment() {
    // 默认是手机 单界面      不是平板 两界面
    private var isTwoPane = false

    inner class PhoneAdapter(val phoneList: List<Phone>): RecyclerView.Adapter<PhoneAdapter.ViewHolder>(){
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val phoneTitle: TextView = view.findViewById(R.id.phoneTitle)
            val phoneDetail: TextView = view.findViewById(R.id.phoneDetail)
            val phoneImage:ImageView = view.findViewById(R.id.phoneImage)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.phone_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val phone = phoneList[holder.absoluteAdapterPosition]
                if(isTwoPane){
                    // 双页模式
                    val fragment = phoneContentFrag as PhoneContentFragment
                    fragment.refresh(phone.phoneName, phone.phoneDetail,phone.imageid.toString())
                }else{
                    PhoneContentActivity.actionStart(parent.context, phone.phoneName, phone.phoneDetail,phone.imageid)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val phone = phoneList[position]
            holder.phoneTitle.text = phone.phoneName
            holder.phoneDetail.text = phone.phoneDetail
            holder.phoneImage.setImageResource(phone.imageid)
        }
        override fun getItemCount(): Int {
            return phoneList.size
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.phone_title_frag, container ,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.phoneTitle) != null
        val layoutManager = LinearLayoutManager(activity)
        phoneTitleRecyclerView.layoutManager = layoutManager
        val adapter =  PhoneAdapter(getPhones())
        phoneTitleRecyclerView.adapter = adapter
    }





    private  fun getPhones(): List<Phone>{
        //val fileContent = context?.assets?.open("phoneList_copy.json")?.bufferedReader().use { it?.readText() }
        val fileContent = this.context?.assets?.open("phoneList.json")?.bufferedReader().use { it?.readText() }
        Log.e("avad",fileContent.toString())
        var phoneList  =  JSON.parseArray(fileContent,Phone::class.java)
        for (phone in phoneList){ // 按照名字来获取图片的资源id  这样就不会写死了
            phone.imageid = getResourceByName(phone.imageName)
        }
        return phoneList
    }


    // 根据图片名字获取图片资源id
    private fun getResourceByName( imageName:String):Int{
        val appInfo = context?.applicationInfo
        val resId = resources.getIdentifier(imageName, "drawable", appInfo?.packageName)
        return resId
    }


}