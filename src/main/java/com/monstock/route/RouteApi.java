package com.monstock.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component

public class RouteApi extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        from("direct:getProducts")
                .routeId("getProductsRoute")
                .setHeader("CamelHttpMethod", constant("GET"))
                .toD("https://montest.com/api/produits?query=${header.query}");
              //  .log("Réponse de l'API produits: ${body}");

        from("direct:getOrders")
                .routeId("getOrdersRoute")
                .setHeader("CamelHttpMethod", constant("GET"))
                .toD("https://montest.com/api/commandes?query=${header.query}");
              //  .log("Réponse de l'API commandes: ${body}");

        from("direct:createProduct")
                .routeId("createProductRoute")
                .setHeader("CamelHttpMethod", constant("POST"))
                .setHeader("Content-Type", constant("application/json"))
                .to("https://montest.com/api/products");
             //  .log("Produit créé avec succès: ${body}");


        from("timer:fetchProducts?period=60000")
                .routeId("fetchProductsRoute")
                .setHeader("CamelHttpMethod", constant("GET"))
                .setHeader("Accept", constant("application/json"))
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("CamelHttpQuery", simple("category=electronics&minPrice=100"))
                .to("https://montest.com/api/produits")
                .process(exchange -> {
                    // Traitement supplémentaire si nécessaire
                    String responseBody = exchange.getIn().getBody(String.class);
                    System.out.println("Réponse des produits: " + responseBody);
                });

        // Route pour récupérer des commandes avec un filtre
        from("timer:fetchOrders?period=120000")
                .routeId("fetchOrdersRoute")
                .setHeader("CamelHttpMethod", constant("GET"))
                .setHeader("Accept", constant("application/json"))
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("CamelHttpQuery", simple("status=completed&dateFrom=2024-01-01"))
                .to("https://montest.com/api/commandes")
                .log("Commandes récupérées: ${body}")
                .process(exchange -> {
                    String responseBody = exchange.getIn().getBody(String.class);
                    System.out.println("Réponse des commandes: " + responseBody);
                });

    }
}
