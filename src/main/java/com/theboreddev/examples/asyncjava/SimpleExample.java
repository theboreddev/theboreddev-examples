package com.theboreddev.exercises.asyncjava;

import java.util.concurrent.CompletableFuture;

public class SimpleExample {

    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        completableFuture
                .thenAccept(System.out::println);

        completableFuture.complete("I have completed!");
    }
}
