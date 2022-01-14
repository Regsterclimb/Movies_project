package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.data.NetworkModule.NetworkModuleResponses

interface NetworkModuleProvider {

    fun provideNetworkModule() : NetworkModuleResponses
}