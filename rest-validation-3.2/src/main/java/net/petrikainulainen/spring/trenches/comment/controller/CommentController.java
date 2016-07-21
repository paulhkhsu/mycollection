package net.petrikainulainen.spring.trenches.comment.controller;

import net.petrikainulainen.spring.trenches.comment.dto.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Petri Kainulainen
 */
@Controller
public class CommentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    //http://localhost:8090/rest-validation/api/comment GET
    //http://localhost:8090/rest-validation/api/comment.json GET
    //http://localhost:8090/rest-validation/api/comment GET Accept:Application/xml
    
        @RequestMapping(value = "/api/comment", method = RequestMethod.GET)
    @ResponseBody
    public CommentDTO get() {
    	CommentDTO comment = new CommentDTO();
    	comment.setText("dfsfs");
        return comment;
    }
    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    @ResponseBody
    public CommentDTO add(@Valid @RequestBody CommentDTO comment) {
        LOGGER.debug("Received comment: {}", comment);
        return comment;
    }
}
