package com.test.exercise1

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

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import com.test.exercise1.UserServiceGrpc.UserServiceBlockingStub

fun main(args: Array<String>) {
    val channel: ManagedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build()
    try {
        val stub: UserServiceBlockingStub = UserServiceGrpc.newBlockingStub(channel)

        val request: UserServiceOuterClass.GetUserRequest = UserServiceOuterClass.GetUserRequest.newBuilder()
                .setUserId("123")
                .build()

        val response = stub.getUser(request)

        println("User ID: ${response.userId}")
        println("Name: ${response.name}")
        println("Email: ${response.email}")

    } finally {
        channel.shutdown()
    }
}