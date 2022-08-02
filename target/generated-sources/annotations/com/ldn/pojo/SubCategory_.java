package com.ldn.pojo;

import com.ldn.pojo.Category;
import com.ldn.pojo.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-08-02T09:58:34")
@StaticMetamodel(SubCategory.class)
public class SubCategory_ { 

    public static volatile CollectionAttribute<SubCategory, Product> productCollection;
    public static volatile SingularAttribute<SubCategory, String> name;
    public static volatile SingularAttribute<SubCategory, Integer> id;
    public static volatile SingularAttribute<SubCategory, Category> categoryId;

}