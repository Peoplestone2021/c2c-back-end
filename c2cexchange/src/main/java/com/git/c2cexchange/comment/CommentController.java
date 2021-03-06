package com.git.c2cexchange.comment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.git.c2cexchange.lib.TextProcesser;

@RestController
public class CommentController {
	
	private CommentRepository repo;
	
	@Autowired
	public CommentController(CommentRepository repo) {
		this.repo=repo;
	}
	
	@GetMapping(value="/comments")
	public List<Comment> getComments() throws InterruptedException {
		return repo.findAll(Sort.by("commentId").descending());
	}
	
	@GetMapping(value = "/comments/paging")
	public Page<Comment> getCommentPaging(@RequestParam int page, @RequestParam int size){
		return repo.findAll(PageRequest.of(page,  size, Sort.by("commentId").descending()));
	}
	
	@PostMapping(value = "/comments/{itemId}")
	public Comment postComment(@RequestBody Comment comment, HttpServletResponse res) throws InterruptedException {
		
		if(TextProcesser.isEmpyText(comment.getCommentContent())) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		Comment commentItem = Comment.builder()
				.commentId(comment.getCommentId())
				.itemId(comment.getItemId())
				.commentContent(TextProcesser.getPlainText(comment.getCommentContent()))
				.createdTime(new Date().getTime())
				.build();
		
		Comment commentSaved = repo.save(commentItem);
		
		res.setStatus(HttpServletResponse.SC_CREATED);
		
		

		return commentSaved;
	}
	
//	@PutMapping(value="/comment/{itemId}")
//	public Comment modifyComment(@PathVariable long commentid, @RequestBody Comment comment, HttpServletResponse res) throws InterruptedException {
//		
//		Optional<Comment> commentItem = repo.findById(commentId);
//		if(commentItem.isEmpty()) {
//			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		
//		Comment commentToSave = commentItem.get();
//		
//		Comment commentSaved = repo.save(commentToSave);
//		return commentSaved;
//	}

}
