//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.18 at 05:34:47 PM CST 
//


package com.loam.framework.core.security.jaxb.ws.corporatesecurity;

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
 *         &lt;element name="IdStatusToken">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="9999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descIdStatusToken">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="255"/>
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
    "idStatusToken",
    "descIdStatusToken"
})
@XmlRootElement(name = "SecurityTokenOut")
public class SecurityTokenOut {

    @XmlElement(name = "IdStatusToken")
    protected long idStatusToken;
    @XmlElement(required = true)
    protected String descIdStatusToken;

    /**
     * Gets the value of the idStatusToken property.
     * 
     */
    public long getIdStatusToken() {
        return idStatusToken;
    }

    /**
     * Sets the value of the idStatusToken property.
     * 
     */
    public void setIdStatusToken(long value) {
        this.idStatusToken = value;
    }

    /**
     * Gets the value of the descIdStatusToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescIdStatusToken() {
        return descIdStatusToken;
    }

    /**
     * Sets the value of the descIdStatusToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescIdStatusToken(String value) {
        this.descIdStatusToken = value;
    }

}