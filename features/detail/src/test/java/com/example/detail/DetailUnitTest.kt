package com.example.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.detail.domain.GetUserDetailCase
import com.example.model.views.User
import com.example.common.AppDispatchers
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class DetailUnitTest {

    val DATE_REFRESH: Date = GregorianCalendar(2018, 5, 12).time
    val FAKE_USERS = listOf(
        User(id="Id_1", login = "Login_1", avatarUrl = "AvatarUrl_1", blog = "Blog1", company = "Company1", lastRefreshed = DATE_REFRESH, name = "Name1"),
        User(id="Id_2", login = "Login_2", avatarUrl = "AvatarUrl_2", blog = "Blog2", company = "Company2", lastRefreshed = DATE_REFRESH, name = "Name2"),
        User(id="Id_3", login = "Login_3", avatarUrl = "AvatarUrl_3", blog = "Blog3", company = "Company3", lastRefreshed = DATE_REFRESH, name = "Name3")
    )

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getUserDetailCase: GetUserDetailCase
    private lateinit var viewModel: DetailViewModel
    private val appDispatchers =
        AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setup() {
        getUserDetailCase = mockk()
        viewModel = DetailViewModel(getUserDetailCase, appDispatchers)
    }

    @Test
    fun `loadUserDetailSuccess`() {
        //GIVEN
        val observerResult = mockk<Observer<User>>()
        val result = Resource.success(FAKE_USERS.first())

        viewModel.userDetail.observeForever(observerResult)

        coEvery{
            getUserDetailCase.run("fake")
        } returns result

        //WHEN
        viewModel.loadUserDetail("fake")

        verify {
            observerResult.onChanged(FAKE_USERS.first())
        }

        confirmVerified(observerResult)
    }

    @Test
    fun `loadUserDetailError`() {
        //GIVEN
        val observerResult = mockk<Observer<User>>()
        val error = Resource.error(Exception(), null)

        viewModel.userDetail.observeForever(observerResult)


        coEvery{
            getUserDetailCase.run("fake")
        } returns error

        //WHEN
        viewModel.loadUserDetail("fake")

        //THEN
        verify {
            observerResult.onChanged(null)
        }

        confirmVerified(observerResult)
    }
}