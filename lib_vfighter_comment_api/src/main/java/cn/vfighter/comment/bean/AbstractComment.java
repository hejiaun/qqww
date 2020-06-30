package cn.vfighter.comment.bean;

import java.util.Date;

/**
 * 评论抽象类
 * 
 * @author hanghuideng
 *
 */
public abstract class AbstractComment {
	/** 主键 */
	private long id;
	/** 状态-1：被删除，1：正常 */
	private short state;
	/** 评论人id */
	private long commentPerson;
	/** 被评论人id */
	private long beCommentPerson;
	/** 评论内容 */
	private String commentContent;
	/** 评论日期 */
	private Date commentDate;

	/**
	 * 获取主键
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取评论人id
	 * 
	 * @return
	 */
	public long getCommentPerson() {
		return commentPerson;
	}

	/**
	 * 设置评论人id
	 * 
	 * @param commentPerson
	 */
	public void setCommentPerson(long commentPerson) {
		this.commentPerson = commentPerson;
	}

	/**
	 * 获取状态-1：被删除，1：正常
	 * 
	 * @return 状态-1：被删除，1：正常
	 */
	public short getState() {
		return state;
	}

	/**
	 * 设置状态-1：被删除，1：正常
	 * 
	 * @param state状态-1：被删除，1：正常
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * 获取被评论人id
	 * 
	 * @return
	 */
	public long getBeCommentPerson() {
		return beCommentPerson;
	}

	/**
	 * 设置被评论人id
	 * 
	 * @return
	 */
	public void setBeCommentPerson(long beCommentPerson) {
		this.beCommentPerson = beCommentPerson;
	}

	/**
	 * 获取评论内容
	 * 
	 * @return
	 */
	public String getCommentContent() {
		return commentContent;
	}

	/**
	 * 获取被评论内容
	 * 
	 * @param commentContent
	 */
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	/**
	 * 获取评论日期
	 * 
	 * @return
	 */
	public Date getCommentDate() {
		return commentDate;
	}

	/**
	 * 设置评论日期
	 * 
	 * @param commentDate
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

}
