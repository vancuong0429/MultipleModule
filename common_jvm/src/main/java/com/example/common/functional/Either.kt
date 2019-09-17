package com.example.common.functional

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Failure<out L>(val a: L) : Either<L, Nothing>()
    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isFailure get() = this is Failure<L>

    fun <L> fail(a: L) = Either.Failure(a)
    fun <R> success(b: R) = Either.Success(b)

    fun either(failAction: (L) -> Any, successAction: (R) -> Any): Any =
        when (this) {
            is Failure -> failAction(a)
            is Success -> successAction(b)
        }
}

// Credits to Alex Hart -> https://proandroiddev.com/kotlins-nothing-type-946de7d464fb
// Composes 2 functions
fun <A, B, C> ((A) -> B).compose(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Failure -> Either.Failure(a)
        is Either.Success -> fn(b)
    }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.compose(::success))