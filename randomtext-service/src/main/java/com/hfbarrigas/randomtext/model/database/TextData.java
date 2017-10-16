package com.hfbarrigas.randomtext.model.database;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "textdata")
public class TextData implements Serializable {

    private static final long serialVersionUID = 3487495895819393L;

    //delegating it's creation to mongodb
    private String id;
    private String mostFrequentWord;
    private Integer avgParagraphSize;
    private Float avgParagraphProcessingTime;
    private Float totalProcessingTime;
    private Long timestamp;

    public TextData() {
    }

    public TextData(String mostFrequentWord, Integer avgParagraphSize, Float avgParagraphProcessingTime,
                    Float totalProcessingTime, Long timestamp) {
        this.mostFrequentWord = mostFrequentWord;
        this.avgParagraphSize = avgParagraphSize;
        this.avgParagraphProcessingTime = avgParagraphProcessingTime;
        this.totalProcessingTime = totalProcessingTime;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getMostFrequentWord() {
        return mostFrequentWord;
    }

    public Integer getAvgParagraphSize() {
        return avgParagraphSize;
    }

    public Float getAvgParagraphProcessingTime() {
        return avgParagraphProcessingTime;
    }

    public Float getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMostFrequentWord(String mostFrequentWord) {
        this.mostFrequentWord = mostFrequentWord;
    }

    public void setAvgParagraphSize(Integer avgParagraphSize) {
        this.avgParagraphSize = avgParagraphSize;
    }

    public void setAvgParagraphProcessingTime(Float avgParagraphProcessingTime) {
        this.avgParagraphProcessingTime = avgParagraphProcessingTime;
    }

    public void setTotalProcessingTime(Float totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextData textData = (TextData) o;
        return Objects.equals(id, textData.id) &&
                Objects.equals(mostFrequentWord, textData.mostFrequentWord) &&
                Objects.equals(avgParagraphSize, textData.avgParagraphSize) &&
                Objects.equals(avgParagraphProcessingTime, textData.avgParagraphProcessingTime) &&
                Objects.equals(totalProcessingTime, textData.totalProcessingTime) &&
                Objects.equals(timestamp, textData.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mostFrequentWord, avgParagraphSize, avgParagraphProcessingTime, totalProcessingTime, timestamp);
    }

    @Override
    public String toString() {
        return "TextData{" +
                "id='" + id + '\'' +
                ", mostFrequentWord='" + mostFrequentWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                ", timestamp=" + timestamp +
                '}';
    }
}
