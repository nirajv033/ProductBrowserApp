package data

import io.ktor.client.HttpClient

expect object HttpClientFactory {
    fun createClient(): HttpClient
}