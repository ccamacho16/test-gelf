package com.test.exercise1

import com.test.exercise1.UserServiceGrpc.UserServiceImplBase
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
class UserServiceImpl: UserServiceImplBase() {
    override fun getUser(request: UserServiceOuterClass.GetUserRequest, responseObserver: StreamObserver<UserServiceOuterClass.UserResponse>) {
        val response = UserServiceOuterClass.UserResponse.newBuilder()
                .setUserId("123")
                .setName("Alice")
                .setEmail("alice@example.com")
                .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()

    }
}