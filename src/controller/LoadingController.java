package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class LoadingController {

    @FXML
    private ProgressBar loadingProgress;

    public void initialize() {
        //progress();
    }

    public void progress()
    {
        for (int i = 0; i < 10; i++) {
            loadingProgress.setProgress(0.3);
            System.out.println("ali");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ProgressBar getLoadingProgress() {
        return loadingProgress;
    }
}
