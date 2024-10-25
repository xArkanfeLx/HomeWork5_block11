package com.example.gridlayout_tablelayout

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var toolbarTB:Toolbar
    private lateinit var inputET:EditText
    private lateinit var resultTV:TextView
    private lateinit var gridBtnsGL:GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarTB=findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)

        inputET=findViewById(R.id.inputET)
        resultTV=findViewById(R.id.resultTV)

        gridBtnsGL=findViewById(R.id.gridBtnsGL)
        for (i in gridBtnsGL.children) {
            val btn = i as Button
            btn.setOnClickListener(this)
            //val toast = Toast.makeText(this,"${i.text}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick (v: View?) {
        val target = v as Button
        val text = target.text.toString()
        when (text) {
            "=" -> if(!inputET.text.isEmpty()) resultTV.text = Calculator().getResult(inputET.text.toString()).toString()
            "reset" -> {
                inputET.text.clear()
                resultTV.text=""
            }
            else -> inputET.text = Editable.Factory.getInstance().newEditable("${inputET.text}$text")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }


}