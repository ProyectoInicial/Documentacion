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
 *         &lt;element name="CatalogAccount" type="{http://www.loam.com/framework/core/catalog/jaxb/ws/CatalogManager}CatalogAccount"/>
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
    "catalogAccount"
})
@XmlRootElement(name = "MaintainCatalogAccountIn")
public class MaintainCatalogAccountIn {

    @XmlElement(name = "CatalogAccount", required = true)
    protected CatalogAccount catalogAccount;

    /**
     * Gets the value of the catalogAccount property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogAccount }
     *     
     */
    public CatalogAccount getCatalogAccount() {
        return catalogAccount;
    }

    /**
     * Sets the value of the catalogAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogAccount }
     *     
     */
    public void setCatalogAccount(CatalogAccount value) {
        this.catalogAccount = value;
    }

}
