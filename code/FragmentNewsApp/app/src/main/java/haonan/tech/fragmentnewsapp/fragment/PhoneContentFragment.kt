package haonan.tech.fragmentnewsapp.fragment

import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import haonan.tech.fragmentnewsapp.R
import kotlinx.android.synthetic.main.phone_content_frag.*
import kotlinx.android.synthetic.main.phone_content_frag.phoneTitle
import kotlinx.android.synthetic.main.phone_item.*

class PhoneContentFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.phone_content_frag, container, false)
    }

    fun refresh(phoneName: String, phoneDetail: String,phoneImageId:String){
        contentLayoutt.visibility = View.VISIBLE
        phoneTitle.text = phoneName  //  刷新标题
        phoneDetaill.text = phoneDetail // 刷新内容
        phoneImagee.setImageResource(phoneImageId.toInt())
    }
}