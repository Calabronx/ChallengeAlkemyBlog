package alkemy.challenge.Challenge.Alkemy.repository;

import alkemy.challenge.Challenge.Alkemy.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPost extends JpaRepository<Post,Long> {

   // @Query("FROM Post")
    //List<Post> findAllOrderByNameDesc(Sort sort);

   // @Query(value = "SELECT * FROM post where title like %postTitle%,nativeQuery = true")
    List<Post> findPostByTitle(String postTitle);

   // @Query(value = "SELECT * FROM Post where category like %category%,nativeQuery = true")
    List<Post> findPostByCategory(String category);

   // @Query(value = "SELECT * FROM Post where id like %id%,nativeQuery = true")
    List<Post> findPostById(Long id);

    //@Query(value = "SELECT * FROM Post where posts like %post%,nativeQuery = true")
    //List<Post> savePosting(Post post);

    //@Query(value = "SELECT * FROM Post where id like %post%,nativeQuery = true")
    //List<Post> updatePost(Long id);


}
