package com.mbn.services;

import com.mbn.pojo.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class SaleOrderService {
    private static final SessionFactory SESSION_FACTORY = HibernateUtils.getSessionFactory();

    public static boolean addSaleOrder(List<Cart> carts, int userId) {
        try (Session session = SESSION_FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                SaleOrder order = new SaleOrder();
                order.setCreatedDate(new Date());
                order.setUser(session.get(User.class, userId));
                session.save(order);

                carts.forEach(cart -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setUnitPrice(cart.getPrice());
                    orderDetail.setNum(cart.getQuantity());
                    orderDetail.setProduct(session.get(Product.class, cart.getId()));
                    orderDetail.setSaleOrder(order);
                    session.save(orderDetail);
                });
                session.getTransaction().commit();
                return true;
            } catch (HibernateException ex) {
                session.getTransaction().rollback();
                ex.printStackTrace();
            }
        }
        return false;
    }
}
