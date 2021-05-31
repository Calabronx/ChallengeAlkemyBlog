package alkemy.challenge.Challenge.Alkemy;

import alkemy.challenge.Challenge.Alkemy.dto.PostRequest;
import alkemy.challenge.Challenge.Alkemy.model.Post;
import alkemy.challenge.Challenge.Alkemy.repository.IPost;
import alkemy.challenge.Challenge.Alkemy.service.PostService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private IPost postRepository;

    @Test
    public void testPostData() {
        Optional<Post.PostBuilder> post = Optional.of(Post.builder()
        .id(10L)
        .title("title")
        .content("content")
        .dateCreation("31/5/2021")
        .image("image")
        );
        Mockito.when(postRepository.findById(10L));

        PostRequest postRequest = (PostRequest) postService.findPostById(10L);

        verify(postRepository).findPostById(10L);
        assertAll(() -> {
            assertEquals("title", postRequest.getTitle());
            assertEquals("content",postRequest.getContent());
        });
    }
}
