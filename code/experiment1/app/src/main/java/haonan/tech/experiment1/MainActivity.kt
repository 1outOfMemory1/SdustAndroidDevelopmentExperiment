package haonan.tech.experiment1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.widget.Toast
import com.alibaba.fastjson.JSON
import haonan.tech.experiment1.entity.Question
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import kotlin.math.log




class MainActivity : AppCompatActivity() {

    var currentIndex:Int = 0  // 用来记录当前的题目索引是哪一个
    var listOfEachQuestion = ArrayList<Boolean>()     // 如果已经选择了答案 就记住这个选择
    var checkAnswer = ArrayList<Boolean>()  // 用来判断是否已经查看了答案  如果已经选错了或者查看了答案就不能再选
    var fileContent = ""      // 读取的json文件内容
    var firstQuestion = ""  // 记住第一个问题

    override fun onCreate(savedInstanceState: Bundle?) {
        // 读取json格式的问题列表 使用阿里巴巴的fastjson解析
        fileContent = assets.open("questions.json").bufferedReader().use { it.readText() }
        var fileContentList  =  JSON.parseArray(fileContent,Question::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstQuestion = "1. " +  fileContentList[currentIndex].question
        question.text = firstQuestion
        for (i in 0..fileContentList.size){
            listOfEachQuestion.add(false)
            checkAnswer.add(false)
        }


        // 正确按钮的点击事件
        right.setOnClickListener {
            if(!checkAnswer[currentIndex]) {  // 如果没有勾选过或者查看答案 才可以判断
                if ( fileContentList[currentIndex].answer){
                    Toast.makeText(this,"你答对了",Toast.LENGTH_SHORT).show()
                    listOfEachQuestion[currentIndex] = true
                }
                else{
                    this.checkAnswer[currentIndex] = true
                    Toast.makeText(this,"你答错了",Toast.LENGTH_SHORT).show()
                }
            }else
                Toast.makeText(this,"你已经知道答案了,作弊是不对的喔",Toast.LENGTH_SHORT).show()
        }

        // 错误按钮的点击事件
        wrong.setOnClickListener {
            //question.text = "wrong"
            if(!checkAnswer[currentIndex]) {  // 如果没有勾选过或者查看答案 才可以判断
                if (!fileContentList[currentIndex].answer) {
                    Toast.makeText(this, "你答对了", Toast.LENGTH_SHORT).show()
                    listOfEachQuestion[currentIndex] = true
                } else{
                    this.checkAnswer[currentIndex] = true
                    Toast.makeText(this,"你答错了",Toast.LENGTH_SHORT).show()
                }
            }else
                Toast.makeText(this,"你已经知道答案了,作弊是不对的喔",Toast.LENGTH_SHORT).show()

        }

        // 查看答案的阿按钮点击事件
        answer.setOnClickListener {
            //question.text = "answer"
            var answerIntent = Intent(this, Answer::class.java)
            AlertDialog.Builder(this).apply {// 弹出对话框 如果点击确认会启动 answerActivity
                setTitle("查看答案")
                setMessage("你确定要查看答案吗？")
                setCancelable(false)
                setPositiveButton("确定", DialogInterface.OnClickListener { dialogInterface, i ->
                    // 传递数据 拿到答案传送给  answerActivity
                    answerIntent.putExtra("answer",fileContentList[currentIndex].answer.toString())
                    startActivity(answerIntent)
                })
                setNegativeButton("取消", DialogInterface.OnClickListener { dialogInterface, i ->
                })
                show()
            }
            this.checkAnswer[currentIndex] = true  // 将查看过答案的标志标记为true
        }

        // 下一个题目的点击事件
        next.setOnClickListener {
            // 如果当前索引已经超过列表中题目数量了 就准备启动finishActivity来进行 展示最后得分
            if(currentIndex >= fileContentList.size -1){
                val finishIndent = Intent(this,Finish::class.java)
                var count = 0
                for(ele in listOfEachQuestion){
                    if(ele)
                        count++
                }
                val score = count * 20
                finishIndent.putExtra("score",score.toString())
                startActivity(finishIndent)
            }else{
                currentIndex++
                val strTemp:String = "${currentIndex +1}. "+ fileContentList[currentIndex].question
                question.text = strTemp
            }
        }
    }


}