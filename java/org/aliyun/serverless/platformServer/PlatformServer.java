package org.aliyun.serverless.platformServer;

import io.grpc.stub.StreamObserver;
import protobuf.PlatformGrpc;
import protobuf.SchedulerProto;

import java.util.Random;
import java.util.UUID;

public class PlatformServer extends PlatformGrpc.PlatformImplBase {

    @Override
    public void createSlot(SchedulerProto.CreateSlotRequest request, StreamObserver<SchedulerProto.CreateSlotReply> replyStreamObserver) {
        SchedulerProto.CreateSlotReply reply = SchedulerProto.CreateSlotReply.newBuilder()
                .setSlot(SchedulerProto.Slot.newBuilder().setId(UUID.randomUUID().toString())
                        .setCreateDurationInMs(10L)
                        .setCreateTime(System.currentTimeMillis())
                        .setResourceConfig(SchedulerProto.ResourceConfig.newBuilder()
                                .setMemoryInMegabytes(100L).build()))
                .setStatus(SchedulerProto.Status.Ok)
                .build();
        replyStreamObserver.onNext(reply);
        replyStreamObserver.onCompleted();
    }

    @Override
    public void init(SchedulerProto.InitRequest request, StreamObserver<SchedulerProto.InitReply> replyStreamObserver) {
        SchedulerProto.InitReply reply = SchedulerProto.InitReply.newBuilder()
                .setCreateTime(System.currentTimeMillis())
                .setStatus(SchedulerProto.Status.Ok)
                .setInitDurationInMs(10L)
                .build();
        replyStreamObserver.onNext(reply);
        replyStreamObserver.onCompleted();
    }

}
