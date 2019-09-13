package com.example.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.common.Event
import com.example.home.domain.GetTopUsers
import com.example.home.views.HomeFragmentDirections
import com.example.model.views.User
import com.example.navigation.NavigationCommand
import com.example.repository.AppDispatchers
import com.example.repository.Resource
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception
import java.util.*

@RunWith(JUnit4::class)
class HomeUnitTest {

    val DATE_REFRESH: Date = GregorianCalendar(2018, 5, 12).time
    val FAKE_USERS = listOf(
        User(id="Id_1", login = "Login_1", avatarUrl = "AvatarUrl_1", blog = "Blog1", company = "Company1", lastRefreshed = DATE_REFRESH, name = "Name1"),
        User(id="Id_2", login = "Login_2", avatarUrl = "AvatarUrl_2", blog = "Blog2", company = "Company2", lastRefreshed = DATE_REFRESH, name = "Name2"),
        User(id="Id_3", login = "Login_3", avatarUrl = "AvatarUrl_3", blog = "Blog3", company = "Company3", lastRefreshed = DATE_REFRESH, name = "Name3")
    )

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var homeViewModel: HomeViewModel
    private lateinit var getTopUsers: GetTopUsers

    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        getTopUsers = mockk()
    }

    @Test
    fun `loadUsers_success`() {
        //GIVEN
        val result = Resource.success(FAKE_USERS)
        val observer = mockk<Observer<List<User>>>(relaxed = true)
        coEvery {
            getTopUsers.run()
        } returns result

        homeViewModel = HomeViewModel(getTopUsers, appDispatchers)
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

    @Test
    fun `loadUsers_error`() {
        //GIVEN
        val result = Resource.error(Exception(), FAKE_USERS)
        val observer = mockk<Observer<List<User>>>(relaxed = true)
        coEvery {
            getTopUsers.run()
        } returns result

        homeViewModel = HomeViewModel(getTopUsers, appDispatchers)
        homeViewModel.usersLiveData.observeForever(observer)

        //WHEN
        homeViewModel.loadUsers()

        //THEN
        coVerify {
            getTopUsers.run()
        }

        verify {
            observer.onChanged(null)
        }

        confirmVerified(observer)
    }

    @Test
    fun `loadUsersCallback_error`() {
        //GIVEN
        val result = Resource.error(Exception(), FAKE_USERS)
        val observer = mockk<Observer<List<User>>>(relaxed = true)

        val callBack = slot<CallBackI>()
        every {
            getTopUsers.runCallback(
                callBackI = capture(callBack)
            )
        } answers {
            callBack.captured.onError()
        }

        homeViewModel = HomeViewModel(getTopUsers, appDispatchers)
        homeViewModel.usersLiveData.observeForever(observer)

        //WHEN
        homeViewModel.loadUsersCallback()

        //THEN
        verify {
            observer.onChanged(null)
        }

        confirmVerified(observer)
    }

    @Test
    fun `loadUsersCallback_success`() {
        //GIVEN
        val result = Resource.error(Exception(), FAKE_USERS)
        val observer = mockk<Observer<List<User>>>(relaxed = true)

        val callBack = slot<CallBackI>()
        every {
            getTopUsers.runCallback(
                callBackI = capture(callBack)
            )
        } answers {
            callBack.captured.onSuccess(result.data!!)
        }

        homeViewModel = HomeViewModel(getTopUsers, appDispatchers)
        homeViewModel.usersLiveData.observeForever(observer)

        //WHEN
        homeViewModel.loadUsersCallback()

        //THEN
        verify {
            observer.onChanged(FAKE_USERS)
        }

        confirmVerified(observer)
    }

    @Test
    fun `User clicks on item on RecyclerView`() {
        //GIVEN
        homeViewModel = HomeViewModel(getTopUsers, appDispatchers)
        val event = Event(NavigationCommand.To(HomeFragmentDirections.actionHomeFragmentToDetailFragment(FAKE_USERS.first().login)))

        //WHEN
        homeViewModel.userClicksOnItem(FAKE_USERS.first())

        //THEN
        Assert.assertEquals(event.peekContent(), homeViewModel.navigation.value?.peekContent())
    }

}