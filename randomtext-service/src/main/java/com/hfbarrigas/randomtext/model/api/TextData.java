package com.hfbarrigas.randomtext.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import java.util.Objects;

public class TextData {

    //jackson purposes
    public TextData() {
    }

    public TextData(Builder builder) {
        this.avgParagraphProcessingTime = builder.avgParagraphProcessingTime;
        this.avgParagraphSize = builder.avgParagraphSize;
        this.totalProcessingTime = builder.totalProcessingTime;
        this.mostFrequentWord = builder.mostFrequestWord;
    }

    @JsonProperty("most_frequent_word")
    private String mostFrequentWord;

    @JsonProperty("avg_paragraph_size")
    private Integer avgParagraphSize;

    @JsonProperty("avg_paragraph_processing_time")
    private Float avgParagraphProcessingTime;

    @JsonProperty("total_processing_time")
    private Float totalProcessingTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextData textData = (TextData) o;
        return Objects.equals(mostFrequentWord, textData.mostFrequentWord) &&
                Objects.equals(avgParagraphSize, textData.avgParagraphSize) &&
                Objects.equals(avgParagraphProcessingTime, textData.avgParagraphProcessingTime) &&
                Objects.equals(totalProcessingTime, textData.totalProcessingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mostFrequentWord, avgParagraphSize, avgParagraphProcessingTime, totalProcessingTime);
    }

    @Override
    public String toString() {
        return "TextData{" +
                "mostFrequentWord='" + mostFrequentWord + '\'' +
                ", avgParagraphSize=" + avgParagraphSize +
                ", avgParagraphProcessingTime=" + avgParagraphProcessingTime +
                ", totalProcessingTime=" + totalProcessingTime +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String mostFrequestWord;
        private Integer avgParagraphSize;
        private Float avgParagraphProcessingTime;
        private Float totalProcessingTime;

        public Builder withMostFrequentWord(@Nonnull String mostFrequentWord) {
            this.mostFrequestWord = mostFrequentWord;
            return this;
        }

        public Builder withAvgParagraphSize(@Nonnull Integer avgParagraphSize) {
            this.avgParagraphSize = avgParagraphSize;
            return this;
        }

        public Builder withAvgParagraphProcessingTime(@Nonnull Float avgParagraphProcessingTime) {
            this.avgParagraphProcessingTime = avgParagraphProcessingTime;
            return this;
        }

        public Builder withTotalProcessingTime(@Nonnull Float totalProcessingTime) {
            this.totalProcessingTime = totalProcessingTime;
            return this;
        }

        public TextData build() {
            return new TextData(this);
        }
    }
}
