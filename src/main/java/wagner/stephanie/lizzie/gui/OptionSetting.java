package wagner.stephanie.lizzie.gui;

import org.apache.logging.log4j.core.util.Integers;

import java.awt.*;

public class OptionSetting {
    private int variationLimit;
    private boolean a1OnTop;
    private Color boardColor;
    private boolean autoHideMoveNumber;
    private boolean autoHideAnalysisSuggession;
    private boolean analysisModeOn;
    private boolean mouseOverShowMove;
    private String leelazCommandLine;
    private boolean showMoveNumber;
    private int numberOfLastMovesShown;

    private int mainWindowPosX;
    private int mainWindowPosY;
    private int mainWindowWidth;
    private int mainWindowHeight;
    private int analysisWindowPosX;
    private int analysisWindowPosY;
    private int analysisWindowWidth;
    private int analysisWindowHeight;

    private String lastChooserLocation;

    public OptionSetting() {
        variationLimit = Integer.MAX_VALUE;
        a1OnTop = false;
        boardColor = new Color(0xf0, 0xd2, 0xa0);
        autoHideMoveNumber = true;
        autoHideAnalysisSuggession = true;
        analysisModeOn = true;
        mouseOverShowMove = false;
        leelazCommandLine = "-g -t2 -wnetwork";
        showMoveNumber = true;
        numberOfLastMovesShown = Integer.MAX_VALUE;

        mainWindowPosX = -1;
        mainWindowPosY = -1;
        // on 1080p windows screens, this is a good width/height
        mainWindowWidth = 657;
        mainWindowHeight = 687;
        analysisWindowPosX = -1;
        analysisWindowPosY = -1;
        analysisWindowWidth = -1;
        analysisWindowHeight = -1;

        lastChooserLocation = ".";
    }

    public int getVariationLimit() {
        return variationLimit;
    }

    public void setVariationLimit(int variationLimit) {
        this.variationLimit = variationLimit;
    }

    public boolean isA1OnTop() {
        return a1OnTop;
    }

    public void setA1OnTop(boolean a1OnTop) {
        this.a1OnTop = a1OnTop;
    }

    public Color getBoardColor() {
        return boardColor;
    }

    public void setBoardColor(Color boardColor) {
        this.boardColor = boardColor;
    }

    public boolean isAutoHideMoveNumber() {
        return autoHideMoveNumber;
    }

    public void setAutoHideMoveNumber(boolean autoHideMoveNumber) {
        this.autoHideMoveNumber = autoHideMoveNumber;
    }

    public boolean isAutoHideAnalysisSuggession() {
        return autoHideAnalysisSuggession;
    }

    public void setAutoHideAnalysisSuggession(boolean autoHideAnalysisSuggession) {
        this.autoHideAnalysisSuggession = autoHideAnalysisSuggession;
    }

    public boolean isAnalysisModeOn() {
        return analysisModeOn;
    }

    public void setAnalysisModeOn(boolean analysisModeOn) {
        this.analysisModeOn = analysisModeOn;
    }

    public boolean isMouseOverShowMove() {
        return mouseOverShowMove;
    }

    public void setMouseOverShowMove(boolean mouseOverShowMove) {
        this.mouseOverShowMove = mouseOverShowMove;
    }

    public String getLeelazCommandLine() {
        return leelazCommandLine;
    }

    public void setLeelazCommandLine(String leelazCommandLine) {
        this.leelazCommandLine = leelazCommandLine;
    }

    public boolean isShowMoveNumber() {
        return showMoveNumber;
    }

    public void setShowMoveNumber(boolean showMoveNumber) {
        this.showMoveNumber = showMoveNumber;
    }

    public int getNumberOfLastMovesShown() {
        return numberOfLastMovesShown;
    }

    public void setNumberOfLastMovesShown(int numberOfLastMovesShown) {
        this.numberOfLastMovesShown = numberOfLastMovesShown;
    }

    public int getMainWindowPosX() {
        return mainWindowPosX;
    }

    public void setMainWindowPosX(int mainWindowPosX) {
        this.mainWindowPosX = mainWindowPosX;
    }

    public int getMainWindowPosY() {
        return mainWindowPosY;
    }

    public void setMainWindowPosY(int mainWindowPosY) {
        this.mainWindowPosY = mainWindowPosY;
    }

    public int getMainWindowWidth() {
        return mainWindowWidth;
    }

    public void setMainWindowWidth(int mainWindowWidth) {
        this.mainWindowWidth = mainWindowWidth;
    }

    public int getMainWindowHeight() {
        return mainWindowHeight;
    }

    public void setMainWindowHeight(int mainWindowHeight) {
        this.mainWindowHeight = mainWindowHeight;
    }

    public int getAnalysisWindowPosX() {
        return analysisWindowPosX;
    }

    public void setAnalysisWindowPosX(int analysisWindowPosX) {
        this.analysisWindowPosX = analysisWindowPosX;
    }

    public int getAnalysisWindowPosY() {
        return analysisWindowPosY;
    }

    public void setAnalysisWindowPosY(int analysisWindowPosY) {
        this.analysisWindowPosY = analysisWindowPosY;
    }

    public int getAnalysisWindowWidth() {
        return analysisWindowWidth;
    }

    public void setAnalysisWindowWidth(int analysisWindowWidth) {
        this.analysisWindowWidth = analysisWindowWidth;
    }

    public int getAnalysisWindowHeight() {
        return analysisWindowHeight;
    }

    public void setAnalysisWindowHeight(int analysisWindowHeight) {
        this.analysisWindowHeight = analysisWindowHeight;
    }

    public String getLastChooserLocation() {
        return lastChooserLocation;
    }

    public void setLastChooserLocation(String lastChooserLocation) {
        this.lastChooserLocation = lastChooserLocation;
    }
}
