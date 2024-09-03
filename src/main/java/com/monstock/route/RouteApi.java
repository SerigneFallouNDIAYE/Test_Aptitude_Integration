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
    }
}
