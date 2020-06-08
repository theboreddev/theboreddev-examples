package com.theboreddev.examples.asyncjava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ExceptionallyAndTimeoutExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> subTask = new CompletableFuture<>();

        CompletableFuture<Result> chain = CompletableFuture.runAsync(() -> System.out.println("Start"))
                .thenCombine(subTask, (nil, text) -> {
                    if (text == null || text.isEmpty())
                        throw new IllegalArgumentException("Text cannot be null or empty!");
                    return Result.COMPLETED;
                })
                .completeOnTimeout(Result.TIMEOUT, 500, TimeUnit.MILLISECONDS)
                .exceptionally(exception -> Result.FAILED);

        final Result result = chain.get();
        System.out.println("Result is : " + result);
    }

    static enum Result {
        COMPLETED,
        FAILED,
        TIMEOUT
    }
}
