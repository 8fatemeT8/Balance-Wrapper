package com.refah.walletwrapper.model

data class UserRegisterDto(
    var MobileNumber: String,
    var EmailAddress: String,
    var NationalCode: String,
    var NameFrst: String,
    var NameLast: String,
    var PIN: String = "1234",
    var WalletAmount: Int = 0,
    var PointAmount: Int = 0,
    var CustomerKind: Int = 1,
    var SettlementType: Int = 0,
    var UserGroupId: Int = 1,
    var ContractType: Int = 1,
    var SIMType: Int = 1
)