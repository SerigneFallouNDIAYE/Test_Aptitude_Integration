package com.monstock.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component // Assurez-vous que cette annotation est présente
public class RouteApi extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Route pour récupérer des produits avec un filtre
        from("timer:fetchProducts?period=60000") // Démarre la route toutes les 60 secondes
                .routeId("fetchProductsRoute")
                .setHeader("CamelHttpMethod", constant("GET")) // Définir le verbe HTTP
                .setHeader("Accept", constant("application/json")) // Définir le type de contenu accepté
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("CamelHttpQuery", simple("category=electronics&minPrice=100")) // Ajouter des filtres (exemple)
                .to("https://montest.com/api/produits") // Appel de l'API des produits
                .log("Produits récupérés: ${body}")
                .process(exchange -> {
                    // Traitement supplémentaire si nécessaire
                    String responseBody = exchange.getIn().getBody(String.class);
                    System.out.println("Réponse des produits: " + responseBody);
                });

        // Route pour récupérer des commandes avec un filtre
        from("timer:fetchOrders?period=120000") // Démarre la route toutes les 120 secondes
                .routeId("fetchOrdersRoute")
                .setHeader("CamelHttpMethod", constant("GET"))
                .setHeader("Accept", constant("application/json"))
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("CamelHttpQuery", simple("status=completed&dateFrom=2024-01-01")) // Ajouter des filtres (exemple)
                .to("https://montest.com/api/commandes") // Appel de l'API des commandes
                .log("Commandes récupérées: ${body}")
                .process(exchange -> {
                    // Traitement supplémentaire si nécessaire
                    String responseBody = exchange.getIn().getBody(String.class);
                    System.out.println("Réponse des commandes: " + responseBody);
                });
    }
}
