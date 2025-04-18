package com.project.videoflow.model;


import java.io.Serializable;

/**
 * Egy videó feldolgozási feladatot reprezentáló osztály
 */
public class VideoProcessingTask implements Serializable {

    private static final long serialVersionUID = 1L;

    private String videoId;
    private String textOverlay;
    private String position;
    private int fontSize;
    private String textColor;
    private float textOpacity;
    private String backgroundColor;
    private float backgroundOpacity;
    private String outputFormat;
    private String outputQuality;

    // Alapértelmezett konstruktor
    public VideoProcessingTask() {
    }

    // Getterek és setterek

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTextOverlay() {
        return textOverlay;
    }

    public void setTextOverlay(String textOverlay) {
        this.textOverlay = textOverlay;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public float getTextOpacity() {
        return textOpacity;
    }

    public void setTextOpacity(float textOpacity) {
        this.textOpacity = textOpacity;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getBackgroundOpacity() {
        return backgroundOpacity;
    }

    public void setBackgroundOpacity(float backgroundOpacity) {
        this.backgroundOpacity = backgroundOpacity;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getOutputQuality() {
        return outputQuality;
    }

    public void setOutputQuality(String outputQuality) {
        this.outputQuality = outputQuality;
    }

    @Override
    public String toString() {
        return "VideoProcessingTask{" +
                "videoId='" + videoId + '\'' +
                ", textOverlay='" + textOverlay + '\'' +
                ", position='" + position + '\'' +
                ", fontSize=" + fontSize +
                ", outputFormat='" + outputFormat + '\'' +
                ", outputQuality='" + outputQuality + '\'' +
                '}';
    }
}
