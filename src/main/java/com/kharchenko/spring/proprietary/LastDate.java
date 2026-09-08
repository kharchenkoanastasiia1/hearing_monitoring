package com.kharchenko.spring.proprietary;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastDate {
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastDateAudiogram;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastDateMedian;

    public LastDate() {
    }

    public LastDate(Date lastDateAudiogram, Date lastDateMedian) throws ParseException {
        if(lastDateAudiogram == null){
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.lastDateAudiogram = sdf.parse("1970-01-01");
        } else{
            this.lastDateAudiogram = lastDateAudiogram;
        }
        if(lastDateMedian == null){
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            this.lastDateMedian = sdf.parse("1970-01-01");
        } else{
            this.lastDateMedian = lastDateMedian;
        }
    }

    public Date getLastDateAudiogram() {
        return lastDateAudiogram;
    }

    public void setLastDateAudiogram(Date lastDateAudiogram) {
        this.lastDateAudiogram = lastDateAudiogram;
    }

    public Date getLastDateMedian() {
        return lastDateMedian;
    }

    public void setLastDateMedian(Date lastDateMedian) {
        this.lastDateMedian = lastDateMedian;
    }
}
