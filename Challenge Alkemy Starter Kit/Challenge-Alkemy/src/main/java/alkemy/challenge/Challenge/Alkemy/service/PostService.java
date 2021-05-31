package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.exception.NotPostFoundException;
import alkemy.challenge.Challenge.Alkemy.model.Post;
import alkemy.challenge.Challenge.Alkemy.repository.IPost;
import org.aspectj.weaver.ast.Not;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private IPost postData;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Post> listPosts() throws NotPostFoundException {
        //List all the post that exist
        return postData.findAll();

    }

    public PostService(IPost postData) {
        this.postData = postData;
    }

    @Override
    //define a field that will store whether a row has been deleted or not.
    // Then we've to override the delete command using the @SQLDelete annotation on that particular entity class.
    //If we want more control we can use the @FilterDef and @Filter annotations so we can determine if query results should include deleted data or not.
    public List<Post> findAllOrderByDateDesc(boolean isDeleted) throws NotPostFoundException {
        Session session = entityManager.unwrap(Session.class);
        org.hibernate.Filter filter = (org.hibernate.Filter) session.enableFilter("deletedPostFilter");
        filter.setParameter("isDeleted", isDeleted);
        //List all the post found and sort them in Descended type
        List<Post> listDesc = postData.findAll(Sort.by(Sort.Direction.DESC, "dateCreation"));
        session.disableFilter("deletedPostFilter");
        return listDesc;
    }

    @Override
    public List<Post> findPostByTitle(String title) throws NotPostFoundException {
        //create a ArrayList of type Post
        List<Post> postList = new ArrayList<>();
        //find the post by title from the repository method postData
        postData.findPostByTitle(title)
                .forEach(postList::add);
        //using a for to iterate the list and print it
        for (Post post : postList)
            System.out.println(postList);
        // return the list
        return postList;
    }
    //method to find the post by the category
    @Override
    public List<Post> findPostByCategory(String category) throws NotPostFoundException {
        //create a ArrayList of type Post
        List<Post> postList = new ArrayList<>();
        //find the post by category from the repository method postData
        postData.findPostByCategory(category)
                .forEach(postList::add);
        for (Post post : postList)
            //using a for to iterate the list and print it
            System.out.println(postList);
        return postList;
    }
    //method to find the post by id
    @Override
    public List<Post> findPostById(long id) throws NotPostFoundException {
        List<Post> postList = new ArrayList<>();
        postData.findPostById(id)
                .forEach(postList::add);
        for (Post post : postList)
            System.out.println(postList);
        return postList;
    }
    //method to save the post
    @Override
    public List<Post> savePost(Post post) throws NotPostFoundException {
        //create a list of type post and call the find by id tho get the post
        List<Post> postList = postData.findPostById(post.getId());
        //save the post
        postData.save(post);
        return postList;
    }
    // @Override
    //public List<Post> updatePost(Long id) {
    //  List<Post>postList = postData.updatePost(post.getId());
    //  return postList;
    // }
}
