package com.example.codialhomework

import PlantAdapter
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.plant_item.*

abstract class Activity2 : AppCompatActivity() {

    private var launcher: ActivityResultLauncher<Intent>? = null
    private val adapter = PlantAdapter(ArrayList(), this)
    abstract val plant : ArrayList<Plant>

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result : ActivityResult->
            if (result.resultCode == RESULT_OK){
                adapter.addPlant(result.data?.getSerializableExtra(Cons.KEY) as Plant)
                val i = intent
                if (i!=null){
                    if (i.getStringExtra(Cons.KEY)!=null){
                        tv2.text = Tvtitle.toString()
                    }
                }

            }
        }
    }

}