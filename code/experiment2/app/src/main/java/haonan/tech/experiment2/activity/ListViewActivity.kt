package haonan.tech.experiment2.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.fastjson.JSON
import haonan.tech.experiment2.R
import haonan.tech.experiment2.adapter.PhoneAdapter
import haonan.tech.experiment2.entity.Phone
import kotlinx.android.synthetic.main.activity_list_view.*


class ListViewActivity : AppCompatActivity() {

    private var fileContent = ""
    private var phoneList:MutableList<Phone> = ArrayList<Phone>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

//        val appInfo = applicationInfo
//        val resId = resources.getIdentifier("l1", "drawable", appInfo.packageName)
//        Log.e("resId",resId.toString())


        initPhones()
        val adapter = PhoneAdapter(this, R.layout.phone_item,phoneList)
        myListView.adapter = adapter
        val detailIntent = Intent(this,DetailActivity::class.java)
        myListView.setOnItemClickListener { _, _, position, _ ->
            var phone = phoneList[position]
            detailIntent.putExtra("name", phone.phoneName)
            detailIntent.putExtra("imageId", phone.imageid.toString())
            detailIntent.putExtra("phoneDetail", phone.phoneDetail)
            startActivity(detailIntent)
        }
    }


    private fun  initPhones(){
        fileContent = assets.open("phoneList_copy.json").bufferedReader().use { it.readText() }
        phoneList  =  JSON.parseArray(fileContent,Phone::class.java)
        for (phone in phoneList){ // 按照名字来获取图片的资源id  这样就不会写死了
            phone.imageid = getResourceByName(phone.imageName)
        }
    }

    private fun getResourceByName( imageName:String):Int{
        val appInfo = applicationInfo
        val resId = resources.getIdentifier(imageName, "drawable", appInfo.packageName)
        return resId
    }
}