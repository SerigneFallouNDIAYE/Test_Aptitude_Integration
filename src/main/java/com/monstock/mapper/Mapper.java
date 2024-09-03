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
        Order order = new Order();
        Contenu contenu = clientOrder.getContenu();

        // Mapping de ClientOrder à Order
        order.setId(contenu.getId());
        order.setContactId(contenu.getFournisseur().getCode());
        order.setContactName(contenu.getFournisseur().getNom().trim());
        order.setBranchsId(contenu.getSiteReception().getSiteId());
        order.setBranchName(contenu.getSiteReception().getNomSite().trim());
        order.setUserText5(contenu.getTypeMessage());
        order.setReference(contenu.getNumeroCommande());
        order.setDateOrder(contenu.getCreation() + "+0000");
        order.setDatereceiveEstimated(contenu.getDateReception() != null ? contenu.getDateReception() + "T00:00:00+0000" : null);
        order.setWeight("0.000000"); // Valeur par défaut

        // Calcul de la quantité totale
        int totalQuantity = contenu.getLignes().stream().mapToInt(Ligne::getQuantite).sum();
        order.setQuantity(String.format("%.6f", (double) totalQuantity));
        order.setQuantityReceive(order.getQuantity());

        // Convertir les lignes en items
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
}
