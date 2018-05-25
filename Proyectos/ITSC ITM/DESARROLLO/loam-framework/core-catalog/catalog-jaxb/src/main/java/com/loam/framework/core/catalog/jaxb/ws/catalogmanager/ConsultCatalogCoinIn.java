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
 *         &lt;element name="IdCoin" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="9999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DescCoin" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="StatusFlag">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdStart">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="99999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdEnd">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="99999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Order" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
    "idCoin",
    "descCoin",
    "statusFlag",
    "idStart",
    "idEnd",
    "order"
})
@XmlRootElement(name = "ConsultCatalogCoinIn")
public class ConsultCatalogCoinIn {

    @XmlElement(name = "IdCoin")
    protected Long idCoin;
    @XmlElement(name = "DescCoin")
    protected String descCoin;
    @XmlElement(name = "StatusFlag")
    protected int statusFlag;
    @XmlElement(name = "IdStart")
    protected int idStart;
    @XmlElement(name = "IdEnd")
    protected int idEnd;
    @XmlElement(name = "Order")
    protected String order;

    /**
     * Gets the value of the idCoin property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCoin() {
        return idCoin;
    }

    /**
     * Sets the value of the idCoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCoin(Long value) {
        this.idCoin = value;
    }

    /**
     * Gets the value of the descCoin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescCoin() {
        return descCoin;
    }

    /**
     * Sets the value of the descCoin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescCoin(String value) {
        this.descCoin = value;
    }

    /**
     * Gets the value of the statusFlag property.
     * 
     */
    public int getStatusFlag() {
        return statusFlag;
    }

    /**
     * Sets the value of the statusFlag property.
     * 
     */
    public void setStatusFlag(int value) {
        this.statusFlag = value;
    }

    /**
     * Gets the value of the idStart property.
     * 
     */
    public int getIdStart() {
        return idStart;
    }

    /**
     * Sets the value of the idStart property.
     * 
     */
    public void setIdStart(int value) {
        this.idStart = value;
    }

    /**
     * Gets the value of the idEnd property.
     * 
     */
    public int getIdEnd() {
        return idEnd;
    }

    /**
     * Sets the value of the idEnd property.
     * 
     */
    public void setIdEnd(int value) {
        this.idEnd = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrder(String value) {
        this.order = value;
    }

}
