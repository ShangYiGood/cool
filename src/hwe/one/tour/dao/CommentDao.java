package hwe.one.tour.dao;

import java.util.List;

import hwe.one.tour.po.Comment;

public interface CommentDao {
	
	//通过景点查询评论
	public List<Comment> selectCommentBySceneryId(int scId);
	
	//添加评论
	public void addComment(Comment comment);
	
}
