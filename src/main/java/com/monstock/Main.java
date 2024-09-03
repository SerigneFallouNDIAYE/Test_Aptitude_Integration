package com.monstock;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import com.monstock.route.OrderRoute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        CamelContext camelContext = new DefaultCamelContext();

        try {
            // Ajoutez les routes à Camel
            camelContext.addRoutes(new OrderRoute());
            camelContext.start();
            logger.info("Camel context started.");

            // Gardez Camel en cours d'exécution jusqu'à ce qu'une interruption se produise
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    camelContext.stop();
                    logger.info("Camel context stopped.");
                } catch (Exception e) {
                    logger.error("Error while stopping Camel context", e);
                }
            }));

            // Attendez que l'utilisateur interrompe l'application
            synchronized (Main.class) {
                Main.class.wait();
            }
        } catch (Exception e) {
            logger.error("Error occurred", e);
        } finally {
            try {
                camelContext.stop();
            } catch (Exception e) {
                logger.error("Error while stopping Camel context", e);
            }
        }
    }
}
