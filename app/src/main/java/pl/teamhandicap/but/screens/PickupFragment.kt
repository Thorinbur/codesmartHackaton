package pl.teamhandicap.but.screens

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_new_order.*
import pl.teamhandicap.but.R
import com.google.zxing.WriterException

import android.R.attr.bitmap
import android.graphics.Color

import androidmads.library.qrgenearator.QRGContents

import androidmads.library.qrgenearator.QRGEncoder
import kotlinx.android.synthetic.main.fragment_pick_up.*

class PickupFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pick_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val qrgEncoder = QRGEncoder("testestesteasdew", null, QRGContents.Type.TEXT, 600 )
        qrgEncoder.colorBlack = Color.BLACK
        qrgEncoder.colorWhite = Color.WHITE
        try {
            // Getting QR-Code as Bitmap
            val bitmap = qrgEncoder.bitmap
            // Setting Bitmap to ImageView
            qrCode.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            //ignore
        }
    }
}