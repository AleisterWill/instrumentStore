package com.ldn.pojo;

import com.ldn.pojo.ImagePath;
import com.ldn.pojo.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-09-08T14:49:08")
@StaticMetamodel(ImageSet.class)
public class ImageSet_ { 

    public static volatile CollectionAttribute<ImageSet, Product> productCollection;
    public static volatile SingularAttribute<ImageSet, String> description;
    public static volatile SingularAttribute<ImageSet, Integer> id;
    public static volatile CollectionAttribute<ImageSet, ImagePath> imagePathCollection;

}