package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.grpc.MapServiceGrpc;
import com.example.grpc.MapServiceOuterClass;

public class MapClient
{
    public static void main( String[] args ) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forTarget("localhost:8888")
                .usePlaintext()
                .build();

        MapServiceGrpc.MapServiceBlockingStub mapServiceBlockingStub =
                MapServiceGrpc.newBlockingStub(managedChannel);
        MapServiceOuterClass.MapRequest mapRequest = MapServiceOuterClass.MapRequest
                .newBuilder().setQuery("Query map").build();
        MapServiceOuterClass.MapResponse mapResponse = mapServiceBlockingStub.takeMap(mapRequest);
        System.out.println(mapResponse);

        managedChannel.shutdownNow();
    }
}
