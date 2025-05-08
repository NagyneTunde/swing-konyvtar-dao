/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nagyg
 */
public class DBModel implements IModel {

    private Connection conn;
    private PreparedStatement getAllTagPstmt;
    private PreparedStatement getTagByIDPstmt;
    private PreparedStatement removeTagPstmt;
    private PreparedStatement updateTagPstmt;
    private PreparedStatement addTagPstmt;

    private PreparedStatement getAllKonyvPstmt;
    private PreparedStatement getAllKonyvByTagPstmt;
    private PreparedStatement removeKonyvPstmt;
    private PreparedStatement updateKonyvPstmt;
    private PreparedStatement addKonyvPstmt;

    public DBModel(Connection conn) throws SQLException {
        this.conn = conn;
        getAllTagPstmt = conn.prepareStatement("SELECT * FROM tag");
        getTagByIDPstmt = conn.prepareStatement("SELECT * FROM tag WHERE id=?");
        removeTagPstmt = conn.prepareStatement("DELETE FROM tag WHERE id=?");
        updateTagPstmt = conn.prepareStatement("UPDATE tag SET nev=?, email=? WHERE id=?");
        addTagPstmt = conn.prepareStatement("INSERT INTO tag (nev,email) VALUES (?,?)");

        getAllKonyvPstmt = conn.prepareStatement("SELECT * FROM konyv");
        getAllKonyvByTagPstmt = conn.prepareStatement("SELECT * FROM konyv WHERE tag_id=?");
        removeKonyvPstmt = conn.prepareStatement("DELETE FROM konyv WHERE id=?");
        updateKonyvPstmt = conn.prepareStatement("UPDATE konyv SET szerzo=?, cim=?, oldalszam=?, tag_id=? WHERE id=?");
        addKonyvPstmt = conn.prepareStatement("INSERT INTO konyv (szerzo, cim, oldalszam, tag_id) VALUES (?,?,?,?)");
    }

    @Override
    public List<Tag> getAllTag() throws SQLException {
        List<Tag> tagok = new ArrayList<>();

        ResultSet rs = getAllTagPstmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nev = rs.getString("nev");
            String email = rs.getString("email");

            Tag t = new Tag(id, nev, email);
            tagok.add(t);
        }
        rs.close();
        return tagok;
    }

    @Override
    public Tag getTagByID(int id) throws SQLException {
        getTagByIDPstmt.setInt(1, id);
        ResultSet rs = getTagByIDPstmt.executeQuery();
        Tag t = null;

        if (rs.next()) {
            t = new Tag(
                    rs.getInt("id"),
                    rs.getString("nev"),
                    rs.getString("email"));
        }
        rs.close();
        return t;
    }

    @Override
    public int removeTag(Tag t) throws SQLException {
        removeTagPstmt.setInt(1, t.getId());
        return removeTagPstmt.executeUpdate();
    }

    @Override
    public int updateTag(Tag t) throws SQLException {
        updateTagPstmt.setString(1, t.getNev());
        updateTagPstmt.setString(2, t.getEmail());
        updateTagPstmt.setInt(3, t.getId());
        return updateTagPstmt.executeUpdate();
    }

    @Override
    public int addTag(Tag t) throws SQLException {
        addTagPstmt.setString(1, t.getNev());
        addTagPstmt.setString(2, t.getEmail());
        return addTagPstmt.executeUpdate();
    }

    @Override
    public List<Konyv> getAllKonyv() throws SQLException {
        ResultSet rs = getAllKonyvPstmt.executeQuery();
        List<Konyv> konyvek = new ArrayList<>();

        while (rs.next()) {
            Konyv k = new Konyv(
                    rs.getInt("id"),
                    rs.getString("szerzo"),
                    rs.getString("cim"),
                    rs.getInt("oldalszam"),
                    rs.getInt("tag_id"));
            konyvek.add(k);
        }
        rs.close();
        return konyvek;
    }

    @Override
    public List<Konyv> getAllKonyv(Tag t) throws SQLException {
        getAllKonyvByTagPstmt.setInt(1, t.getId());

        ResultSet rs = getAllKonyvByTagPstmt.executeQuery();
        List<Konyv> konyvek = new ArrayList<>();
        while (rs.next()) {
            Konyv k = new Konyv(
                    rs.getInt("id"),
                    rs.getString("szerzo"),
                    rs.getString("cim"),
                    rs.getInt("oldalszam"),
                    rs.getInt("tag_id"));
            konyvek.add(k);
        }
        rs.close();
        return konyvek;

    }

    @Override
    public int removeKonyv(Konyv k) throws SQLException {
        removeKonyvPstmt.setInt(1, k.getId());
        return removeKonyvPstmt.executeUpdate();
    }

    @Override
    public int updateKonyv(Konyv k) throws SQLException {
        updateKonyvPstmt.setString(1, k.getSzerzo());
        updateKonyvPstmt.setString(2, k.getCim());
        updateKonyvPstmt.setInt(3, k.getOldalszam());
        updateKonyvPstmt.setInt(4, k.getTagId());
        updateKonyvPstmt.setInt(5, k.getId());

        return updateKonyvPstmt.executeUpdate();
    }

    @Override
    public int addKonyv(Konyv k) throws SQLException {
        addKonyvPstmt.setString(1, k.getSzerzo());
        addKonyvPstmt.setString(2, k.getCim());
        addKonyvPstmt.setInt(3, k.getOldalszam());
        addKonyvPstmt.setInt(4, k.getTagId());
        return addKonyvPstmt.executeUpdate();
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }

}
