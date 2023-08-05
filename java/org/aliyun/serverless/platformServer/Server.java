package org.aliyun.serverless.platformServer;

import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(50051)
                .addService(new org.aliyun.serverless.platformServer.PlatformServer())
                .executor(new ThreadPoolExecutor(16, 32, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(16)))
                .build()
                .start();
        System.out.println("Server started, listening on 50051");
        server.awaitTermination();
    }

}
