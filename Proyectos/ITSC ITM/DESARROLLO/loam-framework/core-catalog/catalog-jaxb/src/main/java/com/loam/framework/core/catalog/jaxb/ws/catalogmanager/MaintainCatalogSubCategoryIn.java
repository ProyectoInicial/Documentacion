//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.19 at 04:25:48 PM CST 
//


package com.loam.framework.core.catalog.jaxb.ws.catalogmanager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CatalogSubCategory" type="{http://www.loam.com/framework/core/catalog/jaxb/ws/CatalogManager}CatalogSubCategory"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "catalogSubCategory"
})
@XmlRootElement(name = "MaintainCatalogSubCategoryIn")
public class MaintainCatalogSubCategoryIn {

    @XmlElement(name = "CatalogSubCategory", required = true)
    protected CatalogSubCategory catalogSubCategory;

    /**
     * Gets the value of the catalogSubCategory property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogSubCategory }
     *     
     */
    public CatalogSubCategory getCatalogSubCategory() {
        return catalogSubCategory;
    }

    /**
     * Sets the value of the catalogSubCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogSubCategory }
     *     
     */
    public void setCatalogSubCategory(CatalogSubCategory value) {
        this.catalogSubCategory = value;
    }

}
