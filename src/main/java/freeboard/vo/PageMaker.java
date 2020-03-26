package freeboard.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	 private int totalCount; //게시물 총 갯수
	 private int startPage; // 현재 페이지의 시작번호
	 private int endPage; //현재 페이지의 끝번호
	 private boolean prev; //이전 페이지로 이동하는 링크의 존재 여부
	 private boolean next; //다음 페이지로 이동하는 링크의 존재 여부

	 private int displayPageNum = 10;

	 private PageReady pagerd;
	 
	 public void setPagerd(PageReady pagerd) {
	  this.pagerd = pagerd;
	 }

	 public void setTotalCount(int totalCount) {
	  this.totalCount = totalCount;
	  calcData();
	 }

	 public int getTotalCount() {
	  return totalCount;
	 }

	 public int getStartPage() {
	  return startPage;
	 }

	 public int getEndPage() {
	  return endPage;
	 }

	 public boolean isPrev() {
	  return prev;
	 }

	 public boolean isNext() {
	  return next;
	 }

	 public int getDisplayPageNum() {
	  return displayPageNum;
	 }

	 public PageReady getPagerd() {
	  return pagerd;
	 }
	 
	 private void calcData() {
	  endPage = (int) (Math.ceil(pagerd.getPage() / (double)displayPageNum) * displayPageNum);
	  startPage = (endPage - displayPageNum) + 1;
	  
	  int tempEndPage = (int) (Math.ceil(totalCount / (double)pagerd.getPerPageNum()));
	  if (endPage > tempEndPage)
	  {
	   endPage = tempEndPage;
	  }
	  prev = startPage == 1 ? false : true;
	  next = endPage * pagerd.getPerPageNum() >= totalCount ? false : true;
	 }
	
	
	 public String makeQuery(int page){
	  UriComponents uriComponents =
	    UriComponentsBuilder.newInstance()
	    .queryParam("page", page)
	    .queryParam("perPageNum", pagerd.getPerPageNum())
	    .build();
	    
	  return uriComponents.toUriString();
	 }
	 
	 
	 
	 public String makeSearch(int page) {
		 UriComponents uriComponents = UriComponentsBuilder.newInstance()
				 .queryParam("page", page)
				 .queryParam("perPageNum", pagerd.getPerPageNum())
				 .queryParam("searchType",((Search)pagerd).getSearchType())
				 .queryParam("keyword", encoding(((Search)pagerd).getKeyword()))
				 .build(); 
			 return uriComponents.toUriString();  
				 
	 }

	 private String encoding(String keyword) {
		  if(keyword == null || keyword.trim().length() == 0)
		  { return ""; }
		  
		  try {
		   return URLEncoder.encode(keyword, "UTF-8");
		  } catch(UnsupportedEncodingException e)
		  { return ""; }
		 } 
	
	 
	 
	 
	 
	
	
}
