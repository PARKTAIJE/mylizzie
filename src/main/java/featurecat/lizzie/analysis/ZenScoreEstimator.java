package featurecat.lizzie.analysis;

import featurecat.lizzie.rules.Board;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

import java.util.List;

public class ZenScoreEstimator extends GtpBasedScoreEstimator implements DetailedScoreEstimator {
    public ZenScoreEstimator(String commandLine) {
        super(commandLine);
    }

    @Override
    public ImmutablePair<String, Double> estimateScore() {
        DetailedScore detailedScore = estimateDetailedScore();
        double score = detailedScore.getScore();
        String color = "B";
        if (score < 0) {
            color = "W";
            score = -score;
        }

        return ImmutablePair.of(color, score);
    }

    @Override
    public String estimateScoreRaw() {
        ImmutablePair<String, Double> score = estimateScore();
        return score.getLeft() + "+" + score.getRight();
    }

    @Override
    public double[] estimateInfluences() {
        List<String> response = estimateInfluencesRaw();
        MutableIntList territories = GtpCommand.parseResponseIntTable(response);
        MutableDoubleList influences = territories.collectDouble(influence -> {
            if (-300 < influence && influence < 300) {
                influence = 0;
            } else if (influence > 800) {
                influence = 800;
            } else if (influence < -800) {
                influence = -800;
            }
            return influence / 800.0;
        }, new DoubleArrayList(Board.BOARD_SIZE * Board.BOARD_SIZE));

        double[] influencesAdjusted = new double[Board.BOARD_SIZE * Board.BOARD_SIZE];
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                influencesAdjusted[Board.getIndex(j, Board.BOARD_SIZE - 1 - i)] = influences.get(Board.getIndex(i, j));
            }
        }

        return influencesAdjusted;
    }

    @Override
    public List<String> estimateInfluencesRaw() {
        return gtpClient.sendCommand("territory_statistics territory");
    }

    @Override
    public String getEstimatorName() {
        return "Zen";
    }

    private MutableIntList getScoreStatistics() {
        List<String> response = gtpClient.sendCommand("score_statistics");
        return GtpCommand.parseResponseIntTable(response);
    }

    @Override
    public DetailedScore estimateDetailedScore() {
        MutableIntList scoreStatistics = getScoreStatistics();
        int blackTerritoryCount = scoreStatistics.get(6);
        int whiteTerritoryCount = scoreStatistics.get(7);
        int blackDeadCount = scoreStatistics.get(4);
        int whiteDeadCount = scoreStatistics.get(5);
        int blackPrisonerCount = scoreStatistics.get(2);
        int whitePrisonerCount = scoreStatistics.get(3);
        int dameCount = scoreStatistics.get(12);
        int blackAreaCount = scoreStatistics.get(8);
        int whiteAreaCount = scoreStatistics.get(9);

        double score = blackTerritoryCount + blackPrisonerCount - blackDeadCount
                - (whiteTerritoryCount + whitePrisonerCount - whiteDeadCount)
                - getKomi();

        return new DetailedScore(blackTerritoryCount, whiteTerritoryCount, blackDeadCount, whiteDeadCount, blackPrisonerCount, whitePrisonerCount, blackAreaCount, whiteAreaCount, dameCount, score);
    }
}
