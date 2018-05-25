//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.18 at 05:31:38 PM CST 
//


package com.loam.framework.core.customer.jaxb.ws.customerdetails;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="TotalRegs" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="9999999999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="UserContactMethod" type="{http://www.loam.com/framework/core/customer/jaxb/ws/CustomerDetails}UserContactMethod" maxOccurs="unbounded"/>
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
    "totalRegs",
    "userContactMethod"
})
@XmlRootElement(name = "ConsultUserContactMethodOut")
public class ConsultUserContactMethodOut {

    @XmlElement(name = "TotalRegs")
    protected Long totalRegs;
    @XmlElement(name = "UserContactMethod", required = true)
    protected List<UserContactMethod> userContactMethod;

    /**
     * Gets the value of the totalRegs property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalRegs() {
        return totalRegs;
    }

    /**
     * Sets the value of the totalRegs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalRegs(Long value) {
        this.totalRegs = value;
    }

    /**
     * Gets the value of the userContactMethod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userContactMethod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserContactMethod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserContactMethod }
     * 
     * 
     */
    public List<UserContactMethod> getUserContactMethod() {
        if (userContactMethod == null) {
            userContactMethod = new ArrayList<UserContactMethod>();
        }
        return this.userContactMethod;
    }

}