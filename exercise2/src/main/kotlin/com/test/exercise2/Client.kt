package com.test.exercise2
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver

/**-------------------------------------------------------------------------*
 * Información General
 *-------------------------------------------------------------------------*
 * Código de Aplicación:
 * Código de Objeto:
 * Descripción:
 * Author Prog.: Crisvel Camacho
 *-------------------------------------------------------------------------*
 * Fecha | Author | Comentario
 * 07.02.2025 | Crisvel Camacho | Creación Inicial
 *-------------------------------------------------------------------------*
 */




fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build()

    val stub = UserServiceGrpc.newStub(channel)
    val request = UserServiceOuterClass.ListUsersRequest.newBuilder().setFilter("active").build()

    val responseObserver = object : StreamObserver<UserServiceOuterClass.UserResponse> {
        override fun onNext(value: UserServiceOuterClass.UserResponse) {
            println("Received user: ${value.userId}, ${value.name}, ${value.email}")
        }

        override fun onError(t: Throwable) {
            println("Error receiving user: ${t.message}")
        }

        override fun onCompleted() {
            println("All users received.")
            channel.shutdown()
        }
    }

    stub.listUsers(request, responseObserver)
}