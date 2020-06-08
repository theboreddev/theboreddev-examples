package com.theboreddev.exercises.asyncjava;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class MyExample {


    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<Void> chain = new CompletableFuture<>();

        chain.thenComposeAsync(nil -> createUser())
                .thenAcceptAsync(logNewUserId())
                .thenComposeAsync(nil ->
                        registerAddress()
                                .thenAcceptBothAsync(registerPaymentDetails(), (address, paymentDetailsSuccess) -> {
                                    System.out.println("Registered address was : " + address);
                                    System.out.println("Registered payment details success : " + paymentDetailsSuccess);
                                })
                )
                .thenComposeAsync(nil -> sendEmail())
                .thenAcceptAsync(result -> System.out.println("Email sent : " + result));

        chain.complete(null);

        Thread.sleep(2000);
    }

    private static Consumer<String> logNewUserId() {
        return userId -> {
            System.out.println("User created with id : " + userId);
        };
    }

    private static CompletableFuture<String> createUser() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Create user start : " + TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            printCurrentThread("createUser");
            return UUID.randomUUID().toString();
        });
    }

    private static CompletableFuture<String> registerAddress() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Register address start : " + TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
            printCurrentThread("registerAddress");
            return "Flat 0, 100 Some Street. W1 XYZ";
        });
    }

    private static CompletableFuture<Boolean> registerPaymentDetails() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Register payment details start : " + TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS));
            printCurrentThread("paymentDetails");
            return true;
        });
    }

    private static CompletableFuture<Boolean> sendEmail() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Send email start : " + TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS));
            printCurrentThread("sendEmail");
            return true;
        });
    }

    private static void printCurrentThread(String prefix) {
        System.out.println(prefix + " - " + Thread.currentThread().getName());
    }
}
