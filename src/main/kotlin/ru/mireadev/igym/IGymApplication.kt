package ru.mireadev.igym

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IGymApplication

fun main(args: Array<String>) {
    runApplication<IGymApplication>(*args)
}
