package com.monstock.route;
import org.apache.camel.builder.RouteBuilder;

public class RouteCreationProduits extends RouteBuilder {
    public void configure() throws Exception {
        //  créer un produit avec une route
        from("direct:RouteCreationProduits")
                .routeId("RouteCreationProduits");
               // .setHeader("CamelHttpMethod", constant("POST"))
              //  .to("https://montest.com/api/products")
    }
}
