package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.NotPostFoundException;
import alkemy.challenge.Challenge.Alkemy.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    List<Post> listPosts() throws NotPostFoundException;

    List<Post> findAllOrderByDateDesc(boolean isDeleted) throws NotPostFoundException;

    List<Post> findPostByTitle(String title) throws NotPostFoundException;

    List<Post> findPostByCategory(String category) throws NotPostFoundException;

    List<Post> findPostById(long id) throws NotPostFoundException;

    List<Post> savePost(Post post) throws NotPostFoundException;
    //List<Post>updatePost(Long id);

}
