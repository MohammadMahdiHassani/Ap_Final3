package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class LoadingController {

    @FXML
    private ProgressBar loadingProgress;

    public ProgressBar getLoadingProgress() {
        return loadingProgress;
    }
}
