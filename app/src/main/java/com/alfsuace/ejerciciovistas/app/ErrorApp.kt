package com.miguelalvrub.ex02_formandstore.app

sealed class ErrorApp {
    object UnknowErrorApp : ErrorApp()
    object DataErrorApp: ErrorApp()
    object InternetConectionErrorApp: ErrorApp()
}