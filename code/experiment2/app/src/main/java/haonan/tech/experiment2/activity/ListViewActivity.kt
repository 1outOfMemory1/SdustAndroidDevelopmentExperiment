package haonan.tech.experiment2.activity

import android.content.Intent
import android.os.Bundle
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
        // 初始化手机的信息 主要做的事情是将json格式的手机信息读取进来
        initPhones()
        // 设置adapter
        val adapter = PhoneAdapter(this, R.layout.phone_item,phoneList)
        myListView.adapter = adapter
        val detailIntent = Intent(this,DetailActivity::class.java)
        // 设置每个item的点击事件 传递信息给DetailActivity 然后展示详细内容
        myListView.setOnItemClickListener { _, _, position, _ ->
            var phone = phoneList[position]
            detailIntent.putExtra("name", phone.phoneName)
            detailIntent.putExtra("imageId", phone.imageid.toString())
            detailIntent.putExtra("phoneDetail", phone.phoneDetail)
            startActivity(detailIntent)
        }
    }

    //初始化手机信息
    private fun  initPhones(){
        fileContent = assets.open("phoneList_copy.json").bufferedReader().use { it.readText() }
        phoneList  =  JSON.parseArray(fileContent,Phone::class.java)
        for (phone in phoneList){ // 按照名字来获取图片的资源id  这样就不会写死了
            phone.imageid = getResourceByName(phone.imageName)
        }
    }
    // 根据图片名字获取图片资源id
    private fun getResourceByName( imageName:String):Int{
        val appInfo = applicationInfo
        val resId = resources.getIdentifier(imageName, "drawable", appInfo.packageName)
        return resId
    }
}