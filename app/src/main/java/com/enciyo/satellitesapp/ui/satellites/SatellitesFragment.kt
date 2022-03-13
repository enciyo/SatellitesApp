package com.enciyo.satellitesapp.ui.satellites

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.enciyo.domain.model.Satellite
import com.enciyo.satellitesapp.R
import com.enciyo.satellitesapp.databinding.SatellitesFragmentBinding
import com.enciyo.satellitesapp.ui.base.BaseFragment
import com.enciyo.satellitesapp.ui.ext.attach
import com.enciyo.satellitesapp.ui.ext.detach
import com.enciyo.satellitesapp.ui.ext.dividerDecoration
import com.enciyo.satellitesapp.ui.ext.linearLayoutManager
import com.enciyo.satellitesapp.ui.ext.queryTextChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SatellitesFragment : BaseFragment<SatellitesFragmentBinding, SatellitesViewModel>(
    layoutRes = R.layout.satellites_fragment,
    vbFactory = SatellitesFragmentBinding::bind,
    vmClass = SatellitesViewModel::class
) {

    private val adapter by lazy { SatellitesAdapter(::onClicked) }
    private val layoutManager by linearLayoutManager()
    private val itemDecoration by dividerDecoration()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            satellites.attach(
                adapter = adapter,
                layoutManager = layoutManager,
                decorator = itemDecoration
            )
            searchView.queryTextChanges()
                .onEach(adapter.filter::filter)
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }

        with(vm) {
            satellites.observe(viewLifecycleOwner, ::observeSatellites)
        }
    }

    private fun onClicked(satellite: Satellite) {
        SatellitesFragmentDirections.actionSatellitesFragmentToSatelliteDetailFragment(
            id = satellite.id,
            name = satellite.name
        ).also(findNavController()::navigate)
    }

    private fun observeSatellites(result: List<Satellite>) {
        adapter.submitList(result)
    }

    override fun onClearReferences(safeBinding: SatellitesFragmentBinding) {
        safeBinding.satellites.detach()
    }

    override fun loadingState(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

}