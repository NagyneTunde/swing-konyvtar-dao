/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nagyg
 */
public interface IModel {
    
    List<Tag> getAllTag() throws SQLException;
    Tag getTagByID(int id) throws SQLException;
    int removeTag(Tag t)throws SQLException;
    int updateTag(Tag t)throws SQLException;
    int addTag(Tag t)throws SQLException;
    
    List<Konyv> getAllKonyv()throws SQLException;
    List<Konyv> getAllKonyv(Tag t)throws SQLException;
    int removeKonyv(Konyv k)throws SQLException;
    int updateKonyv(Konyv k)throws SQLException;
    int addKonyv(Konyv k)throws SQLException;
    
    public void close()throws SQLException;
}
