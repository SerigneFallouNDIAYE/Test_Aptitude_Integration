package com.monstock.mapper;

import com.monstock.model.ClientOrder;
import com.monstock.model.Contenu;
import com.monstock.model.Fournisseur;
import com.monstock.model.Ligne;
import com.monstock.model.Order;
import com.monstock.model.OrderItem;
import com.monstock.model.SiteReception;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Order mapClientOrderToOrder(ClientOrder clientOrder) {
        if (clientOrder == null || clientOrder.getContenu() == null) {
            throw new IllegalArgumentException("ClientOrder ou Contenu ne peut pas être null.");
        }

        Contenu contenu = clientOrder.getContenu();
        Order order = new Order();

        // Mapper les champs
        order.setId(contenu.getId());
        order.setContactId(safeTrim(contenu.getFournisseur().getCode()));
        order.setContactName(safeTrim(contenu.getFournisseur().getNom()));
        order.setBranchsId(contenu.getSiteReception().getSiteId());
        order.setBranchName(safeTrim(contenu.getSiteReception().getNomSite()));
        order.setUserText5(contenu.getTypeMessage());
        order.setReference(contenu.getNumeroCommande());
        order.setDateOrder(contenu.getCreation() + "+0000");
        order.setDatereceiveEstimated(
                contenu.getDateReception() != null ? contenu.getDateReception() + "T00:00:00+0000" : null
        );
        order.setWeight("0.000000");
        int totalQuantity = contenu.getLignes().stream().mapToInt(Ligne::getQuantite).sum();
        order.setQuantity(String.format("%.6f", (double) totalQuantity));
        order.setQuantityReceive(order.getQuantity());

        // Conversion dees lignes en items
        List<OrderItem> items = new ArrayList<>();
        for (Ligne ligne : contenu.getLignes()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(ligne.getId());
            orderItem.setIdPurchaseOrder(contenu.getId());
            orderItem.setIdProducts(ligne.getCodeProduit());
            orderItem.setQuantityOrder(String.format("%.6f", (double) ligne.getQuantite()));
            orderItem.setBranchsId(contenu.getSiteReception().getSiteId());
            items.add(orderItem);
        }
        order.setItems(items);

        return order;
    }
    private static String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}