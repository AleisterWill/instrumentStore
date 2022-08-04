package com.ldn.pojo;

import com.ldn.pojo.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-04T15:46:34")
@StaticMetamodel(Brand.class)
public class Brand_ { 

    public static volatile SingularAttribute<Brand, String> country;
    public static volatile CollectionAttribute<Brand, Product> productCollection;
    public static volatile SingularAttribute<Brand, String> name;
    public static volatile SingularAttribute<Brand, Integer> id;

}