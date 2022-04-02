
package com.carlaospa.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher()) {
            for (var i = 0; i < 20; i++) {
                var key = UUID.randomUUID().toString();
                var value = "123456,65321,11.00";
                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                var email = "Obrigado! Nós processamos seu pedido!";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }
}
