package com.kostikum.lifehackstudio.ui

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kostikum.lifehackstudio.R

class LocationFragment : SupportMapFragment(), OnMapReadyCallback {
    private val args: LocationFragmentArgs by navArgs()

    override fun onActivityCreated(bundle: Bundle?) {
        super.onActivityCreated(bundle)
        getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        val latLng = LatLng(args.lat.toDouble(), args.lon.toDouble())
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18.0f))
        map?.addMarker(MarkerOptions().position(latLng))
    }
}
