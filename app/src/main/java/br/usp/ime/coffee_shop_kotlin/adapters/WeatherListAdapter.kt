package br.usp.ime.coffee_shop_kotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import br.usp.ime.coffee_shop_kotlin.R
import br.usp.ime.coffee_shop_kotlin.domain.Weather

class WeatherListAdapter(private val context: Context, private val dataset: MutableList<Weather>) :
    RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var temperature: TextView
        var date: TextView
        var icon: ImageView

        init {
            temperature = view.findViewById(R.id.listTemperature)
            date = view.findViewById(R.id.listDate)
            icon = view.findViewById(R.id.listIcon)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_weather, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val weather = dataset[position]

        viewHolder.temperature.text = weather.temperatureFormatted()
        viewHolder.date.text = weather.dateFormatted()
        viewHolder.icon.setImageDrawable(
            AppCompatResources.getDrawable(
                context,
                weather.weatherIcon()
            )
        )
    }

    override fun getItemCount() = dataset.size
}
