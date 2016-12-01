package com.davidmiguel.gobees.data.model.mothers;

import com.davidmiguel.gobees.data.model.Record;
import com.davidmiguel.gobees.data.model.Recording;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Mother class for recordings.
 * Using Object Mother, Test Data Builder and Builder patter.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RecordingMother {

    private static final int NUM_RECORDS = 10;
    private static final int MIN_INTERVAL_RECORDS = 1;
    private Date date;
    private List<Record> records;

    public RecordingMother() {
        setValues(NUM_RECORDS);
    }

    public RecordingMother(int numRecords) {
        setValues(numRecords);
    }

    private static RecordingMother newRecording() {
        return new RecordingMother();
    }

    private static RecordingMother newRecording(int numRecords) {
        return new RecordingMother(numRecords);
    }

    /**
     * Generate random recording with 10 records.
     *
     * @return recording.
     */
    public static Recording newDefaultRecording() {
        return RecordingMother.newRecording().build();
    }

    /**
     * Generate random recording with numRecords.
     *
     * @param numRecords number of records to generate.
     * @return recording.
     */
    public static Recording newDefaultRecording(int numRecords) {
        return RecordingMother.newRecording(numRecords).build();
    }

    private void setValues(int numRecords) {
        Random r = new Random(System.nanoTime());
        date = generateRandomDate(r);
        records = generateRecords(numRecords, date, r);
    }

    private Recording build() {
        return new Recording(date, records);
    }

    private List<Record> generateRecords(int numRecords, Date recordingDate, Random r) {
        List<Record> records = new ArrayList<>(numRecords);
        for (int i = 0; i < numRecords; i++) {
            records.add(RecordMother.newDefaultRecord(
                    sumTimeToDate(recordingDate, 0, 0, MIN_INTERVAL_RECORDS, 0)));
        }
        return records;
    }

    private Date generateRandomDate(Random r) {
        Date today = new Date();
        return new Date(today.getTime() - ((1000 * 60 * 60 * 24) * r.nextInt(100)));
    }

    private Date sumTimeToDate(Date date, int days, int hours, int min, int sec) {
        return new Date(date.getTime() - ((1000 * (60 * sec) * (60 * min) * (24 * hours)) * days));
    }
}
