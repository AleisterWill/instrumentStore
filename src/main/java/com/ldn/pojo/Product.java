/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author three
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
    @NamedQuery(name = "Product.findByName", query = "SELECT p FROM Product p WHERE p.name = :name"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByInStock", query = "SELECT p FROM Product p WHERE p.inStock = :inStock")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45, message = "{product.name.lengthErr}")
    @Column(name = "name")
    private String name;
    @Size(max = 18000, message = "{product.description.lengthErr}")
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private long price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_stock")
    private int inStock;
    @Basic(optional = false)
    @Column(name = "created_date")
    private Date createdDate;
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @ManyToOne
    private Brand brandId;
    @JoinColumn(name = "image_set_id", referencedColumnName = "id")
    @ManyToOne
    private ImageSet imageSetId;
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SubCategory subCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<OrderDetail> orderDetailCollection;

    
    @NotNull(message = "{input.nullErr}")
    @Transient
    private String brandid;
    @NotNull(message = "{input.nullErr}")
    @Transient
    private String imgsetid;
    @NotNull(message = "{input.nullErr}")
    @Transient
    private String subcatid;
    
    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, long price, int inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }

    public ImageSet getImageSetId() {
        return imageSetId;
    }

    public void setImageSetId(ImageSet imageSetId) {
        this.imageSetId = imageSetId;
    }

    public SubCategory getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(SubCategory subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ldn.pojo.Product[ id=" + id + " ]";
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the brandid
     */
    public String getBrandid() {
        return brandid;
    }

    /**
     * @param brandid the brandid to set
     */
    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    /**
     * @return the imgsetid
     */
    public String getImgsetid() {
        return imgsetid;
    }

    /**
     * @param imgsetid the imgsetid to set
     */
    public void setImgsetid(String imgsetid) {
        this.imgsetid = imgsetid;
    }

    /**
     * @return the subcatid
     */
    public String getSubcatid() {
        return subcatid;
    }

    /**
     * @param subcatid the subcatid to set
     */
    public void setSubcatid(String subcatid) {
        this.subcatid = subcatid;
    }
    
}
