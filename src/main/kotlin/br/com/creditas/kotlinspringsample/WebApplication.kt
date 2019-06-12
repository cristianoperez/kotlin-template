package br.com.creditas.kotlinspringsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebApplication

fun main() {
	runApplication<WebApplication>()
}
