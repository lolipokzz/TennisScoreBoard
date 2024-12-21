package servlet;

import dao.MatchesDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet ("/matches")
public class MatchesServlet extends HttpServlet {
    private static final MatchesDao matchesDao = new MatchesDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameFromParameters = req.getParameter("filter_by_player_name");
        if (nameFromParameters == null) {
            List<Match> matches = matchesDao.getAllMatches();
            int numberOfPage;
            int numberOfPages = (int) Math.ceil(matches.size() /(double) 5);
            if(req.getParameter("page") != null ) {
                numberOfPage=Integer.parseInt(req.getParameter("page"));
                if (numberOfPage == 0) {
                    numberOfPage = 1;
                }
            }else {
                numberOfPage = 1;
            }
            int startIndex = numberOfPage*5-5;
            int endIndex = numberOfPage*5;
            List<Match> pageMatches = new ArrayList<>();
            for (int i = startIndex; i < endIndex; i++) {
                if(i > matches.size()-1) {
                    break;
                }
                pageMatches.add(matches.get(i));
            }
            req.setAttribute("numberOfPages", numberOfPages);
            req.setAttribute("pageMatches", pageMatches);
            req.getRequestDispatcher("matches.jsp").forward(req, resp);
        }else {
            String name  = nameFromParameters.replaceAll("%", " ");
            List<Match> matches = matchesDao.findMatchesByPlayersName(name);
            int numberOfPage;
            int numberOfPages = (int) Math.ceil(matches.size() /(double) 5);
            if(req.getParameter("page") != null ) {
                numberOfPage=Integer.parseInt(req.getParameter("page"));
                if (numberOfPage == 0) {
                    numberOfPage = 1;
                }
            }else {
                numberOfPage = 1;
            }
            int startIndex = numberOfPage*5-5;
            int endIndex = numberOfPage*5;
            List<Match> pageMatches = new ArrayList<>();
            for (int i = startIndex; i < endIndex; i++) {
                if(i > matches.size()-1) {
                    break;
                }
                pageMatches.add(matches.get(i));
            }
            req.setAttribute("numberOfPages", numberOfPages);
            req.setAttribute("pageMatches", pageMatches);
            req.getRequestDispatcher("matches.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter("playerName");
        resp.sendRedirect("/TennisScoreBoard_war_exploded/matches?filter_by_player_name=" + playerName);

    }
}
