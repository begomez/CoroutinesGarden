package com.example.coroutines


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coroutines.data.api.impl.FakePicsAPIImpl
import com.example.coroutines.presentation.viewmodel.MainFragmentViewModel
import com.example.coroutines.presentation.viewmodel.MainFragmentViewModelFactory
import com.example.coroutines.repository.impl.PicsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Unit test for main fragment view model
 *
 */
@RunWith(JUnit4::class)
class MainFragmentViewmodelTest {

    //XXX: thread executor for tests
    var dispatcher = TestCoroutineDispatcher()

    //XXX: post immediately results in viewmodel LiveData to main thread observers
    @get:Rule
    val executor = InstantTaskExecutorRule()

    //XXX: class under test...
    lateinit var viewmodel : MainFragmentViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(this.dispatcher)

        //XXX: repo with fake api
        var repo = PicsRepositoryImpl(FakePicsAPIImpl())

        this.viewmodel =
            MainFragmentViewModelFactory(repo).create(MainFragmentViewModel::class.java)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetList() {

        //XXX: "runBlockingTest" is similar to scope.lauch(i.e its a hook for coroutines when testing)
        this.dispatcher.runBlockingTest{

            // ARRANGE
            //XXX: already done in setUp()

            // ACT
            viewmodel.getList()

            // ASSERT
            assertTrue(viewmodel.exposedData.value != null)
            assertTrue(viewmodel.exposedData.value!!.size == 10)
        }
    }
}
