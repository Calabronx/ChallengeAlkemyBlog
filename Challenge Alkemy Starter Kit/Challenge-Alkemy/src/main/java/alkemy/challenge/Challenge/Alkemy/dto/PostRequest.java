package alkemy.challenge.Challenge.Alkemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long id;
    private String dateCreation;
    private String title;
    private String image;
    private String category;
    private String content;
    private boolean deleted;
}