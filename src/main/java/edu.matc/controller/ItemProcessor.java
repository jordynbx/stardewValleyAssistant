package edu.matc.controller;

import edu.matc.entity.Crop;
import edu.matc.entity.Item;
import edu.matc.entity.Note;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;

@Log4j2
public class ItemProcessor {

    /**
     * This method processes items for output on results.jsp
     * @param session
     * @param request
     * @param itemId
     */
//    public HttpServletRequest processItem(HttpSession session, HttpServletRequest request, int itemId) {
//
//        GenericDao<Item> itemDao = new GenericDao<>(Item.class);
//        Item item = itemDao.getById(itemId);
//
//        if (item.getType().equals("crop")) {
//            GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);
//
//            Crop crop = cropDao.getByUniquePropertyEqualInt("itemId", itemId);
//            request.setAttribute("crop", crop);
//        } else if (item.getType().equals("forage")) {
//            // In the future there will be processing here
//        }
//
//        return request;
//    }

    public Crop processCrop(int itemId) {

        GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);
        return cropDao.getByUniquePropertyEqualInt("itemId", itemId);
    }

    /**
     * This method processes user notes for output on results.jsp
     * @param session
     * @param request
     * @param itemId
     */
    public List<Note> generateNotes(int userId, int itemId) {

        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        List<Note> notes = noteDao.getListByMultipleProperties("user", userId, "item", itemId);

        return notes;
    }

}
