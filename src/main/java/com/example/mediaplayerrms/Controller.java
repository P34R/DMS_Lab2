package com.example.mediaplayerrms;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.*;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class Controller implements Initializable {
    @FXML
    public VBox vBoxMain;

    @FXML
    public MediaView mVideo;
    public MediaPlayer mpVideo;
    public Media mediaVideo;
    @FXML
    public HBox hBoxControls;

    @FXML
    public HBox hBoxVolume;

    @FXML
    public Button buttonPlay;

    @FXML
    public Label labelCurrentTime;

    @FXML
    public Label labelTotalTime;

    @FXML
    public Label labelFullscreen;

    @FXML
    public Label labelOpenFile;

    @FXML
    public Label labelPrev;

    @FXML
    public Label labelNext;

    @FXML
    public Label labelVolume;

    @FXML
    public Label labelURL;

    @FXML
    public Slider sliderVolume;

    @FXML
    public Slider sliderTime;

    public boolean endVideo=false;
    public boolean isPlaying=true;
    public boolean isMuted=true;

    public ImageView imgPlay;
    public ImageView imgPause;
    public ImageView imgRestart;
    public ImageView imgVolume;
    public ImageView imgFullscreen;
    public ImageView imgMute;
    public ImageView imgExit;
    public ImageView imgPrev;
    public ImageView imgNext;
    public ImageView imgFile;
    public ImageView imgURL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int image_size=25;



        mediaVideo = new Media(new File("src/resources/sok.mp4").toURI().toString());
        mpVideo = new MediaPlayer(mediaVideo);
        mVideo.setMediaPlayer(mpVideo);

        Image imagePlay = new Image(new File("src/resources/play.png").toURI().toString());
        imgPlay=new ImageView(imagePlay);
        imgPlay.setFitHeight(image_size);
        imgPlay.setFitWidth(image_size);

        Image imagePause = new Image(new File("src/resources/pause.png").toURI().toString());
        imgPause=new ImageView(imagePause);
        imgPause.setFitHeight(image_size);
        imgPause.setFitWidth(image_size);

        Image imageRestart = new Image(new File("src/resources/restart.png").toURI().toString());
        imgRestart=new ImageView(imageRestart);
        imgRestart.setFitHeight(image_size);
        imgRestart.setFitWidth(image_size);

        Image imageVolume = new Image(new File("src/resources/volume.png").toURI().toString());
        imgVolume=new ImageView(imageVolume);
        imgVolume.setFitHeight(image_size);
        imgVolume.setFitWidth(image_size);

        Image imageFullscreen = new Image(new File("src/resources/fullscreen.png").toURI().toString());
        imgFullscreen=new ImageView(imageFullscreen);
        imgFullscreen.setFitHeight(image_size);
        imgFullscreen.setFitWidth(image_size);

        Image imageMute = new Image(new File("src/resources/mute.png").toURI().toString());
        imgMute=new ImageView(imageMute);
        imgMute.setFitHeight(image_size);
        imgMute.setFitWidth(image_size);

        Image imageExit = new Image(new File("src/resources/exit.png").toURI().toString());
        imgExit=new ImageView(imageExit);
        imgExit.setFitHeight(image_size);
        imgExit.setFitWidth(image_size);

        Image imagePrev = new Image(new File("src/resources/prev.png").toURI().toString());
        imgPrev=new ImageView(imagePrev);
        imgPrev.setFitHeight(image_size);
        imgPrev.setFitWidth(image_size);

        Image imageNext = new Image(new File("src/resources/next.png").toURI().toString());
        imgNext=new ImageView(imageNext);
        imgNext.setFitHeight(image_size);
        imgNext.setFitWidth(image_size);

        Image imageFile = new Image(new File("src/resources/file.png").toURI().toString());
        imgFile=new ImageView(imageFile);
        imgFile.setFitHeight(image_size);
        imgFile.setFitWidth(image_size);

        Image imageURL = new Image(new File("src/resources/url.png").toURI().toString());
        imgURL=new ImageView(imageURL);
        imgURL.setFitHeight(image_size);
        imgURL.setFitWidth(image_size);

        buttonPlay.setGraphic(imgPause);
        labelVolume.setGraphic(imgMute);
        labelFullscreen.setGraphic(imgFullscreen);
        labelPrev.setGraphic(imgPrev);
        labelNext.setGraphic(imgNext);
        mpVideo.play();
        labelOpenFile.setGraphic(imgFile);

        labelURL.setGraphic(imgURL);

        labelURL.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mpVideo.stop();
                Stage newWindow = new Stage();
                newWindow.setTitle("Enter URL");
//Create view in Java
                TextField textField = new TextField("");
                Button button = new Button("OK");
                button.setOnAction(event -> {
                    if (!Objects.equals(textField.getText(), "")){
                        //    mediaVideo = new Media(new File("src/resources/asd.mp3").toURI().toString());
                        //    mpVideo = new MediaPlayer(mediaVideo);
                        //    mVideo.setMediaPlayer(mpVideo);
                        mpVideo.pause();
                        Media mediastream = new Media(textField.getText());
                        mpVideo = new MediaPlayer(mediastream);
                        mVideo.setMediaPlayer(mpVideo);
                        mpVideo.play();

                        mpVideo.setOnReady(new Runnable() {
                            @Override
                            public void run() {
                                labelTotalTime.setText("STREAM");
                                bindCurrentTimeLabel();
                                buttonPlay.setGraphic(imgPause);
                                sliderTime.setMax(mpVideo.getTotalDuration().toSeconds());
                            }
                        });
                        newWindow.close();
                    }
                    else{
                        mpVideo.play();
                        newWindow.close();
                    }
                });
                VBox container = new VBox( textField, button);

                container.setSpacing(15);
                container.setPadding(new Insets(25));
                container.setAlignment(Pos.CENTER);

                newWindow.setScene(new Scene(container));

                newWindow.show();
            }
        });

        labelOpenFile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser=new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                if (file!=null){
                    //    mediaVideo = new Media(new File("src/resources/asd.mp3").toURI().toString());
                    //    mpVideo = new MediaPlayer(mediaVideo);
                    //    mVideo.setMediaPlayer(mpVideo);
                    mpVideo.stop();
                    Media media = new Media(file.toURI().toString());
                    mpVideo = new MediaPlayer(media);
                    mVideo.setMediaPlayer(mpVideo);
                    mpVideo.play();

                    mpVideo.setOnReady(new Runnable() {
                        @Override
                        public void run() {
                            labelTotalTime.setText(getTime(mpVideo.getTotalDuration()));
                            bindCurrentTimeLabel();
                            buttonPlay.setGraphic(imgPause);
                            sliderTime.setMax(mpVideo.getTotalDuration().toSeconds());
                        }
                    });
                    mpVideo.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                        bindCurrentTimeLabel();
                        if (!sliderTime.isValueChanging()){
                            sliderTime.setValue(t1.toSeconds());
                        }
                        labelMatchEndVideo(labelCurrentTime.getText(),labelTotalTime.getText());
                    });
                }
            }
        });

        buttonPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button buttonPlay = (Button) actionEvent.getSource();
                bindCurrentTimeLabel();
                if (endVideo){
                    sliderTime.setValue(0);
                    endVideo=false;
                    isPlaying=false;
                }
                if (isPlaying){
                    buttonPlay.setGraphic(imgPlay);
                    mpVideo.pause();
                    isPlaying=false;
                }else{
                    buttonPlay.setGraphic(imgPause);
                    mpVideo.play();
                    isPlaying=true;
                }

            }
        });

        hBoxVolume.getChildren().remove(sliderVolume);

        mpVideo.volumeProperty().bindBidirectional(sliderVolume.valueProperty());

        bindCurrentTimeLabel();

        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mpVideo.setVolume(sliderVolume.getValue());
                if (mpVideo.getVolume()!=0.0){
                    labelVolume.setGraphic(imgVolume);
                    isMuted=false;
                }else{
                    labelVolume.setGraphic(imgMute);
                    isMuted=true;
                }
            }
        });

        labelVolume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isMuted){
                    labelVolume.setGraphic(imgVolume);
                    sliderVolume.setValue(0.2);
                    isMuted=false;
                }else{
                    labelVolume.setGraphic(imgMute);
                    sliderVolume.setValue(0);
                    isMuted=true;
                }
            }
        });

        labelVolume.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (hBoxVolume.lookup("#sliderVolume")==null){
                    hBoxVolume.getChildren().add(sliderVolume);
                    sliderVolume.setValue(mpVideo.getVolume());
                }
            }
        });

        hBoxVolume.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hBoxVolume.getChildren().remove(sliderVolume);
            }
        });

        vBoxMain.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene scene, Scene t1) {
                if (scene==null && t1!=null){
                    mVideo.fitHeightProperty().bind(t1.heightProperty().subtract(hBoxControls.heightProperty().add(20)));

                }
            }
        });

        labelFullscreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Label label = (Label) mouseEvent.getSource();
                Stage stage = (Stage) label.getScene().getWindow();

                if (stage.isFullScreen()){
                    stage.setFullScreen(false);
                    labelFullscreen.setGraphic(imgFullscreen);
                }else{
                    stage.setFullScreen(true);
                    labelFullscreen.setGraphic(imgExit);
                }
                stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode()==KeyCode.ESCAPE){
                            labelFullscreen.setGraphic(imgFullscreen);
                        }
                    }
                });
            }
        });

        mpVideo.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                bindCurrentTimeLabel();
                sliderTime.setMax(t1.toSeconds());
                labelTotalTime.setText(getTime(t1));

            }
        });

        sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                bindCurrentTimeLabel();
                if (!t1){
                    mpVideo.seek(Duration.seconds(sliderTime.getValue()));
                }
            }
        });

        sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                bindCurrentTimeLabel();
                double currentTime = mpVideo.getCurrentTime().toSeconds();
                if (Math.abs(currentTime-t1.doubleValue())>0.5){
                    mpVideo.seek(Duration.seconds(t1.doubleValue()));
                }
                labelMatchEndVideo(labelCurrentTime.getText(),labelTotalTime.getText());
            }
        });

        mpVideo.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                bindCurrentTimeLabel();
                if (!sliderTime.isValueChanging()){
                    sliderTime.setValue(t1.toSeconds());
                }
                labelMatchEndVideo(labelCurrentTime.getText(),labelTotalTime.getText());
            }
        });

        mpVideo.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                buttonPlay.setGraphic(imgRestart);
                endVideo=true;
                if (!labelCurrentTime.textProperty().equals(labelTotalTime.textProperty())){
                    labelCurrentTime.textProperty().unbind();
                    labelCurrentTime.setText(getTime(mpVideo.getTotalDuration()) + " / ");
                }
            }
        });

        labelNext.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mpVideo.seek(mpVideo.getCurrentTime().add(Duration.seconds(10)));

            }
        });

        labelPrev.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mpVideo.seek(mpVideo.getCurrentTime().subtract(Duration.seconds(10)));

            }
        });
