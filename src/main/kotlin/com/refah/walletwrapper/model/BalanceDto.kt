package com.refah.walletwrapper.model

data class BalanceDto(
    var Items: List<ItemBalance>,
    var Description: String = "transfer data",
    val Currency: String = "IRR",
    val TransactionType: Int = 204,
    var CashoutType: Int = 0,
    var ThirdPartyTransactionID: Int = 0
)

data class ItemBalance(
    var Source: ChildItemBalance,
    var Target: ChildItemBalance,
    var Amount: Long,
    var Description: String = "transfer data"
)

data class ChildItemBalance(
    var TenantID: Long,
    var AccountKind: Int? = 1,
    var MobileNumber: String? = null,
    var AccountNumberCode: String? = null
)