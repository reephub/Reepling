package com.reepling.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "stats",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "statId",
                childColumns = "id",
                onDelete = CASCADE))
@Getter
@Setter
@ToString
@Parcel
public class Stats {

    private static final String TAG = Stats.class.getSimpleName();

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "records")
    public int records;

    @ColumnInfo(name = "likes")
    public int likes;
}
