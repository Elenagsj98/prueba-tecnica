import org.junit.Test

class Question1Test {

    fun combineOutputs(priorityResult: CustomResult, secondaryResult: CustomResult): CustomResult {
        return when {
            priorityResult is CustomResult.RSuccess && secondaryResult is CustomResult.RSuccess -> {
                if (priorityResult.product.id == secondaryResult.product.id) {
                    priorityResult.apply {
                        product = product.copy(
                            amount = product.amount + secondaryResult.product.amount,
                            providers = product.providers.toMutableList()
                                .apply { addAll(secondaryResult.product.providers) }
                        )
                    }
                } else {
                    priorityResult
                }
            }

            else -> {
                CustomResult.RCriticalError
            }
        }
    }

    fun combineOutputsSimplified(
        priorityResult: CustomResult,
        secondaryResult: CustomResult
    ): CustomResult = when {
        priorityResult is CustomResult.RSuccess && secondaryResult is CustomResult.RSuccess &&
                priorityResult.product.id == secondaryResult.product.id -> priorityResult.copy(
            product = priorityResult.product.copy(
                amount = priorityResult.product.amount + secondaryResult.product.amount,
                providers = priorityResult.product.providers + secondaryResult.product.providers
            )
        )

        else -> CustomResult.RCriticalError
    }

    @Test
    fun `test any critical error received`() {
        val priorityResult = CustomResult.RSuccess(ProductData("id1", 4, listOf("provider1")))
        val secondaryResult = CustomResult.RCriticalError

        combineOutputsSimplified(priorityResult, secondaryResult)
    }

    @Test
    fun `test successful results, return only priority result`() {
        val priorityResult = CustomResult.RSuccess(ProductData("id1", 4, listOf("provider1")))
        val secondaryResult = CustomResult.RSuccess(ProductData("id2", 3, listOf("provider2")))

        combineOutputsSimplified(priorityResult, secondaryResult)
    }


    @Test
    fun `test successful results, combine output`() {
        val product1 = ProductData("id1", 4, listOf("provider1"))
        val product2 = ProductData("id1", 3, listOf("provider2", "provider3"))

        val priorityResult = CustomResult.RSuccess(product1)
        val secondaryResult = CustomResult.RSuccess(product2)

        combineOutputsSimplified(priorityResult, secondaryResult)
    }

}