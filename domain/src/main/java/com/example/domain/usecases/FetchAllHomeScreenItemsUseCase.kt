package com.example.domain.usecases

import com.example.domain.models.HomeScreenItems
import kotlinx.coroutines.flow.Flow

interface FetchAllHomeScreenItemsUseCase {

    operator fun invoke(): Flow<HomeScreenItems>
}