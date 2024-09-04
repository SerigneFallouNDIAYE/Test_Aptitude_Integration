package com.monstock.mapper;

import com.monstock.model.ClientOrder;
import com.monstock.model.Contenu;
import com.monstock.model.Ligne;
import com.monstock.model.Order;
import com.monstock.model.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static Order mapClientOrderToOrder(ClientOrder clientOrder) {


        Contenu contenu = clientOrder.getContenu();
        Order order = new Order();

        order.setId(contenu.getId());
        order.setBranchsId(contenu.getSiteReception().getSiteId());
        order.setUserText5(contenu.getTypeMessage());
        order.setReference(contenu.getNumeroCommande());
        order.setDateOrder(contenu.getCreation() + "+0000");
        order.setDatereceiveEstimated(contenu.getDateReception() + "T00:00:00+0000");
        order.setWeight("0.000000");

        String totalQuantity = String.format("%.6f", contenu.getLignes().stream().mapToInt(Ligne::getQuantite).sum());
        order.setQuantity(totalQuantity);
        order.setQuantityReceive(totalQuantity);

        List<OrderItem> items = contenu.getLignes().stream().map(ligne -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(ligne.getId());
            orderItem.setIdPurchaseOrder(contenu.getId());
            orderItem.setIdProducts(ligne.getCodeProduit());
            orderItem.setQuantityOrder(String.format("%.6f", (double) ligne.getQuantite()));
            orderItem.setBranchsId(contenu.getSiteReception().getSiteId());
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(items);

        return order;
    }

}
