package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.exception.NotPostFoundException;
import alkemy.challenge.Challenge.Alkemy.model.Post;
import alkemy.challenge.Challenge.Alkemy.repository.IPost;
import alkemy.challenge.Challenge.Alkemy.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IPost postData;


    @PostMapping("/posts")
    public List<Post> getPost(boolean isDeleted) throws NotPostFoundException {
        //order the post with the findAll method in descendent way
        return postService.findAllOrderByDateDesc(isDeleted);
    }

    @GetMapping({"/posts?title=TITULO", "/posts{title}"})
    public String searchPostByTitle(@RequestParam("title") Optional<String> title, Model model) throws NotPostFoundException {
        //Use an if to see if the title exist
        try {
            if (title.isPresent()) {
                //create the post list and asign the post service.Calling the find title method,with an optional parameter with type String.Calling the get method.
                List<Post> postList = postService.findPostByTitle(title.get());
                for (Post post : postList) {
                    model.addAttribute("posts", post);
                    throw new NotPostFoundException("The post wasnt found!");
                }

                return "posts";
            }
        } catch (NotPostFoundException e) {
            e.getStackTrace();
        }
        return "posts";
    }

    //if the category field is present,create a postlist and asign the method findPostByCategory to get the field
    @GetMapping({"/posts?category=CATEGORY", "/posts{category}"})
    public String searchPostByCategory(@RequestParam("category") Optional<String> category, Model model) throws NotPostFoundException {
        if (category.isPresent()) {
            List<Post> postList = postService.findPostByCategory(category.get());
            for (Post post : postList) {
                model.addAttribute("posts", post);
            }
            return "Category Posts";
        } else {
            return "Category Posts";
        }
    }

    //Get a single post by id
    @RequestMapping(value = "/getPosts{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") Long id, Model model) {
        List<Post> postList = postData.findPostById(id);
        //add the postList to the model
        model.addAttribute("posts", postList);
        return "posts";

    }

    @RequestMapping(value = "/savePost/{id}")
    public String savePosting(@ModelAttribute("posts") Post post) throws NotPostFoundException {
        postService.savePost(post);
        return "saved";
    }

    @RequestMapping(path = "/postsUpdate/{id}", method = RequestMethod.PATCH)
    public Post updatePost(Post post) throws Exception {
        //check if there is an real id post,if it doesnt find anything gets an error
        if (postData.findPostById(post.getId()) == null) {
            System.out.println("Searching for post...");
            throw new Exception("The post doesnt exist");
        } else {
            //the post exist so will be found with the method findById
            postData.findPostById(post.getId());
        }
        //save the post
        return postData.save(post);
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.POST)
    public String deletePost(@ModelAttribute Post post) throws NotPostFoundException {
        //try and catch to find the post
        try {
            List<Post> postList = postService.findPostById(post.getId());
            //delete the post found
            postData.delete(post);
            return "deletePost";
        } catch (NoSuchFieldError e) {
            e.getStackTrace();
        } catch (Exception ex) {
            ex.getCause();
        }
        return "cantFindDelete";

    }

    @GetMapping("/postsView")
    public String listPosts(Model model) throws NotPostFoundException {
        //create a list and call the listPosts method
        List<Post> postList = postService.listPosts();
        // add the model the post object
        model.addAttribute("posts", new Post());
        //return the posts
        return "posts";
    }
}





