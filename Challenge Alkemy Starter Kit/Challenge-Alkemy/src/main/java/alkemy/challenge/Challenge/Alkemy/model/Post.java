package alkemy.challenge.Challenge.Alkemy.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Builder
@Data
@Entity
@Table(name = "post")
//SQL delete implementation
@SQLDelete(sql = "UPDATE post SET deleted WHERE id =?")
@FilterDef(name = "deletedPostFilter", parameters = @ParamDef(name = "isDeleted", type =
        "boolean"))
@Filter(name = "deletedPostFilter", condition = "deleted = :isDeleted")
public class Post implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;

    @Column
    private String dateCreation;

    @Column
    private String title;

    @Column
    private String image;

    @Column
    private String category;

    @Column
    private String content;
    //field to see if the post is deleted
   // @Column
   // private boolean deleted = Boolean.FALSE;


    public Post() {
    }

    public Post(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Post(Long id, String dateCreation, String title, String image, String category, String content) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.title = title;
        this.image = image;
        this.category = category;
        this.content = content;
    }
    //setter to obtain the creation date and invoque DateTimeFormatter class
    public void setDateCreation(String dateCreation) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(getDateCreation());
        formatter = formatter.withLocale(Locale.US);
        this.dateCreation = dateCreation;
    }

}
