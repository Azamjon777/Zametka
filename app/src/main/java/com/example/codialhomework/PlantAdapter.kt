import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codialhomework.Activity2
import com.example.codialhomework.Cons
import com.example.codialhomework.Plant
import com.example.codialhomework.R
import com.example.codialhomework.databinding.PlantItemBinding.*
import kotlin.collections.ArrayList


class PlantAdapter(plant: ArrayList<Plant>, private val contextM: Context): RecyclerView.Adapter<PlantAdapter.PlantHolder>() {
    val plantList = plant

    class PlantHolder(item: View, contextV: Context): RecyclerView.ViewHolder(item) {
        val binding = bind(item)
        val context = contextV

        fun bind(plant: Plant) = with(binding){
            im.setImageResource(plant.imageId)
            Tvtitle.text = plant.title
            itemView.setOnClickListener {
                val intent = Intent(context, Activity2::class.java).apply {
                    putExtra(Cons.KEY, plant.title)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantHolder(view, contextM)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    fun addPlant(plant: Plant){
        plantList.add(plant)
        notifyDataSetChanged()
    }
}