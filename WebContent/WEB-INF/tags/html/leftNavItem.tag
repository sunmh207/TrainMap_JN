<%@ tag pageEncoding="utf-8" %>
<%@ attribute name="category" type="com.jitong.common.domain.CategoryItem" %>
<tr height="43">
   <td onMouseOver="this.className='tt_menu_over'" onMouseOut="this.className='tt_menu_out'" 
	style="width: 163; height: 43;  padding-left:65px; padding-top:3px; font-size:13px;"><a href="<%=request.getContextPath()%>/<%=category.getKey()%>/index.do" style="color: #000000">${category.display}</a></td>
</tr>