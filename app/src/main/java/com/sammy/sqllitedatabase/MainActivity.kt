package com.sammy.sqllitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sammy.sqllitedatabase.adapter.NoteAdater
import com.sammy.sqllitedatabase.databinding.ActivityMainBinding
import com.sammy.sqllitedatabase.db.MyDbHelper
import com.sammy.sqllitedatabase.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var myDbHelper: MyDbHelper

    lateinit var noteAdater: NoteAdater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        noteAdater = NoteAdater(myDbHelper.getUsers())

        binding.rv.adapter = noteAdater

        binding.apply {
            submitButton.setOnClickListener {
                if (nameEditText.text.isEmpty() || numberEditText.text.isEmpty()){
                    Toast.makeText(this@MainActivity, "Malumot to'liq emas", Toast.LENGTH_SHORT).show()
                }
                else{
                    val user = User(nameEditText.text.toString(),numberEditText.text.toString())
                    myDbHelper.addUser(user)
                    noteAdater.list.add(user)
                    noteAdater.notifyItemInserted(noteAdater.list.size - 1)
                    binding.rv.itemAnimator = DefaultItemAnimator()
                    Toast.makeText(this@MainActivity, "Saved", Toast.LENGTH_SHORT).show()

                }
              
            }
        }
    }
}