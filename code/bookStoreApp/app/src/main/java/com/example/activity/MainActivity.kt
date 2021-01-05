package com.example.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.entity.User
import com.example.test.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var currentUser: User

    companion object{
        fun actionStart(context: Context,user: User){
            val mainIntent: Intent = Intent(context,MainActivity::class.java)
            mainIntent.putExtra("user",user)
            context.startActivity(mainIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        //Log.e("aa", currentUser.toString())

    }
    private fun init(){
        val bottomNavigationView:BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController:NavController = Navigation.findNavController(this, R.id.fragment)
        val configuration: AppBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment, R.id.booksFragment, R.id.shoppingCartFragment, R.id.mineFragment).build()
        NavigationUI.setupActionBarWithNavController(this,navController,configuration)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
        //   拿到当前登录的用户
        currentUser  = intent.getParcelableExtra<User>("user")!!
        val editor =  getSharedPreferences("currentUser",Context.MODE_PRIVATE).edit()
        editor.putInt("userId",currentUser.userId)
        editor.putString("userName",currentUser.username)
        editor.apply()
    }

}