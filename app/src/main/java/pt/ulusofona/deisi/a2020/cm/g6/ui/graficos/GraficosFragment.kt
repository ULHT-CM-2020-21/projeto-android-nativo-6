package pt.ulusofona.deisi.a2020.cm.g6.ui.graficos

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_graficos.*
import me.ithebk.barchart.BarChartModel
import pt.ulusofona.deisi.a2020.cm.g6.R
import pt.ulusofona.deisi.a2020.cm.g6.ui.MainActivity
import pt.ulusofona.deisi.a2020.cm.g6.ui.listener.GraficoUIListener
import pt.ulusofona.deisi.a2020.cm.g6.ui.utils.Grafico
import java.text.SimpleDateFormat
import java.util.*

class GraficosFragment : Fragment(), GraficoUIListener {

    private lateinit var viewModel: GraficosViewModel

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_graficos, container, false)
        viewModel = ViewModelProviders.of(this).get(GraficosViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }



    override fun onStart() {
        super.onStart()
        viewModel.registerViewListener(this)
        viewModel.drawGraphs()

    }

    override fun onUpdateUI(grafico: Grafico) {
        bar_chart_Confirmados.barMaxValue = grafico.maxConfirmados
        bar_chart_Recuperados.barMaxValue = grafico.maxRecuperados
        bar_chart_Obitos.barMaxValue = grafico.maxObitos
        bar_chart_Internados.barMaxValue = grafico.maxInternados

        var barchartModel: BarChartModel

        var valuesGraficoConfirmados = grafico.valuesConfirmados
        var valuesGraficoRecuperados = grafico.valuesRecuperados
        var valuesGraficoObitos = grafico.valuesObitos
        var valuesGraficoInternados = grafico.valuesInternados



        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoConfirmados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#989898")
            }else{
                barchartModel.barColor = Color.parseColor("#767676")
            }
            barchartModel.barTag = i
            barchartModel.barText = valuesGraficoConfirmados.elementAt(i).toString()
            bar_chart_Confirmados.addBar(barchartModel)
        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoRecuperados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#5ca08e")
            }else{
                barchartModel.barColor = Color.parseColor("#2e856e")
            }
            barchartModel.barText = valuesGraficoRecuperados.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Recuperados.addBar(barchartModel)

        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoObitos.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#ff7b7b")
            }else{
                barchartModel.barColor = Color.parseColor("#ffbaba")
            }
            barchartModel.barText = valuesGraficoObitos.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Obitos.addBar(barchartModel)

        }

        for(i in 14 downTo 0){
            barchartModel = BarChartModel()
            barchartModel.barValue = valuesGraficoInternados.elementAt(i)
            if(i % 2 ==0){
                barchartModel.barColor = Color.parseColor("#70ADCC")
            }else{
                barchartModel.barColor = Color.parseColor("#568EBC")
            }
            barchartModel.barText = valuesGraficoInternados.elementAt(i).toString()
            barchartModel.barTag = i
            bar_chart_Internados.addBar(barchartModel)

        }

        if(grafico.fromToday == true){
            bar_chart_Confirmados.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
            }
            bar_chart_Recuperados.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
            }
            bar_chart_Obitos.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
            }
            bar_chart_Internados.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int), Toast.LENGTH_SHORT).show()
            }



            startDateC.setText(getDaysAgo(0))
            endDateC.setText(getDaysAgo(14))
            startDateR.setText(getDaysAgo(0))
            endDateR.setText(getDaysAgo(14))
            startDateO.setText(getDaysAgo(0))
            endDateO.setText(getDaysAgo(14))
            startDateI.setText(getDaysAgo(0))
            endDateI.setText(getDaysAgo(14))

        } else {
            bar_chart_Confirmados.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int + 1), Toast.LENGTH_SHORT).show()
            }
            bar_chart_Recuperados.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int + 1), Toast.LENGTH_SHORT).show()
            }
            bar_chart_Obitos.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int + 1), Toast.LENGTH_SHORT).show()
            }
            bar_chart_Internados.setOnBarClickListener {
                Toast.makeText(context as MainActivity,getDaysAgo(it.barTag as Int + 1), Toast.LENGTH_SHORT).show()
            }



            startDateC.setText(getDaysAgo(1))
            endDateC.setText(getDaysAgo(15))
            startDateR.setText(getDaysAgo(1))
            endDateR.setText(getDaysAgo(15))
            startDateO.setText(getDaysAgo(1))
            endDateO.setText(getDaysAgo(15))
            startDateI.setText(getDaysAgo(1))
            endDateI.setText(getDaysAgo(15))
        }
    }

    fun getDaysAgo(daysAgo: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)
        return SimpleDateFormat("dd/MM/yyyy").format(calendar.time)
    }

}