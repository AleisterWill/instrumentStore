package com.ldn.pojo;

import com.ldn.pojo.Brand;
import com.ldn.pojo.Comment;
import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.Storage;
import com.ldn.pojo.SubCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-06-23T13:37:25")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, String> image;
    public static volatile CollectionAttribute<Product, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Product, Long> price;
    public static volatile SingularAttribute<Product, Brand> brandId;
    public static volatile CollectionAttribute<Product, Comment> commentCollection;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, SubCategory> subCategoryId;
    public static volatile SingularAttribute<Product, Storage> storage;

}