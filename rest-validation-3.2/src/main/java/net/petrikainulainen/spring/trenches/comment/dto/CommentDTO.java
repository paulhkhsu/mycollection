package net.petrikainulainen.spring.trenches.comment.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Petri Kainulainen
 */
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentDTO {

    @NotEmpty
    @Length(max = 10)
    private String text;

    public CommentDTO() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
