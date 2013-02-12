<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<div id="footerCopyright">
	<p>&copy;<fmt:formatDate value="${date}" pattern="yyyy" /> Systems In Motion </p>
</div>