package com.example.countriesmvvm.domain.usecases

import com.example.countriesmvvm.data.common.ClientResult

abstract class UseCase<Input, Output> {
    abstract suspend fun call(param: Input): ClientResult<Output>
}