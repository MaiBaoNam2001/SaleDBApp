package com.mbn.services;

import com.mbn.pojo.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {
    private static final SessionFactory SESSION_FACTORY = HibernateUtils.getSessionFactory();

    public static List<Product> getProducts(String keyword, int page) {
        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);

            if (keyword != null && !keyword.isEmpty()) {
                Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), String.format("%%%s%%", keyword));
                criteriaQuery = criteriaQuery.where(predicate);
            }

            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
            Query query = session.createQuery(criteriaQuery);

            int max = 3;
            int index = (page - 1) * max;
            query.setFirstResult(index);
            query.setMaxResults(max);

            return query.getResultList();
        }
    }

    public static List<Product> getProducts(BigDecimal fromPrice, BigDecimal toPrice, int page) {
        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);

            if (fromPrice != null && toPrice != null) {
                Predicate predicate = criteriaBuilder.between(root.get("price").as(BigDecimal.class), fromPrice, toPrice);
                criteriaQuery = criteriaQuery.where(predicate);
            }

            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
            Query query = session.createQuery(criteriaQuery);

            int max = 3;
            int index = (page - 1) * max;
            query.setFirstResult(index);
            query.setMaxResults(max);

            return query.getResultList();
        }
    }

    public static List<Product> getProducts(int categoryId, int page) {
        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);

            if (categoryId > 0) {
                Predicate predicate = criteriaBuilder.equal(root.get("category"), categoryId);
                criteriaQuery = criteriaQuery.where(predicate);
            }

            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
            Query query = session.createQuery(criteriaQuery);

            int max = 3;
            int index = (page - 1) * max;
            query.setFirstResult(index);
            query.setMaxResults(max);

            return query.getResultList();
        }
    }

    public static List<Product> getProducts(Map<String, String> params, int page) {
        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);

            if (params != null) {
                List<Predicate> predicates = new ArrayList<>();
                if (params.containsKey("keyword")) {
                    String keyword = params.get("keyword");
                    Predicate p1 = criteriaBuilder.like(root.get("name").as(String.class), String.format("%%%s%%", keyword));
                    predicates.add(p1);
                }
                if (params.containsKey("fromPrice")) {
                    BigDecimal fromPrice = new BigDecimal(Double.parseDouble(params.get("fromPrice")));
                    Predicate p2 = criteriaBuilder.greaterThanOrEqualTo(root.get("price").as(BigDecimal.class), fromPrice);
                    predicates.add(p2);
                }
                if (params.containsKey("toPrice")) {
                    BigDecimal toPrice = new BigDecimal(Double.parseDouble(params.get("toPrice")));
                    Predicate p3 = criteriaBuilder.lessThanOrEqualTo(root.get("price").as(BigDecimal.class), toPrice);
                    predicates.add(p3);
                }
                if (params.containsKey("categoryId")) {
                    int categoryId = Integer.parseInt(params.get("categoryId"));
                    Predicate p4 = criteriaBuilder.equal(root.get("category"), categoryId);
                    predicates.add(p4);
                }
                criteriaQuery = criteriaQuery.where(predicates.toArray(Predicate[]::new));
            }
            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
            Query query = session.createQuery(criteriaQuery);

            int max = 3;
            int index = (page - 1) * max;
            query.setFirstResult(index);
            query.setMaxResults(max);

            return query.getResultList();
        }
    }
}