/*
        labelOpenFile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser=new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                if (file!=null){
                    //    mediaVideo = new Media(new File("src/resources/asd.mp3").toURI().toString());
                    //    mpVideo = new MediaPlayer(mediaVideo);
                    //    mVideo.setMediaPlayer(mpVideo);
                    mpVideo.stop();
                    Media media = new Media(file.toURI().toString());
                    mpVideo = new MediaPlayer(media);
                    mVideo.setMediaPlayer(mpVideo);
                    mpVideo.play();

                    mpVideo.setOnReady(new Runnable() {
                        @Override
                        public void run() {
                            labelTotalTime.setText(getTime(mpVideo.getTotalDuration()));
                            bindCurrentTimeLabel();
                            buttonPlay.setGraphic(imgPause);
                            sliderTime.setMax(mpVideo.getTotalDuration().toSeconds());
                        }
                    });
                }
            }
        });
*/











    }

    public void bindCurrentTimeLabel(){
        labelCurrentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mpVideo.getCurrentTime())+" / ";
            }
        },mpVideo.currentTimeProperty()));
    }

    public String getTime(Duration time){
        int hours=(int)time.toHours();
        int minutes=(int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds>59) seconds=seconds%60;
        if (minutes>59) seconds=seconds%60;
        if (hours>59) seconds=seconds%60;

        if (hours>0) return String.format("%d:%02d:%02d",hours,minutes,seconds);
        else return String.format("%02d:%02d",minutes,seconds);
    }
    public void labelMatchEndVideo(String labelTime, String labelTotalTime){
        for (int i=0;i<labelTotalTime.length();i++){
            if (labelTime.charAt(i)!=labelTotalTime.charAt(i)) {
                endVideo = false;
                if (isPlaying) buttonPlay.setGraphic(imgPause);
                else buttonPlay.setGraphic(imgPlay);
                break;
            } else{
                endVideo=true;
                buttonPlay.setGraphic(imgRestart);
            }
        }
    }


}

