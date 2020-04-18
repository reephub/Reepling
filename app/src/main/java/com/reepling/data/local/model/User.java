package com.reepling.data.local.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(tableName = "users")
@Getter
@Setter
@ToString
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "phone_number")
    public String phone;

    @ColumnInfo(name = "login")
    public String login;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "admin")
    public boolean isAdmin = false;

    @ColumnInfo(name = "premium")
    public boolean isPremium = false;



    public User(String username, String firstName, String lastName, String phone, String login, String password, @Nullable String country, @Nullable boolean isPremium, boolean isAdmin) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setLogin(login);
        setPassword(encodePassword(password));
        setCountry(country);
        setPremium(isPremium);
        setAdmin(isAdmin);
    }


    public static String encodePassword(String passwordToEncode) {

        String sha256hex = Hashing.sha256()
                .hashString(passwordToEncode, StandardCharsets.UTF_8)
                .toString();

        return sha256hex;

        // SHA 3
        /*
        final MessageDigest digest = MessageDigest.getInstance(SHA3_256);
        final byte[] hashbytes = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        String sha3_256hex = bytesToHex(hashbytes);
        */
    }


}