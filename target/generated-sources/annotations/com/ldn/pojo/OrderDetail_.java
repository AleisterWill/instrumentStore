package com.ldn.pojo;

import com.ldn.pojo.Order1;
import com.ldn.pojo.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-06-23T13:37:25")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Long> unitPrice;
    public static volatile SingularAttribute<OrderDetail, Product> productId;
    public static volatile SingularAttribute<OrderDetail, Long> totalPrice;
    public static volatile SingularAttribute<OrderDetail, Order1> orderId;
    public static volatile SingularAttribute<OrderDetail, Integer> id;

}