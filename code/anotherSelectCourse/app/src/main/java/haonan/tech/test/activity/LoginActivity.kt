package haonan.tech.test.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import haonan.tech.test.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
//            val username = usernameEditText.text.toString()
//            val password = passwordEditText.text.toString()
//            if (username == "admin" && password =="123"){
//                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
//                val studentIntent  = Intent(this,StudentActivity::class.java)
//                startActivity(studentIntent)
//            }
//            else
//                Toast.makeText(this,"登录失败，用户名或者密码有误",Toast.LENGTH_SHORT).show()
            val mainIntent  = Intent(this,MainActivity::class.java)
            startActivity(mainIntent)
        }

    }
}