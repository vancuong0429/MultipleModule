package com.template.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.template.common.AppDispatchers
import com.template.common.exception.Failure
import com.template.common_jvm.functional.Either
import com.template.domain.entities.UserEntity
import com.template.domain.usecases.GetUserDetailUseCase
import io.mockk.*
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
        UserEntity(id="Id_1", login = "Login_1", avatarUrl = "AvatarUrl_1", blog = "Blog1", company = "Company1", lastRefreshed = DATE_REFRESH, name = "Name1"),
        UserEntity(id="Id_2", login = "Login_2", avatarUrl = "AvatarUrl_2", blog = "Blog2", company = "Company2", lastRefreshed = DATE_REFRESH, name = "Name2"),
        UserEntity(id="Id_3", login = "Login_3", avatarUrl = "AvatarUrl_3", blog = "Blog3", company = "Company3", lastRefreshed = DATE_REFRESH, name = "Name3")
    )

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getUserDetailCase: GetUserDetailUseCase
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
        val observerResult = mockk<Observer<UserEntity>>()
        val result = Either.Success(FAKE_USERS.first())

        coEvery{
            getUserDetailCase.run("fake")
        } returns result

        viewModel.userDetail.observeForever(observerResult)

        every {
            observerResult.onChanged(any())
        } just Runs

        //WHEN
        viewModel.loadUserDetail("fake")


        // THEN
        verify {
            observerResult.onChanged(FAKE_USERS.first())
        }

        confirmVerified(observerResult)
    }

    @Test
    fun `loadUserDetailError`() {
        //GIVEN
        val observerResult = mockk<Observer<UserEntity>>()
        val error = Either.Fail(Failure.ConnectionTimeout)

        viewModel.userDetail.observeForever(observerResult)


        coEvery{
            getUserDetailCase.run("fake")
        } returns error

        every {
            observerResult.onChanged(any())
        } just Runs

        //WHEN
        viewModel.loadUserDetail("fake")

        //THEN
        verify {
            observerResult.onChanged(null)
        }

        confirmVerified(observerResult)
    }
}