//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.19 at 04:25:48 PM CST 
//


package com.loam.framework.core.catalog.jaxb.ws.catalogmanager;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CatalogMenu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogMenu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdMenu">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="19"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DescMenu">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="UrlMenu" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdMenuPadre" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;totalDigits value="19"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DescMenuPadre" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TypeMenu">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="20"/>
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
 *         &lt;element name="StartDt">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LastUpdateDt">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LastUpdateUser">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
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
@XmlType(name = "CatalogMenu", propOrder = {
    "idMenu",
    "descMenu",
    "urlMenu",
    "idMenuPadre",
    "descMenuPadre",
    "typeMenu",
    "statusFlag",
    "startDt",
    "lastUpdateDt",
    "lastUpdateUser"
})
public class CatalogMenu {

    @XmlElement(name = "IdMenu", required = true)
    protected BigInteger idMenu;
    @XmlElement(name = "DescMenu", required = true)
    protected String descMenu;
    @XmlElement(name = "UrlMenu")
    protected String urlMenu;
    @XmlElement(name = "IdMenuPadre")
    protected BigInteger idMenuPadre;
    @XmlElement(name = "DescMenuPadre")
    protected String descMenuPadre;
    @XmlElement(name = "TypeMenu", required = true)
    protected String typeMenu;
    @XmlElement(name = "StatusFlag")
    protected int statusFlag;
    @XmlElement(name = "StartDt", required = true)
    protected XMLGregorianCalendar startDt;
    @XmlElement(name = "LastUpdateDt", required = true)
    protected XMLGregorianCalendar lastUpdateDt;
    @XmlElement(name = "LastUpdateUser", required = true)
    protected String lastUpdateUser;

    /**
     * Gets the value of the idMenu property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdMenu() {
        return idMenu;
    }

    /**
     * Sets the value of the idMenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdMenu(BigInteger value) {
        this.idMenu = value;
    }

    /**
     * Gets the value of the descMenu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescMenu() {
        return descMenu;
    }

    /**
     * Sets the value of the descMenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescMenu(String value) {
        this.descMenu = value;
    }

    /**
     * Gets the value of the urlMenu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlMenu() {
        return urlMenu;
    }

    /**
     * Sets the value of the urlMenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlMenu(String value) {
        this.urlMenu = value;
    }

    /**
     * Gets the value of the idMenuPadre property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdMenuPadre() {
        return idMenuPadre;
    }

    /**
     * Sets the value of the idMenuPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdMenuPadre(BigInteger value) {
        this.idMenuPadre = value;
    }

    /**
     * Gets the value of the descMenuPadre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescMenuPadre() {
        return descMenuPadre;
    }

    /**
     * Sets the value of the descMenuPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescMenuPadre(String value) {
        this.descMenuPadre = value;
    }

    /**
     * Gets the value of the typeMenu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeMenu() {
        return typeMenu;
    }

    /**
     * Sets the value of the typeMenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeMenu(String value) {
        this.typeMenu = value;
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
     * Gets the value of the startDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDt() {
        return startDt;
    }

    /**
     * Sets the value of the startDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDt(XMLGregorianCalendar value) {
        this.startDt = value;
    }

    /**
     * Gets the value of the lastUpdateDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateDt() {
        return lastUpdateDt;
    }

    /**
     * Sets the value of the lastUpdateDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateDt(XMLGregorianCalendar value) {
        this.lastUpdateDt = value;
    }

    /**
     * Gets the value of the lastUpdateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * Sets the value of the lastUpdateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdateUser(String value) {
        this.lastUpdateUser = value;
    }

}
