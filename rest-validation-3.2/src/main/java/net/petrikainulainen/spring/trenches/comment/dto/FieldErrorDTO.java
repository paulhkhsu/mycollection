package net.petrikainulainen.spring.trenches.comment.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Petri Kainulainen
 */
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)

public class FieldErrorDTO {

    private String field;

    private String message;
    public FieldErrorDTO() {

    }
    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
