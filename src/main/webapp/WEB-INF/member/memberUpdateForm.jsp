<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./../common/user.jsp" %>

<%
	String[] styleArr = {"현대적인", "예술적인","감각적인","실용적인","에너지넘치는","감성적인"};
%>

<style type="text/css">
	.err{
		font-size: 9pt;
		color:orange;
		font-weight: bold;
	}
	
	table .w20 {
		display: inline-block;
		width: 20%;
	}
</style>

<script type = "text/javascript">
function writeSave(){
	
	if($('input[name="password"]').val() != $('input[name="repassword"]').val()){
		alert("비밀번호 확인이 다릅니다.");
		$('input[name="repassword"]').focus();
		return false;
	}
}
</script>

<div class="container">
	<h3 align=center style="padding:20px 0">마이페이지 수정</h3>
	<form:form commandName="member" method="post" action="update.me" onSubmit = "return writeSave()">
	<input type="hidden" name="num" value="${member.num}">
		<table border=1 frame="void" class="table table-secondary">
			<tr>
				<th width="25%" class="table-primary"><label for="id">아이디</label></th>
				<td>
					<input class="form-control w20" type="text" name="id" value="${member.id}" readonly>
					변경불가
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="password">비밀번호*</label></th>
				<td>
					<input type="password" name="password" class="form-control w20" value="${ member.password }">
					<form:errors cssClass="err" path="password"/>
				</td>
			</tr>	
			<tr>
				<th class="table-primary"><label for="repassword">비밀번호 확인*</label></th>
				<td>
					<input type="password" name="repassword" class="form-control w20">
					<form:errors cssClass="err" path="repassword"/>
				</td>
			</tr>	
			<tr>
				<th class="table-primary"><label for="name">이름</label></th>
				<td>
					<input class="form-control w20" type="text" name="name" value="${member.name}" readonly>
					변경불가
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="gender">성별</label></th>
				<td>
					<input class="form-control w20" type="text" name="gender" value="${member.gender}" readonly>
					변경불가
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="age">나이</label></th>
				<td>
					<input class="form-control w20" type="text" name="age" value="${member.age}" readonly>
					변경불가
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="year">생일*</label></th>
				<td>
					<select name="year" class="form-control w20">
						<c:forEach begin="1910" end="2021" var="y">
							<option value="${ y }" <c:if test="${y==member.year}"> selected</c:if>>${y}
						</c:forEach>
					</select>년 
					
					<select name="month" class="form-control w20">
						<c:forEach begin="1" end="12" var="m">
							<option value="${ m }" <c:if test="${m==member.month}"> selected</c:if>  > ${ m }
						</c:forEach>
					</select>월 
					
					<select name="day" class="form-control w20">
						<c:forEach begin="1" end="31" var="d">
							<option value="${ d }" <c:if test="${d==member.day}"> selected</c:if>> ${ d }
						</c:forEach>
					</select>일
					<form:errors cssClass="err" path="year"/>
					
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="email">이메일*</label></th>
				<td>
					<input type="text" name="email" class="form-control" value="${ member.email }">
					<form:errors cssClass="err" path="email"/>
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="hp1">핸드폰*</label></th>
				<td>
					<input type="text" name="hp1" class="form-control w20" value="${ member.hp1 }">
					-					
					<input type="text" name="hp2" class="form-control w20" value="${ member.hp2 }">
					-
					<input type="text" name="hp3" class="form-control w20" value="${ member.hp3 }"><br>
					<form:errors cssClass="err" path="hp1"/><br>
					<form:errors cssClass="err" path="hp2"/><br>
					<form:errors cssClass="err" path="hp3"/>
				</td>
			</tr>
			<tr>
				<th class="table-primary">
					<label for="add1">주소*</label><br>
				</th>
				<td>
					<input type="text" name="add1" class="form-control" value="${ member.add1 }">
					<form:errors cssClass="err" path="add1"/><br>
					<input type="text" name="add2" class="form-control" value="${ member.add2 }"><br>
					<form:errors cssClass="err" path="add2"/>
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="style">취향*</label></th>
				<td>
					<c:forEach var="style" items="<%=styleArr%>">
						<input type="radio" name="style" value="${ style }" 
						<c:if test="${ fn:contains(member.style,style) }">checked</c:if>
						> ${ style }
					</c:forEach>
					<form:errors cssClass="err" path="style"/>
				</td>
			</tr>
			<tr>
				<th class="table-primary"><label for="cat">관심 카테고리*</label></th>
				<td>
					<select name="cat" class="form-control w20">
						<option value="">선택
					<c:forEach var="cate" items="${list }">
						<option value="${cate.kind}" <c:if test="${member.cat == cate.kind}">selected</c:if> >${cate.kind}(${cate.code })</option>  
					</c:forEach>
					</select>
					<form:errors cssClass="err" path="cat"/>
				</td>
			</tr>
			
			<tr class="btnRow">
				<td colspan=2 align="center" class="table-primary">
					<input type="submit" value="수정하기" class="btn btn-secondary">
				</td>
			</tr>
		</table>
	</form:form>
</div>

<!-- footer -->
<%@ include file="../common/footer.jsp" %>
