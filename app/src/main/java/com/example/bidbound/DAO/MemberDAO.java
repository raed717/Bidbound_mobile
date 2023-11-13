package com.example.bidbound.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bidbound.entities.Member;
import com.example.bidbound.entities.Project;

import java.util.List;

@Dao
public interface MemberDAO {

    @Insert
    long insertMember(Member member);

    @Update
    void updateMember(Member member);

    @Delete
    void deletemember(Member member);

    @Query("SELECT * FROM member")
    List<Member> getAllMembers();

    @Query("SELECT * FROM member WHERE id = :memberId")
    Member getMemberById(int memberId);

    @Query("SELECT * FROM member WHERE memberFirstName LIKE :query")
    List<Member> searchMembers(String query);

}

