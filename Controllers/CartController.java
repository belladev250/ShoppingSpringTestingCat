package com.example.crudtestapis.Controllers;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.crudtestapis.model.Item;
import com.example.crudtestapis.model.Product;

@Controller
@RequestMapping(value = "cart")
public class CartController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") String id, HttpSession session) {
        Product productModel = new Product();
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            cart.add(new AbstractReadWriteAccess.Item(productModel.find(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = this.exists(id, cart);
            if (index == -1) {
                cart.add(new Item(productModel.find(id), 1));
            } else {
                int quantity = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }

    }

    @RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") String id, HttpSession session) {
        Product productModel = new Product();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int index = this.exists(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);

    }

    private int exists(String id, List<Item> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

}
