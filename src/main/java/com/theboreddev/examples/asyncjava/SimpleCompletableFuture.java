package com.theboreddev.examples.asyncjava;

import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        completableFuture
                .thenAccept(System.out::println);

        completableFuture.complete("I have completed!");
    }
}
