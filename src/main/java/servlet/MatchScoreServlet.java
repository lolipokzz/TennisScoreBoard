package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MatchScore;
import model.PlayerScore;
import service.CurrentMatchesService;
import service.FinishedMatchesPersistenceService;
import service.MatchScoreCalculationService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private static final CurrentMatchesService currentMatchesService = new CurrentMatchesService();
    private static final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = currentMatchesService.findMatchScoreByUuid(uuid);
        req.setAttribute("matchScore",matchScore);
        req.getSession().setAttribute("firstPlayerName", currentMatchesService.findFirstPlayerNameByUuid(uuid));
        req.getSession().setAttribute("secondPlayerName", currentMatchesService.findSecondPlayerNameByUuid(uuid));
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        int score = Integer.parseInt(req.getParameter("score"));
        MatchScore matchScore = matchScoreCalculationService.calculateMatchScore(score, uuid);
        if (matchScore.isMatchEnded()){
            finishedMatchesPersistenceService.addFinishedMatch(matchScore);
            currentMatchesService.deleteMatchScoreByUuid(uuid);
        }
        req.setAttribute("matchScore",matchScore);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);

    }
}
