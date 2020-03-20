package chatMatching.vo;

public class ReviewVo {
	String reviewCode;
	String matchingCode;
	String category;
	String reviewerCode;
	String targetCode;
	int star;
	String contents;
	

	public ReviewVo() {}
	
	public ReviewVo(String category, String matchingCode, String reviewerCode, String targetCode, int star, String contents) {
		super();
		this.category = category;
		this.matchingCode = matchingCode;
		this.reviewerCode = reviewerCode;
		this.targetCode = targetCode;
		this.star = star;
		this.contents = contents;
	}
	


	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	
	public String getMatchingCode() {
		return matchingCode;
	}

	public void setMatchingCode(String matchingCode) {
		this.matchingCode = matchingCode;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getReviewerCode() {
		return reviewerCode;
	}
	public void setReviewerCode(String reviewerCode) {
		this.reviewerCode = reviewerCode;
	}
	public String getTargetCode() {
		return targetCode;
	}
	public void setTargetCode(String targetCode) {
		this.targetCode = targetCode;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	
}
