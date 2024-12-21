<%@ page import="model.MatchScore" %>
<%@ page import="java.util.Optional" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <%
        MatchScore matchScore = (MatchScore) request.getAttribute("matchScore");
    %>
    <script src="js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="brand">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/TennisScoreBoard_war_exploded/">Home</a>
                <a class="nav-link" href="/TennisScoreBoard_war_exploded/matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text"><%out.println(matchScore.getPlayer1().getName());
                    if (matchScore.getFirstPlayerScore().getSet() == 2){
                    %><img src ="images/cup.png" width="35" height="35"/>
                        <%}%>
                    </td>
                    <td class="table-text"><% if (matchScore.getFirstPlayerScore().getSet() == 0){
                        out.println(0);
                    }else{
                        out.println(matchScore.getFirstPlayerScore().getSet());
                    }%></td>
                    <td class="table-text"><% if (matchScore.getFirstPlayerScore().getGames() == 0){
                        out.println(0);
                    }else{
                        out.println(matchScore.getFirstPlayerScore().getGames());
                    }%></td>
                    <td class="table-text"><% if (matchScore.getFirstPlayerScore().getPoints() == 0){
                        out.println(0);
                    } else if (matchScore.getFirstPlayerScore().getPoints() == 55) {
                        out.println("AD");
                    } else{
                        out.println(matchScore.getFirstPlayerScore().getPoints());
                    }%></td>
                    <td class="table-text">
                        <form action=""  method="post">
                            <button class="score-btn" name="score" value="0" <%if (matchScore.isMatchEnded()){%>disabled<%}%>>Score</button>

                        </form>

                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text"><%out.println(matchScore.getPlayer2().getName());
                        if (matchScore.getSecondPlayerScore().getSet() == 2){
                    %><img src ="images/cup.png" width="35" height="35"/>
                        <%}%></td>
                    <td class="table-text"><% if (matchScore.getSecondPlayerScore().getSet() == 0){
                        out.println(0);
                    }else{
                        out.println(matchScore.getSecondPlayerScore().getSet());
                    }%></td>
                    <td class="table-text"><% if (matchScore.getSecondPlayerScore().getGames() == 0){
                        out.println(0);
                    }else{
                        out.println(matchScore.getSecondPlayerScore().getGames());
                    }%></td>
                    <td class="table-text"><% if (matchScore.getSecondPlayerScore().getPoints() == 0){
                        out.println(0);
                    } else if (matchScore.getSecondPlayerScore().getPoints() == 55) {
                        out.println("AD");
                    } else{
                        out.println(matchScore.getSecondPlayerScore().getPoints());
                    }%></td>
                    <td class="table-text">
                        <form action=""  method="post">
                            <button class="score-btn" name="score" value="1" <%if (matchScore.isMatchEnded()){%>disabled<%}%>>Score</button>

                        </form>

                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>
