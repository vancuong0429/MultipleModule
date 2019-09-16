package com.example.home.views

import com.example.home.HomeViewModelJacob
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.home.domain.GetTopUsers
import com.example.model.views.User
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class HomeViewModelJacobTest {
    val DATE_REFRESH: Date = GregorianCalendar(2018, 5, 12).time
    val FAKE_USERS = listOf(
        User(id="Id_1", login = "Login_1", avatarUrl = "AvatarUrl_1", blog = "Blog1", company = "Company1", lastRefreshed = DATE_REFRESH, name = "Name1"),
        User(id="Id_2", login = "Login_2", avatarUrl = "AvatarUrl_2", blog = "Blog2", company = "Company2", lastRefreshed = DATE_REFRESH, name = "Name2"),
        User(id="Id_3", login = "Login_3", avatarUrl = "AvatarUrl_3", blog = "Blog3", company = "Company3", lastRefreshed = DATE_REFRESH, name = "Name3")
    )

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    lateinit var homeViewModel: HomeViewModelJacob
    private lateinit var getTopUsers: GetTopUsers

    private val testDispatcher = TestCoroutineDispatcher()
    @Before
    fun setUp() {
        getTopUsers = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun loadUsers() {

    }

    @Test
    fun `loadUsers_success`() {
        //GIVEN
        val result = Resource.success(FAKE_USERS)
        val observer = mockk<Observer<List<User>>>(relaxed = true)
        coEvery {
            getTopUsers.run()
        } returns result

        homeViewModel = HomeViewModelJacob(getTopUsers)
        homeViewModel.usersLiveData.observeForever(observer)

        //WHEN
        homeViewModel.loadUsers()

        //THEN
        coVerify {
            getTopUsers.run()
        }

        verify {
            observer.onChanged(result.data)
        }


        confirmVerified(observer)
    }
}