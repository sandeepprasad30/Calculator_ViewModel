package com.sandeep.calculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

/*private const val STATE_PENDING_OPERATION = "PendingOperation"
private const val STATE_OPERAND1 = "Operand1"
private const val STATE_OPERAND1_STORED = "Operand1_Stored"*/

class MainActivity : AppCompatActivity() {

//    private lateinit var result: EditText
//    private lateinit var newNumber: EditText
//    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    //variable to hold operands and type calculation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        val viewModel = ViewModelProviders.of(this).get(BigDecimalViewModel::class.java)
        viewModel.stringResult.observe(this, Observer<String> { stringResult -> result.setText(stringResult) })
        viewModel.stringNewNumber.observe(this, Observer<String> { stringNumber -> newNumber.setText(stringNumber) })
        viewModel.stringOperation.observe(this, Observer<String> { stringOperation -> operation.text = stringOperation })

//        result = findViewById(R.id.result)
//        newNumber = findViewById(R.id.newNumber)
//
//        val button0: Button = findViewById(R.id.button0)
//        val button1: Button = findViewById(R.id.button1)
//        val button2: Button = findViewById(R.id.button2)
//        val button3: Button = findViewById(R.id.button3)
//        val button4: Button = findViewById(R.id.button4)
//        val button5: Button = findViewById(R.id.button5)
//        val button6: Button = findViewById(R.id.button6)
//        val button7: Button = findViewById(R.id.button7)
//        val button8: Button = findViewById(R.id.button8)
//        val button9: Button = findViewById(R.id.button9)
//        val buttonDot: Button = findViewById(R.id.buttonDot)
//
//        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
//        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
//        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
//        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
//        val buttonPlus = findViewById<Button>(R.id.buttonPlus)

        val listener = View.OnClickListener { v ->
            //val b = v as Button
            //newNumber.append(b.text)
            viewModel.digitPressed((v as Button).text.toString())
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            viewModel.operandPressed((v as Button).text.toString())
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)

        buttonNeg.setOnClickListener {
            viewModel.negPressed()

        }
    }



    /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        operand1 = if (savedInstanceState.getBoolean(STATE_OPERAND1_STORED, false)) {
            savedInstanceState.getDouble(STATE_OPERAND1)
        } else {
            null
        }

        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION).toString()
        operation.text = pendingOperation
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.about -> {
                showAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAbout() {
        val intent = Intent(this, AboutActivity::class.java).apply {}
        startActivity(intent)
    }
}
