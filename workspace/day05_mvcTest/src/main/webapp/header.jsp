<header>
   <nav class="navbar">
      <ul>
         <li><a href="${pageContext.request.contextPath}/index.jsp"></a>home</li>
         <c:if test = "${empty sessionScope.member}">
            <li><a href="${pageContext.request.contextPath}/member/login.me"></a>login</li>
            <li><a href="${pageContext.request.contextPath}/member/join.me"></a>join</li>
         </c:if>
         <c:if test="${not empty sessionScope.member}">
            <li><a href="${pageContext.request.contextPath}/member/logout.me">logout</a></li>
         </c:if>
      </ul>
   </nav>
      <div class="welcome-message">
      <c:choose>
         <c:when test="${not empty sessionScope.member}">
            ${sessionScope.member.memberName}님, 환영합니다!
         </c:when>
         <c:otherwise>
            로그인해주세요
         </c:otherwise>
      </c:choose>
   </div>
</header>
