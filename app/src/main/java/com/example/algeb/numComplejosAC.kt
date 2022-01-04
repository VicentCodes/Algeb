package com.example.algeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.algeb.databinding.ActivityNumComplejosBinding
import java.util.*

var data = ""
var operacion = ""
val operL = logd()

class numComplejosAC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNumComplejosBinding.inflate(layoutInflater).apply {
            setContentView(root)
            expres.requestFocus()
            expres.showSoftInputOnFocus = false
            buttonOne.setOnClickListener {setT(buttonOne, expres) }
            buttonTwo.setOnClickListener{ setT(buttonTwo, expres) }
            buttonThree.setOnClickListener{ setT(buttonThree, expres) }
            buttonFour.setOnClickListener{ setT(buttonFour, expres)}
            buttonFive.setOnClickListener{ setT(buttonFive, expres) }
            buttonSix.setOnClickListener{ setT(buttonSix, expres) }
            buttonSeven.setOnClickListener{ setT(buttonSeven, expres) }
            buttonEight.setOnClickListener{ setT(buttonEight, expres) }
            buttonNine.setOnClickListener{ setT(buttonNine, expres) }
            buttonZero.setOnClickListener{ setT(buttonZero, expres) }
            buttonDiv.setOnClickListener{ setT(buttonDiv, expres) }
            buttonAdd.setOnClickListener{ setT(buttonAdd, expres) }
            buttonI.setOnClickListener{
                val start: Int = expres.getSelectionStart()
                expres.getText().insert(start, "i")
            }
            buttonMul.setOnClickListener{ setT(buttonMul, expres) }
            buttonSub.setOnClickListener{ setT(buttonSub, expres) }
            buttonPo.setOnClickListener{ setT(buttonPo, expres) }
            buttonParentOp.setOnClickListener { setT(buttonParentOp, expres) }
            buttonPartCl.setOnClickListener { setT(buttonPartCl,expres) }
            buttonBack.setOnClickListener{
                val textString: String = expres.getText().toString()
                if (textString.length > 0) {
                    expres.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                }
            }
            buttonAc.setOnClickListener{ expres.setText("")
                data=""
                answer.setText("")
            }

            buttonequal.setOnClickListener{
                try {
                    resOper(expres,answer)
                }catch (e:Exception){
                    answer.setText(R.string.msgMathNoSolve)
                }
            }


        }


        val mHandler = Handler()
        binding.expres.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                binding.answer.isVisible = false
                binding.typing.isVisible = true
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                mHandler.removeCallbacksAndMessages(null)
                mHandler.postDelayed(userStoppedTyping, 500) // 2 second
            }

            var userStoppedTyping = Runnable {


                if(isParenthesisMatch(binding.expres.text.toString()) == true){
                    binding.answer.isVisible = true
                    binding.typing.isVisible = false
                    try {
                        resOper(binding.expres, binding.answer)
                    }catch (e:Exception){
                        binding.answer.setText(R.string.msgMathNoSolve)
                    }
                } else {

                    binding.answer.isVisible = true
                    binding.typing.isVisible = false
                    binding.answer.setText(R.string.msgMathNoSolve)
                }



            }
        })




    }
    fun resOper(tvOper:EditText, tvRes:TextView) {
            operacion = tvOper.text.toString()
            operacion.replace("\uD835\uDC56", "i")
            val operA = operL.getA(operacion)
            val operB = operL.getB(operacion)
            val relA = operL.getReal(operA)
            val relB = operL.getReal(operB)
            val imaA = operL.getIma(operA)
            val imaB = operL.getIma(operB)
            val operaci = operL.getOperacion(operacion)

        if (operaci.equals("+") || operaci.equals("-")){
                tvRes.setText(operL.doubleToFraction(operL.sumaComplejos(relA, relB, imaA, imaB, operacion)))
        }else if(operaci.equals("*")){
                tvRes.setText(operL.doubleToFraction(operL.multiComplejos(relA, relB, imaA, imaB)))
        }else if(operaci.equals("/")){
            if (operL.countOccurrences(operL.doubleToFractionDiv(operL.division(relA, relB, imaA, imaB)),'/')>=2
                    && operL.getB(operL.doubleToFractionDiv(operL.division(relA, relB, imaA, imaB))).contains("-")){
                        tvRes.setText(operL.doubleToFractionDiv(operL.division(relA, relB, imaA, imaB)).replace(" ", ""))
                        }else{
                            tvRes.setText(operL.doubleToFractionDiv(operL.division(relA, relB, imaA, imaB)).replace(" ", "+"))
                        }
        }



    }


    fun setT(btnG: Button, textS: EditText){
        data = btnG.getText().toString()
        val start: Int = textS.getSelectionStart()
        textS.getText().insert(start, data)
    }


    fun isParenthesisMatch(str: String): Boolean? {
        try {
            if (str[0] == '[') {
                return false
            }
        }catch (e:Exception){}
        val stack: Stack<Char> = Stack<Char>()
        var c: Char
        for (i in 0 until str.length) {
            c = str[i]
            if (c == '(')
                stack.push(c)
            else if (c == '[') stack.push(c)
            else if (c == ')')
                if (stack.empty())
                    return false
                else if (stack.peek() == '(') stack.pop()
                else
                    return false
            else if (c == ']')
                if (stack.empty())
                    return false
                else if (stack.peek() == '[')
                    stack.pop()
                else
                    return false
        }

        return stack.empty()
    }


}


