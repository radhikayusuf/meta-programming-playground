package io.radhika

import io.radhika.parser.CTMP
import io.radhika.parser.Manual
import io.radhika.parser.Parser
import io.radhika.parser.RTMP
import kotlin.math.roundToInt

class Main {
    private val manualParser: Parser = Manual()
    private val rtmpParser: Parser = RTMP()
    private val ctmpParser: Parser = CTMP()

    fun runPlayground() {
        val json = """{"first_name":"Foo", "last_name":"Bar", "age":25, "phone_number":"08111111111"}"""
        benchmark("Manual") {
            manualParser.parse(json)
        }

        benchmark("RTMP") {
            rtmpParser.parse(json)
        }

        benchmark("CTMP") {
            ctmpParser.parse(json)
        }
    }

    private fun benchmark(name: String, n: Int = 1000, func: () -> Unit) {
        val executions = arrayListOf<Long>()
        for (i in 0 until n) {
            val startMillis = System.currentTimeMillis()
            func()
            val executionTime = System.currentTimeMillis() - startMillis
            executions.add(executionTime)
        }

        val sortedResult = executions.sorted()
        println("\n\n$name - benchmark:")
        println("$name - P100: ${sortedResult[n -1]}ms")
        println("$name - P99: ${sortedResult[(n * 0.99).roundToInt()]}ms")
        println("$name - P90: ${sortedResult[(n * 0.90).roundToInt()]}ms")
        println("$name - P50: ${sortedResult[n / 2]}ms")
        println("$name - P10: ${sortedResult[(n * 0.1).roundToInt()]}ms")
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val main = Main()
            main.runPlayground()
        }
    }
}