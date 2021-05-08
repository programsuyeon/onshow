package utility;

public class Paging {
	//����¡ ���� ���������	
	private int totalCount = 0 ; //�� ���ڵ� �Ǽ�
	private int totalPage = 0 ; //��ü ������ ��
	private int pageNumber = 0 ; //������ ������ �ѹ�(ǥ�� ������ �������� 1���� totalPage�����̴�.)
	private int pageSize = 0 ; //�� �������� ������ �Ǽ�
	private int beginRow = 0 ; //���� �������� ���� ��
	private int endRow = 0 ; //���� �������� �� ��
	private int pageCount = 10 ; // �� ȭ�鿡 ������ ������ ��ũ �� (������ ����)=> ���ڵ� ���� �ƴ� 
	private int beginPage = 0 ; //����¡ ó�� ���� ������ ��ȣ
	private int endPage = 0 ; //����¡ ó�� �� ������ ��ȣ
	private int offset = 0 ;
	private int limit = 0 ;
	private String url = "" ; //���� ==>  http://localhost:8080/MyServlet/list.ab
	private String pagingHtml = ""; //�ϴ��� ���� ������ ��ũ
	//�˻��� ���� ���� �߰�
	private String whatColumn = "" ; //�˻� ���(�ۼ���, ������, ��ü �˻��� all) ���
	private String keyword = "" ; //�˻��� �ܾ� 

	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPagingHtml() {
		return pagingHtml;
	}

	public void setPagingHtml(String pagingHtml) {
		this.pagingHtml = pagingHtml;
	}

	public String getWhatColumn() {
		return whatColumn;
	}

	public void setWhatColumn(String whatColumn) {
		this.whatColumn = whatColumn;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Paging() {
		super();
	}

	public Paging(
					String _pageNumber, 
					String _pageSize,  
					int totalCount,
					String url
					) {
		System.out.println("_pageNumber: "+_pageNumber);
		if(  _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")  ){
			_pageNumber = "1";
		}
		this.pageNumber = Integer.parseInt( _pageNumber ); //1
		System.out.println("pageNumber: "+pageNumber);

		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "10" ; // �� �������� ������ ���ڵ� ����:5
		}		
		this.pageSize = Integer.parseInt( _pageSize ); //5
		
		this.limit = pageSize ; //limit:5

		this.totalCount = totalCount; //�޼������� �Է�

		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ; // 14/5=2.8 -> 3
		
		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ; //1, 6
		this.endRow =  this.pageNumber * this.pageSize ; //5, 10
		
		if( this.pageNumber > this.totalPage ){
			this.pageNumber = this.totalPage ;
		}//���������� totalPage�������� ���߱�: 3����
		
		this.offset = ( pageNumber - 1 ) * pageSize ; //�ǳʶپ�� �ϴ� ���ڵ� ���� 1:0, 2:5, 3:10
		
		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		} //���࿡ �Խù��� endRow������ ���� ��쿡 ���� �����ִ� �۾�

		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1; //pageCount:3�϶� 1, 2, 3..
		System.out.println("beginPage������Ȯ��: "+beginPage);
		this.endPage = this.beginPage + this.pageCount - 1 ; //3, 4, 5...
		
