package com.example.codialhomework

import PlantAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.codialhomework.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_edit.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val adapter = PlantAdapter(ArrayList(), this)
    private var editLAuncher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        editLAuncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                adapter.addPlant(it.data?.getSerializableExtra("plant") as Plant)
            }
        }
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            buttonAdd.setOnClickListener {
                editLAuncher?.launch(Intent(this@MainActivity, EditActivity::class.java))
            }
        }
    }

    fun onClickActivity(view: View) {
        val plant = Plant(0, edTitle.text.toString(), edDesc.text.toString())
        val editIntent = Intent().apply {
            putExtra("key", plant)
        }
        val i = Intent(this, Activity2::class.java)
        startActivity(i)
    }

}