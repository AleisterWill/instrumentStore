package com.ldn.pojo;

import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-07-02T16:18:29")
@StaticMetamodel(Order1.class)
public class Order1_ { 

    public static volatile SingularAttribute<Order1, Date> createdDate;
    public static volatile CollectionAttribute<Order1, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Order1, Long> totalPrice;
    public static volatile SingularAttribute<Order1, Integer> id;
    public static volatile SingularAttribute<Order1, User> userId;
    public static volatile SingularAttribute<Order1, String> status;

}