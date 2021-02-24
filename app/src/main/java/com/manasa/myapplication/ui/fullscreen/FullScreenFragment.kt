package com.manasa.myapplication.ui.fullscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.manasa.myapplication.R
import kotlinx.android.synthetic.main.fragment_full_screen.*
import kotlinx.android.synthetic.main.layout_photo_grid.*


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
        fun newInstance( photoId: String): FullScreenFragment {
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
        if(!photoId.isNullOrEmpty()) {
            fullScreenViewModel.getPhoto(photoId!!)
        }

        fullScreenViewModel.fullScreenData.observe(viewLifecycleOwner, Observer {

            if(it.error!=null){
                // show error
              layout_error_full_screen.visibility = View.VISIBLE
                iv_fullscreen_photo.visibility = View.GONE
            }else if(it.success!=null){
                iv_fullscreen_photo.setImageBitmap(it.success)
                iv_fullscreen_photo.visibility = View.VISIBLE
                layout_error_full_screen.visibility = View.GONE
            }
        })

        iv_fullscreen_close.setOnClickListener {
            (activity as FullScreenListener).onClose()
        }
    }
}