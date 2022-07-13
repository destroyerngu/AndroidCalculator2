package com.example.standardcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {
    // StringBuilder为真正可变字符串
    var num1: StringBuilder = StringBuilder()
    var num2: StringBuilder = StringBuilder()
    lateinit var operation: String
    var isFirstNumber = true    // 判断是否在输入第一个运算数
    var detail: StringBuilder = StringBuilder() // detail 用来展示计算机过程（即计算式子）
    var currentOperation: String? = null // 记录当前运算符的状态
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  清空按钮的任务
        clear_button.setOnClickListener {
            detail.clear()
            num1.clear()
            num2.clear()
            isFirstNumber = true
            currentOperation = null
            detailTextView.text = "0"
            resultTextView.text = "0"
        }

        divideBtn.setOnClickListener {
            operationBtnClicked(it)
        }
        multiplyBtn.setOnClickListener{
            operationBtnClicked(it)
        }
        minusBtn.setOnClickListener{
            operationBtnClicked(it)
        }
        addBtn.setOnClickListener{
            operationBtnClicked(it)
        }
        // 小数点

        // 等于
        equalBtn.setOnClickListener {
            if (num1.isNotEmpty() && num2.isNotEmpty() && operation.isNotEmpty()) {
                var v1 = num1.toString().toDouble()
                var v2 = num2.toString().toDouble()
                var result = when(operation) {
                    "1" ->  v1 / v2
                    "2" ->  v1 * v2
                    "3" ->  v1 - v2
                    "4" ->  v1 + v2
                    else    ->  {0f}
                }
                resultTextView.text = "$result"
                num1.clear()
                num2.clear()
                num1.append("$result")

            }
        }

        btn_0.setOnClickListener{
            numberBtnClicked(it)
        }
        btn_2.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_1.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_3.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_4.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_5.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_6.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_7.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_8.setOnClickListener {
            numberBtnClicked(it)
        }
        btn_9.setOnClickListener {
            numberBtnClicked(it)
        }
        // 删除
        deleteBtn.setOnClickListener{
            if (detail.isNotEmpty()) {
                // 删除最后一个内容
                detail.replace(detail.length-1, detail.length, "")
                if (detail.isEmpty()) {
                    // 删完后 isFirstNumber需要改变
                    isFirstNumber = true
                    detailTextView.text = "0"
                }
                else {
                    // 重新显示
                    detailTextView.text = detail.toString()
                }

            }
        }
    }
    // 运算符按钮的任务
    fun operationBtnClicked(view: View) {
        // 判断num1是否有数字
        if (num1.isNotEmpty()) {
            // 第一个运算数输入结束了
            isFirstNumber = false
            operation = view.tag as String
            // 判断是否多次输入运算符
            if (currentOperation == null) {
                // 直接将运算符拼接
                detail.append(getOperation(operation))
                // 更改状态
                currentOperation = operation
            }
            else {
                // 多次输入运算符 需要用当前的运算符去替换最后一个运算符
                detail.replace(detail.length-1, detail.length, getOperation(operation))
            }
            // 显示详情
            detailTextView.text = detail.toString()
        }

    }
    // 数字按钮的任务
    fun numberBtnClicked(view: View) {
        val numString = view.tag as String
        // 记录当前过程
        detail.append(numString)
        // 显示出详情
        detailTextView.text = detail.toString()
        // 判断输入数字之后，又可以自由输入运算符，把状态改为原始状态
        if (currentOperation != null) {
            currentOperation = null
        }
        // 判断是不是在输入第一个操作数
        if (isFirstNumber) {
            // 直接将点击的数字拼接到num1中
            num1.append(numString)
        }
        else {
            num2.append(numString)
        }
    }
    // 获取tag对应的运算符
    fun getOperation(tag: String): String {
        return when(tag) {
            "1" -> "÷"
            "2" -> "×"
            "3" -> "-"
            "4" -> "+"
            else -> {""}
        }
    }
}