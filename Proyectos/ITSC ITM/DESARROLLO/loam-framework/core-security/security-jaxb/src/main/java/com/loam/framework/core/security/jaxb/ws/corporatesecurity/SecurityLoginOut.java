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
 *         &lt;element name="IdStatusLogin">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="9999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="descIdStatusLogin">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="5"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IdCountLogin" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TokenUser" minOccurs="0">
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
    "idStatusLogin",
    "descIdStatusLogin",
    "idCountLogin",
    "tokenUser"
})
@XmlRootElement(name = "SecurityLoginOut")
public class SecurityLoginOut {

    @XmlElement(name = "IdStatusLogin")
    protected long idStatusLogin;
    @XmlElement(required = true)
    protected String descIdStatusLogin;
    @XmlElement(name = "IdCountLogin")
    protected Integer idCountLogin;
    @XmlElement(name = "TokenUser")
    protected String tokenUser;

    /**
     * Gets the value of the idStatusLogin property.
     * 
     */
    public long getIdStatusLogin() {
        return idStatusLogin;
    }

    /**
     * Sets the value of the idStatusLogin property.
     * 
     */
    public void setIdStatusLogin(long value) {
        this.idStatusLogin = value;
    }

    /**
     * Gets the value of the descIdStatusLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescIdStatusLogin() {
        return descIdStatusLogin;
    }

    /**
     * Sets the value of the descIdStatusLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescIdStatusLogin(String value) {
        this.descIdStatusLogin = value;
    }

    /**
     * Gets the value of the idCountLogin property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdCountLogin() {
        return idCountLogin;
    }

    /**
     * Sets the value of the idCountLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdCountLogin(Integer value) {
        this.idCountLogin = value;
    }

    /**
     * Gets the value of the tokenUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenUser() {
        return tokenUser;
    }

    /**
     * Sets the value of the tokenUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenUser(String value) {
        this.tokenUser = value;
    }

}
