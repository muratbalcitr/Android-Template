package com.m2lifeApps.data.repository

import com.m2lifeApps.data.remote.ProjectService
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val projectService: ProjectService
) {

    suspend fun fetchPopular() = projectService.fetchPopular()
}
