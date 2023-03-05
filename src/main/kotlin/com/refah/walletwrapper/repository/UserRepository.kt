package com.refah.walletwrapper.repository

import com.refah.walletwrapper.model.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : BaseJpaRepository<User, Int> {
}