		//System.out.println("pageNumber:"+pageNumber+"/totalPage:"+totalPage);	
		
		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		} //������ ����ȣ totalPage:3 �� ���߱�
		
		//System.out.println("pageNumber2:"+pageNumber+"/totalPage2:"+totalPage);	
		
		this.url = url ;
		this.pagingHtml = getPagingHtml(url);//�ؿ� �޼���� ������
	}
	
	public Paging(
			String _pageNumber, 
			String _pageSize,  
			int totalCount,
			String url, 
			String whatColumn, 
			String keyword) {		

		if(  _pageNumber == null || _pageNumber.equals("null") || _pageNumber.equals("")  ){
			//System.out.println("_pageNumber:"+_pageNumber); // null
			_pageNumber = "1";
		}
		this.pageNumber = Integer.parseInt( _pageNumber ); 

		if( _pageSize == null || _pageSize.equals("null") || _pageSize.equals("") ){
			_pageSize = "10" ; // �� �������� ������ ���ڵ� ����
		}		
		this.pageSize = Integer.parseInt( _pageSize );
		
		this.limit = pageSize ; // �� �������� ������ ���ڵ� ����

		this.totalCount = totalCount ; 

		this.totalPage = (int)Math.ceil((double)this.totalCount / this.pageSize) ; // 5/2=2.5 -> 3
		
		this.beginRow = ( this.pageNumber - 1 )  * this.pageSize  + 1 ; //1, 3
		this.endRow =  this.pageNumber * this.pageSize ; //2, 4
		
		if( this.pageNumber > this.totalPage ){
			this.pageNumber = this.totalPage ;
		}
		
		this.offset = ( pageNumber - 1 ) * pageSize ; //�ǳʶپ�� �ϴ� ���ڵ� ���� 1:0, 2:2, 3:4
		
		if( this.endRow > this.totalCount ){
			this.endRow = this.totalCount  ;
		} //���࿡ �Խù��� endRow������ ���� ��쿡 ���� �����ִ� �۾�

		this.beginPage = ( this.pageNumber - 1 ) / this.pageCount * this.pageCount + 1  ;
		this.endPage = this.beginPage + this.pageCount - 1 ;
		
		System.out.println("pageNumber:"+pageNumber+"/totalPage:"+totalPage);	
		
		if( this.endPage > this.totalPage ){
			this.endPage = this.totalPage ;
		}
		
		System.out.println("pageNumber2:"+pageNumber+"/totalPage2:"+totalPage);	
		this.url = url ; //  /ex/list.tv
		this.whatColumn = whatColumn ;
		this.keyword = keyword ;
		System.out.println("whatColumn:"+whatColumn+"/keyword:"+keyword);
		
		this.pagingHtml = getPagingWithSearch(url);//�ؿ� �޼���� ������
	}//paging
	
	private String getPagingHtml( String url ){ //����¡ ���ڿ��� �����.
		String result = "" ;
		
		if (this.beginPage != 1) { 
			result += "<a class='page-link' href='" + url  
					+ "?pageNumber=" + ( 1 ) + "&pageSize=" + this.pageSize 
					+ "'>�� ó��</a>" ;
			result += "<a class='page-link' href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ "'>����</a>" ;
		}
			System.out.println("beginPage: "+beginPage);
			System.out.println("endPage: "+endPage);
		
		//���
		for (int i = this.beginPage; i <= this.endPage ; i++) {
			if ( i == this.pageNumber ) {
				result += "<li class='page-item active'><a class='page-link' href='#'>" + i + "</a></li>"	;
						
			} else {
				result += "<li class='page-item'><a class='page-link' href='" + url   
						+ "?pageNumber=" + i + "&pageSize=" + this.pageSize 
						+ "'>" + i + "</a></li>" ;
			}
		}
		
		if ( this.endPage != this.totalPage) { // ����
			
			result += "<li class='page-item'><a class='page-link' href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ "'>����</a></li>" ;
			
			result += "<li class='page-item'><a class='page-link' href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ "'>�� ��</a></li>" ;
		}
		
		System.out.println("result���: "+result);
		return result ;
	}	
	
	private String getPagingWithSearch( String url ){ //����¡ ���ڿ��� �����.
		String result = "" ;
		String added_param = "&whatColumn=" + whatColumn + "&keyword=" + keyword ; 
		
		if (this.beginPage != 1) { 
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + ( 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>�� ó��</a>&nbsp;" ;
			result += "&nbsp;<a href='" + url 
					+ "?pageNumber=" + (this.beginPage - 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>����</a>&nbsp;" ;
		}
		
		//���
		for (int i = this.beginPage; i <= this.endPage ; i++) {
			if ( i == this.pageNumber ) {
				result += "<li class='page-item active'><a class='page-link' href='#'>" + i + "</a></li>";
						
			} else {
				result += "<li class='page-item'><a class='page-link' href='" + url   
						+ "?pageNumber=" + i + "&pageSize=" + this.pageSize 
						+ added_param + "'>" + i + "</a></li>" ;
			}
		}
		
		if ( this.endPage != this.totalPage) { // ����
			
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.endPage + 1 ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>����</a>&nbsp;" ;
			
			result += "&nbsp;<a href='" + url  
					+ "?pageNumber=" + (this.totalPage ) + "&pageSize=" + this.pageSize 
					+ added_param + "'>�� ��</a>&nbsp;" ;
		}		
		System.out.println("result���: "+result);
		return result ;
	}	
	
}