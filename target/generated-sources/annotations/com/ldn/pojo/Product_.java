package com.ldn.pojo;

import com.ldn.pojo.Brand;
import com.ldn.pojo.Comment;
import com.ldn.pojo.ImageSet;
import com.ldn.pojo.OrderDetail;
import com.ldn.pojo.SubCategory;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-09-08T14:49:08")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Date> createdDate;
    public static volatile SingularAttribute<Product, ImageSet> imageSetId;
    public static volatile CollectionAttribute<Product, OrderDetail> orderDetailCollection;
    public static volatile SingularAttribute<Product, Long> price;
    public static volatile CollectionAttribute<Product, Comment> commentCollection;
    public static volatile SingularAttribute<Product, Brand> brandId;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> inStock;
    public static volatile SingularAttribute<Product, SubCategory> subCategoryId;
    public static volatile SingularAttribute<Product, Integer> id;

}