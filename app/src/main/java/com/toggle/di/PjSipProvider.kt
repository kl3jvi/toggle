package com.toggle.di

import com.toggle.services.MyAccount
import com.toggle.services.MyLogWriter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.pjsip.pjsua2.Endpoint
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PjSipProvider {

    @Provides
    @Singleton
    fun provideEndpoint(): Endpoint {
        return Endpoint()
    }




//    lateinit var logWriter: MyLogWriter
//
//    val ep = Endpoint()
//    val acc = MyAccount()
}