package com.enciyo.satellitesapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Either
import com.enciyo.domain.SatelliteDetailGetUseCase
import com.enciyo.domain.SatelliteDetailPositionGetUseCase
import com.enciyo.domain.model.Position
import com.enciyo.domain.model.SatelliteDetail
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
class SatelliteDetailViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher
        get() = mainCoroutineRule.testDispatcher


    private val satelliteDetailGetUseCase: SatelliteDetailGetUseCase = mockk()
    private val satelliteDetailPositionGetUseCase: SatelliteDetailPositionGetUseCase = mockk()
    private lateinit var vm: SatelliteDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        vm = SatelliteDetailViewModel(satelliteDetailGetUseCase, satelliteDetailPositionGetUseCase)
        vm.oneTime = true
    }

    @Test
    fun `Get SatelliteDetail from satelliteDetailGetUseCase return success`() =
        testDispatcher.runBlockingTest {
            //Given
            val dummyList = Either.Right(SatelliteDetail(
                1, 1.3, "date", 1, 2
            ))
            every { satelliteDetailGetUseCase.invoke(any()) } returns flow {
                emit(dummyList)
            }
            every { satelliteDetailPositionGetUseCase.invoke(any()) } returns flow {
                emit(Either.Left(Exception()))
            }
            //When
            vm.getById(1)

            //Then
            assert(vm.detail.value != null)
        }

    @Test
    fun `Get SatelliteDetail from satelliteDetailGetUseCase return error`() =
        testDispatcher.runBlockingTest {
            //Given
            val error = Either.Left(Exception())
            every { satelliteDetailGetUseCase.invoke(any()) } returns flow {
                emit(error)
            }
            every { satelliteDetailPositionGetUseCase.invoke(any()) } returns flow {
                emit(error)
            }
            //When
            vm.getById(1)

            //Then
            assert(vm.detail.value == null)
        }


    @Test
    fun `Get SatelliteDetail from satelliteDetailPositionGetUseCase return success`() =
        testDispatcher.runBlockingTest {
            //Given
            val dummyList = Either.Right(listOf<Position>(
                Position(1.2,2.3)
            ))
            every { satelliteDetailPositionGetUseCase.invoke(any()) } returns flow {
                emit(dummyList)
            }
            val error = Either.Left(Exception())
            every { satelliteDetailGetUseCase.invoke(any()) } returns flow {
                emit(error)
            }
            //When
            vm.getById(1)


            //Then
            assert(vm.lastPosition.value != null)
        }

    @Test
    fun `Get SatelliteDetail from satelliteDetailPositionGetUseCase return error`() =
        testDispatcher.runBlockingTest {
            //Given
            val error = Either.Left(Exception())
            every { satelliteDetailPositionGetUseCase.invoke(any()) } returns flow {
                emit(error)
            }
            every { satelliteDetailGetUseCase.invoke(any()) } returns flow {
                emit(error)
            }
            //When
            vm.getById(1)

            //Then
            assert(vm.lastPosition.value == null)
        }


}