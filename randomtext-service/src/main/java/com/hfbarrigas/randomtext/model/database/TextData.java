package com.hfbarrigas.randomtext.model.database;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "text-data")
public class TextData implements Serializable {

    private static final long serialVersionUID = 3487495895819393L;

    //delegating it's creation to mongodb
    private String id;
    private String mostFrequentWord;
    private Integer avgParagraphSize;
    private Float avgParagraphProcessingTime;
    private Float totalProcessingTime;

    public TextData() {
    }

    public TextData(String mostFrequentWord, Integer avgParagraphSize, Float avgParagraphProcessingTime, Float totalProcessingTime) {
        this.mostFrequentWord = mostFrequentWord;
        this.avgParagraphSize = avgParagraphSize;
        this.avgParagraphProcessingTime = avgParagraphProcessingTime;
        this.totalProcessingTime = totalProcessingTime;
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
                Objects.equals(totalProcessingTime, textData.totalProcessingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mostFrequentWord, avgParagraphSize, avgParagraphProcessingTime, totalProcessingTime);
    }

    @Override
    public String toString() {
        return "TextData{" +
                "id='" + id + '\'' +
                ", mostFrequentWord='" + mostFrequentWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                '}';
    }

}
