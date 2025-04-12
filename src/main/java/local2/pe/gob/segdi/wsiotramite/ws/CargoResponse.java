
package local2.pe.gob.segdi.wsiotramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cargoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cargoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cargoRequest" type="{http://ws.wsiotramite.segdi.gob.pe/}CargoTramite"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cargoResponse", propOrder = {
    "cargoRequest"
})
public class CargoResponse {

    @XmlElement(required = true)
    protected CargoTramite cargoRequest;

    /**
     * Obtiene el valor de la propiedad cargoRequest.
     * 
     * @return
     *     possible object is
     *     {@link CargoTramite }
     *     
     */
    public CargoTramite getCargoRequest() {
        return cargoRequest;
    }

    /**
     * Define el valor de la propiedad cargoRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link CargoTramite }
     *     
     */
    public void setCargoRequest(CargoTramite value) {
        this.cargoRequest = value;
    }

}
