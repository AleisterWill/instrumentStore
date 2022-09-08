/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ldn.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author three
 */
@Entity
@Table(name = "image_path")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagePath.findAll", query = "SELECT i FROM ImagePath i"),
    @NamedQuery(name = "ImagePath.findById", query = "SELECT i FROM ImagePath i WHERE i.id = :id"),
    @NamedQuery(name = "ImagePath.findByPath", query = "SELECT i FROM ImagePath i WHERE i.path = :path")})
public class ImagePath implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 5000)
    @Column(name = "path")
    private String path;
    @JoinColumn(name = "image_set_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImageSet imageSetId;
    
    @Transient
    private MultipartFile file;

    public ImagePath() {
    }

    public ImagePath(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ImageSet getImageSetId() {
        return imageSetId;
    }

    public void setImageSetId(ImageSet imageSetId) {
        this.imageSetId = imageSetId;
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
        if (!(object instanceof ImagePath)) {
            return false;
        }
        ImagePath other = (ImagePath) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ldn.pojo.ImagePath[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
