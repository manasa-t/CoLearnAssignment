package com.manasa.myapplication.ui.fullscreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.manasa.myapplication.R
import kotlinx.android.synthetic.main.fragment_full_screen.*
import java.util.*
import kotlin.collections.ArrayList


private const val PHOTO_ID = "photo_id"


class FullScreenFragment : Fragment() {


    private var photoId: String? = null


    val fullScreenViewModel: FullScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            photoId = it.getString(PHOTO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_screen, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(photoId: String): FullScreenFragment {
            var fragment = FullScreenFragment()
            var arguments = Bundle()
            arguments.putString(PHOTO_ID, photoId)
            fragment.arguments = arguments
            return fragment

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        if (!photoId.isNullOrEmpty()) {
            fullScreenViewModel.getPhoto(photoId!!)
            fullScreenViewModel.getPhotoStats(photoId!!)
        }

        fullScreenViewModel.fullScreenData.observe(viewLifecycleOwner, Observer {

            if (it.error != null) {
                // show error
                layout_error_full_screen.visibility = View.VISIBLE
                iv_fullscreen_photo.visibility = View.GONE
            } else if (it.success != null) {
                iv_fullscreen_photo.setImageBitmap(it.success)
                iv_fullscreen_photo.visibility = View.VISIBLE
                layout_error_full_screen.visibility = View.GONE
            }
        })

        iv_fullscreen_close.setOnClickListener {
            (activity as FullScreenListener).onClose()
        }

        fullScreenViewModel.photosStatsData.observe(viewLifecycleOwner, Observer {
            if (it.success != null) {
                var downloadEntries = ArrayList<Entry>()
                for (stats in it.success.downloads) {
                    Log.d("Full screen fragment", "count " + stats.count.toFloat())
                    downloadEntries.add(Entry(stats.date.time.toFloat(), stats.count.toFloat()))
                }
                var likesEntries = ArrayList<Entry>()
                for (stats in it.success.likes) {
                    Log.d("Full screen fragment", "count " + stats.count.toFloat())
                    likesEntries.add(Entry(stats.date.time.toFloat(), stats.count.toFloat()))
                }

                var viewsEntries = ArrayList<Entry>()
                for (stats in it.success.views) {
                    Log.d("Full screen fragment", "count " + stats.count.toFloat())
                    viewsEntries.add(Entry(stats.date.time.toFloat(), stats.count.toFloat()))
                }

                makeChart(downloadEntries, likesEntries, viewsEntries)
            }
        })
    }

    fun makeChart(
        downloadEntries: List<Entry>,
        likesEntries: List<Entry>,
        viewsEntries: List<Entry>
    ) {
        var downloadsDataSet = LineDataSet(downloadEntries, "Downloads")
        downloadsDataSet.setColor(Color.BLACK, 100)
        downloadsDataSet.setDrawCircles(false)
        downloadsDataSet.setDrawValues(true)
        downloadsDataSet.highlightLineWidth = 0f
        downloadsDataSet.lineWidth = 2f

        // downloadsDataSet.enableDashedLine(10f,10f,10f)
        var likesDataSet = LineDataSet(likesEntries, "Likes")
        likesDataSet.setDrawCircles(false)
        likesDataSet.setDrawValues(true)
        likesDataSet.setColor(Color.GREEN, 100)
        likesDataSet.highlightLineWidth = 0f
        likesDataSet.lineWidth = 2f

        var viewsDataSet = LineDataSet(viewsEntries, "Views")
        viewsDataSet.setColor(Color.BLUE, 100)
        viewsDataSet.setDrawCircles(false)
        viewsDataSet.setDrawValues(true)
        viewsDataSet.highlightLineWidth = 0f
        viewsDataSet.lineWidth = 2f


        var lines = ArrayList<LineDataSet>()
        lines.add(downloadsDataSet)
        lines.add(likesDataSet)
        lines.add(viewsDataSet)


       // chart.setDrawGridBackground(false)
        chart.description.isEnabled = false
        chart.setBackgroundColor(Color.WHITE)
        chart.axisRight.isEnabled = false


        var xAxisFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return fullScreenViewModel.mapDateToString(Date(value.toLong()))
            }
        }

        chart.xAxis.valueFormatter = xAxisFormatter
        chart.xAxis.labelRotationAngle = -45f
        chart.setVisibleXRange(5f,10f)
        chart.setMaxVisibleValueCount(10)
        chart.setPinchZoom(true)
        chart.isHorizontalScrollBarEnabled = true
        @RequiresApi(21)
        chart.isNestedScrollingEnabled = true
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        var lineData = LineData(lines.toMutableList() as List<ILineDataSet>?)
        chart.data = lineData
        chart.invalidate()
    }


}