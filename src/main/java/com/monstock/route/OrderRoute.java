package com.monstock.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.monstock.mapper.Mapper;
import com.monstock.model.ClientOrder;
import com.monstock.model.Order;

@Component
public class OrderRoute extends RouteBuilder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure() throws Exception {
        // Route pour lire un fichier JSON
        from("file://input?noop=true")
                .routeId("OrderProcessingRoute")
                .process(exchange -> {
                    String json = exchange.getIn().getBody(String.class);
                    ClientOrder clientOrder = objectMapper.readValue(json, ClientOrder.class);

                    Order order = Mapper.mapClientOrderToOrder(clientOrder);

                    exchange.getIn().setBody(order);

                    exchange.setProperty("orderId", order.getId());
                    exchange.setProperty("orderDate", order.getDateOrder());
                })
        ;
        from("direct:Mapper")
                .routeId("Mapper")
                .log("Traitement de la commande principale: ${exchangeProperty.orderId} à la date: ${exchangeProperty.orderDate}")
                .marshal().json()
                .to("https://montest.com/api/commandes");

        from("direct:Mapper")
                .routeId("Mapper")
                .split().body()
                .marshal().json()
                .to("https://montest.com/api/produits");

    }
}
