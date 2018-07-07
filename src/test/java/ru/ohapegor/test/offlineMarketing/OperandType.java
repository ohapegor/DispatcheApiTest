
package ru.ohapegor.test.offlineMarketing;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperandType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OperandType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DATE"/&gt;
 *     &lt;enumeration value="NUMBER"/&gt;
 *     &lt;enumeration value="STRING"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "OperandType")
@XmlEnum
public enum OperandType {

    DATE,
    NUMBER,
    STRING;

    public String value() {
        return name();
    }

    public static OperandType fromValue(String v) {
        return valueOf(v);
    }

}
