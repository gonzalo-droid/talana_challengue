package com.box.talana.domian.useCases

import com.box.talana.core.funtional.Either
import com.box.talana.core.funtional.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        val job = GlobalScope.async { run(params) }

        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}
