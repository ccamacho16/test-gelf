package com.test.exercise1

import io.grpc.Server
import io.grpc.ServerBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Exercise1Application {
	private lateinit var server: Server

	private fun start() {
		server = ServerBuilder.forPort(50051)
				.addService(UserServiceImpl())
				.build()
				.start()
		println("Server started, listening on 50051")
		Runtime.getRuntime().addShutdownHook(Thread {
			println("Shutting down gRPC server...")
			server.shutdown()
			println("Server shut down")
		})
	}

	private fun blockUntilShutdown() {
		server.awaitTermination()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val app = Exercise1Application()
			app.start()
			app.blockUntilShutdown()
		}
	}

}

fun main(args: Array<String>) {
	runApplication<Exercise1Application>(*args)
}