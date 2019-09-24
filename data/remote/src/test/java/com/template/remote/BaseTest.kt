package com.template.remote

import com.template.remote.di.createRemoteModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import java.io.File

abstract class BaseTest : KoinTest{
    protected lateinit var mockServer: MockWebServer
    protected val userDataSource: UserDataSource by inject()
    @Before
    open fun setUp() {
        configMockServer()
        startKoin { modules(listOf(createRemoteModule(mockServer.url("/").toString()))) }
    }

    @After
    open fun rearDown() {
        mockServer.shutdown()
        stopKoin()
    }

    private fun configMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    fun mockHttpResponse(mockWebServer: MockWebServer, fileName: String, responseCode: Int) = mockWebServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    fun getJson(path: String): String {
        val uri = javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}