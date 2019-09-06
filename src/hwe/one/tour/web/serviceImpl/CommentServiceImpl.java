package hwe.one.tour.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hwe.one.tour.dao.CommentDao;
import hwe.one.tour.po.Comment;
import hwe.one.tour.web.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> selectCommentBySceneryId(int scId) {
		// TODO Auto-generated method stub
		
		List<Comment> comments = commentDao.selectCommentBySceneryId(scId);
		
		return comments;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
		commentDao.addComment(comment);
		
	}

}
