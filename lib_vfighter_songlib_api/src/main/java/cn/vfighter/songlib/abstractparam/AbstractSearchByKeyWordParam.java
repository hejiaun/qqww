package cn.vfighter.songlib.abstractparam;

/**
 * 关键字搜索
 * 
 * @author hanghuideng
 *
 */
public abstract class AbstractSearchByKeyWordParam {
	/** 关键字 */
	private String keyWord;
	/** 当前页下标 */
	private int pageIndex;
	/** 单页实体数量 */
	private int pageLength;

	/**
	 * 获取关键字
	 * 
	 * @return
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * 设置关键字
	 * 
	 * @param keyWord
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * 获取当前下标
	 * 
	 * @return
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 设置当前下标
	 * 
	 * @param pageIndex
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 获取单页实体数量
	 * 
	 * @return
	 */
	public int getPageLength() {
		return pageLength;
	}

	/**
	 * 设置单页实体数量
	 * 
	 * @param pageLength
	 */
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

}
