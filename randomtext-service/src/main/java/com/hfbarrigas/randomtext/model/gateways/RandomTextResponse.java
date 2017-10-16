package com.hfbarrigas.randomtext.model.gateways;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class RandomTextResponse {
    private String type;
    private Integer amount;
    private String number;
    @JsonProperty("number_max")
    private String numberMax;
    private String format;
    private String time;
    @JsonProperty("text_out")
    private String text;

    public String getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getNumber() {
        return number;
    }

    public String getNumberMax() {
        return numberMax;
    }

    public String getFormat() {
        return format;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomTextResponse that = (RandomTextResponse) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(number, that.number) &&
                Objects.equals(numberMax, that.numberMax) &&
                Objects.equals(format, that.format) &&
                Objects.equals(time, that.time) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, number, numberMax, format, time, text);
    }

    @Override
    public String toString() {
        return "RandomTextResponse{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", number='" + number + '\'' +
                ", numberMax='" + numberMax + '\'' +
                ", format='" + format + '\'' +
                ", time='" + time + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
