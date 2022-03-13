package com.enciyo.satellitesapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.enciyo.domain.model.Position
import com.enciyo.domain.model.SatelliteDetail
import com.enciyo.satellitesapp.R
import com.enciyo.satellitesapp.databinding.SatelliteDetailFragmentBinding
import com.enciyo.satellitesapp.ui.base.BaseFragment
import com.enciyo.satellitesapp.ext.toBold
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat

@AndroidEntryPoint
class SatelliteDetailFragment :
    BaseFragment<SatelliteDetailFragmentBinding, SatelliteDetailViewModel>(
        layoutRes = R.layout.satellite_detail_fragment,
        vbFactory = SatelliteDetailFragmentBinding::bind,
        vmClass = SatelliteDetailViewModel::class) {

    private val args by navArgs<SatelliteDetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(vm) {
            detail.observe(viewLifecycleOwner, ::observeDetail)
            lastPosition.observe(viewLifecycleOwner, ::observeLastPosition)
            getById(args.id)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeLastPosition(position: Position) {
        binding.lastPosition.text =
            ((getString(R.string.lastPosition)) + " (${position.posX},${position.posY})")
                .toBold(getString(R.string.lastPosition))
    }

    @SuppressLint("SetTextI18n")
    private fun observeDetail(result: SatelliteDetail) {
        with(binding) {
            name.text = args.name
            time.text = result.firstFlight
            cost.text =
                (getString(R.string.cost) + result.costPerLaunch.toPrice().toString()).toBold(getString(R.string.cost))
            heightAndMass.text =
                (getString(R.string.heightAndMass) + result.height.toString() + "/" + result.mass.toString())
                    .toBold(getString(R.string.heightAndMass))
        }
    }

    fun Double.toPrice(): String {
        val formatter = NumberFormat.getNumberInstance().format(this)
            .replace(",",".")
        return formatter.format(this)
    }

}