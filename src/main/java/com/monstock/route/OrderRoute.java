package com.monstock.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import java.util.Map;

public class OrderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // Route de lecture du fichier JSON
        from("file://input?noop=true")
                .routeId("OrderProcessingRoute")
                .unmarshal().json(JsonLibrary.Jackson, Map.class) // Déserialisation du JSON en Map Java
                .split(simple("${body['items']}")) // Divise la commande en lignes de commande
                .process(exchange -> {
                    Map<String, Object> order = exchange.getIn().getBody(Map.class);
                    exchange.setProperty("orderId", order.get("orderId"));
                    exchange.setProperty("orderDate", order.get("orderDate"));
                })
                .to("direct:processOrderItems") // Envoie les lignes de commande vers la route des lignes
                .end()
                .to("direct:processOrder"); // Envoie la commande principale vers sa route

        // Route pour traiter la commande principale
        from("direct:processOrder")
                .routeId("route1")
                .log("Traitement de la commande principale: ${exchangeProperty.orderId} à la date: ${exchangeProperty.orderDate}")
                .marshal().json(JsonLibrary.Jackson)
                .to("file://output/orders");

        // Route pour traiter les lignes de commande
        from("direct:processOrderItems")
                .routeId("route2")
                .log("Traitement de la ligne de commande: ${body}")
                .marshal().json(JsonLibrary.Jackson)
                .to("file://output/orderItems");
    }
}
