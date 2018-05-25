//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.18 at 05:31:38 PM CST 
//


package com.loam.framework.core.customer.jaxb.ws.general.headers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EstadoRespuestaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EstadoRespuestaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOperacion">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="10"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Metodo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Clase">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MensajeDetallado">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NivelSegRequerido" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoRespuestaType", propOrder = {
    "idOperacion",
    "metodo",
    "clase",
    "mensajeDetallado",
    "nivelSegRequerido"
})
public class EstadoRespuestaType {

    @XmlElement(name = "IdOperacion", required = true)
    protected String idOperacion;
    @XmlElement(name = "Metodo", required = true)
    protected String metodo;
    @XmlElement(name = "Clase", required = true)
    protected String clase;
    @XmlElement(name = "MensajeDetallado", required = true)
    protected String mensajeDetallado;
    @XmlElement(name = "NivelSegRequerido")
    protected int nivelSegRequerido;

    /**
     * Gets the value of the idOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperacion() {
        return idOperacion;
    }

    /**
     * Sets the value of the idOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperacion(String value) {
        this.idOperacion = value;
    }

    /**
     * Gets the value of the metodo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetodo() {
        return metodo;
    }

    /**
     * Sets the value of the metodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetodo(String value) {
        this.metodo = value;
    }

    /**
     * Gets the value of the clase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClase() {
        return clase;
    }

    /**
     * Sets the value of the clase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClase(String value) {
        this.clase = value;
    }

    /**
     * Gets the value of the mensajeDetallado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeDetallado() {
        return mensajeDetallado;
    }

    /**
     * Sets the value of the mensajeDetallado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeDetallado(String value) {
        this.mensajeDetallado = value;
    }

    /**
     * Gets the value of the nivelSegRequerido property.
     * 
     */
    public int getNivelSegRequerido() {
        return nivelSegRequerido;
    }

    /**
     * Sets the value of the nivelSegRequerido property.
     * 
     */
    public void setNivelSegRequerido(int value) {
        this.nivelSegRequerido = value;
    }

}