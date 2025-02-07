package com.test.exercise2

import io.grpc.Server
import io.grpc.ServerBuilder
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
class UserServiceImpl : UserServiceGrpc.UserServiceImplBase() {
    override fun listUsers(request: UserServiceOuterClass.ListUsersRequest, responseObserver: StreamObserver<UserServiceOuterClass.UserResponse>) {
        // Aquí puedes agregar tu lógica para listar usuarios
        val users = listOf(
                UserServiceOuterClass.UserResponse.newBuilder().setUserId("1").setName("John Doe").setEmail("john@example.com").build(),
                UserServiceOuterClass.UserResponse.newBuilder().setUserId("2").setName("Jane Smith").setEmail("jane@example.com").build()
        )

        for (user in users) {
            responseObserver.onNext(user)
        }
        responseObserver.onCompleted()

    }
}
fun main() {
    val server: Server = ServerBuilder.forPort(50051)
            .addService(UserServiceImpl())
            .build()

    server.start()
    println("Server started on port 50051")
    server.awaitTermination()
}