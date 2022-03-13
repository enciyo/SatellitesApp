package com.enciyo.satellitesapp.ui.satellites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Either
import com.enciyo.domain.SatellitesGetUseCase
import com.enciyo.domain.model.Satellite
import com.enciyo.satellitesapp.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SatellitesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher
        get() = mainCoroutineRule.testDispatcher


    private val satellitesGetUseCase: SatellitesGetUseCase = mockk()
    private lateinit var vm: SatellitesViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        vm = SatellitesViewModel(satellitesGetUseCase)
    }


    @Test
    fun `Get Satellites from UseCase return success`() =
        testDispatcher.runBlockingTest {
            //Given
            val dummyList = Either.Right(listOf<Satellite>())
            every { satellitesGetUseCase.invoke(any()) } returns flow {
                emit(dummyList)
            }
            //When
            vm.getSatellites()

            //Then
            assert(vm.satellites.value != null)
        }

    @Test
    fun `Get Satellites from UseCase return error`() =
        testDispatcher.runBlockingTest {
            //Given
            val exception = Either.Left(Exception(""))
            every { satellitesGetUseCase.invoke(any()) } returns flow {
                emit(exception)
            }

            //When
            vm.getSatellites()

            //Then
            assert(vm.satellites.value == null)
        }

}