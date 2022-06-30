/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author three
 */
@Entity
@Table(name = "image_set")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImageSet.findAll", query = "SELECT i FROM ImageSet i"),
    @NamedQuery(name = "ImageSet.findById", query = "SELECT i FROM ImageSet i WHERE i.id = :id"),
    @NamedQuery(name = "ImageSet.findByDesc", query = "SELECT i FROM ImageSet i WHERE i.desc = :desc")})
public class ImageSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "desc")
    private String desc;
    @OneToMany(mappedBy = "imageSetId")
    private Collection<Product> productCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imageSetId", fetch = FetchType.EAGER)
    private Collection<ImagePath> imagePathCollection;

    public ImageSet() {
    }

    public ImageSet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<ImagePath> getImagePathCollection() {
        return imagePathCollection;
    }

    public void setImagePathCollection(Collection<ImagePath> imagePathCollection) {
        this.imagePathCollection = imagePathCollection;
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
        if (!(object instanceof ImageSet)) {
            return false;
        }
        ImageSet other = (ImageSet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ldn.pojo.ImageSet[ id=" + id + " ]";
    }
    
}
