<%@ page import="java.util.List" %>
<%@ page import="model.Match" %>
<%@ page import="java.util.Optional" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>
<%  List<Match> pageMatches =(List<Match>) request.getAttribute("pageMatches");
    int currentPage;
    String name =(String) request.getParameter("filter_by_player_name");
    if (request.getParameter("page") == null){
        currentPage = 1;
    }else{
        currentPage = Integer.parseInt(request.getParameter("page"));
    }
    int numberOfPages = (int) request.getAttribute("numberOfPages");
%>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/TennisScoreBoard_war_exploded/">Home</a>
                <a class="nav-link" href="#">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
                <form action="matches" method="post" style="display: flex; align-items: center;">
                    <input class="input-filter" placeholder="Filter by name" type="text" name="playerName" pattern="[A-Za-z]\. [A-Za-z]+" required
                           title="Enter a name in English in the format n. surname ">
                    <button class="btn-filter" type="submit">
                        <img src="images/search.png" style="width: 45px; height: 45px;">
                    </button>
                </form>



            <div>
                <a href="/TennisScoreBoard_war_exploded/matches">
                    <button class="btn-filter">Reset Filter</button>
                </a>
            </div>
        </div>
        <%%>
        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
                <%if (0<=pageMatches.size()-1){%>
            <tr>
                <%Match match1 = pageMatches.get(0);%>
                <td><%out.println(match1.getPlayer1().getName());%></td>
                <td><%out.println(match1.getPlayer2().getName());%></td>
                <td><span class="winner-name-td"><%out.println(match1.getWinner().getName());%></span></td>
            </tr>
            <%}else{%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
            <%if (1<=pageMatches.size()-1){%>
            <tr>
                <%Match match2 = pageMatches.get(1);%>
                <td><%out.println(match2.getPlayer1().getName());%></td>
                <td><%out.println(match2.getPlayer2().getName());%></td>
                <td><span class="winner-name-td"><%out.println(match2.getWinner().getName());%></span></td>
            </tr>
            <%}else{%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
            <%if (2<=pageMatches.size()-1){%>
            <tr>
                <%Match match3 = pageMatches.get(2);%>
                <td><%out.println(match3.getPlayer1().getName());%></td>
                <td><%out.println(match3.getPlayer2().getName());%></td>
                <td><span class="winner-name-td"><%out.println(match3.getWinner().getName());%></span></td>
            </tr>
            <%}else{%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
            <%if (3<=pageMatches.size()-1){%>
            <tr>
                <%Match match4 = pageMatches.get(3);%>
                <td><%out.println(match4.getPlayer1().getName());%></td>
                <td><%out.println(match4.getPlayer2().getName());%></td>
                <td><span class="winner-name-td"><%out.println(match4.getWinner().getName());%></span></td>
            </tr>
            <%}else{%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
            <%if (4<=pageMatches.size()-1){%>
            <tr>
                <%Match match5 = pageMatches.get(4);%>
                <td><%out.println(match5.getPlayer1().getName());%></td>
                <td><%out.println(match5.getPlayer2().getName());%></td>
                <td><span class="winner-name-td"><%out.println(match5.getWinner().getName());%></span></td>
            </tr>
            <%}else{%>
            <tr>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <%}%>
        </table>
        <%if (name == null){%>
        <div class="pagination">
            <%if (currentPage-1<1){%>
            <a class="prev" href="/TennisScoreBoard_war_exploded/matches?page=<%=currentPage%>"> < </a>
            <%}else {%>
            <a class="prev" href="/TennisScoreBoard_war_exploded/matches?page=<%=currentPage -1%>"> < </a>
            <%}%>

            <a class="num-page current" href="#"><%out.println(currentPage);%></a>
            <% if (currentPage + 1 <= numberOfPages) { %>
            <a class="num-page" href="/TennisScoreBoard_war_exploded/matches?page=<%= currentPage + 1 %>">
                <%= currentPage + 1 %>
            </a>
            <% } %>

            <% if (currentPage + 2 <= numberOfPages) { %>
            <a class="num-page" href="/TennisScoreBoard_war_exploded/matches?page=<%= currentPage + 2 %>">
                <%= currentPage + 2 %>
            </a>
            <% } %>

            <% if (currentPage + 1 <= numberOfPages) { %>
            <a class="next" href="/TennisScoreBoard_war_exploded/matches?page=<%= currentPage + 1 %>"> > </a>
            <% } %>
        <%}else{%>
            <div class="pagination">
                <%if (currentPage-1<1){%>
                <a class="prev" href="/TennisScoreBoard_war_exploded/matches?page=<%=currentPage%>&filter_by_player_name=<%=name%>"> < </a>
                <%}else {%>
                <a class="prev" href="/TennisScoreBoard_war_exploded/matches?page=<%=currentPage -1%>&filter_by_player_name=<%=name%>"> < </a>
                <%}%>


                <a class="num-page current" href="#"><%out.println(currentPage);%></a>
                <% if (currentPage + 1 <= numberOfPages) { %>
                <a class="num-page" href="/TennisScoreBoard_war_exploded/matches?page=<%= currentPage + 1 %>&filter_by_player_name=<%=name%>">
                    <%= currentPage + 1 %>
                </a>
                <% } %>

                <% if (currentPage + 2 <= numberOfPages) { %>
                <a class="num-page" href="/TennisScoreBoard_war_exploded/matches?page=<%= currentPage + 2 %>&filter_by_player_name=<%=name%>">
                    <%= currentPage + 2 %>
                </a>
                <% } %>

                <% if (currentPage + 1 <= numberOfPages) { %>
                <a class="next" href="/TennisScoreBoard_war_exploded/matches?page=<%= currentPage + 1 %>&filter_by_player_name=<%=name%>"> > </a>
                <% } %>
        <%}%>

        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>
