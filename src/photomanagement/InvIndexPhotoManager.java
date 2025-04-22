/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package photomanagement;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner; 
public class InvIndexPhotoManager {
    private BST<LinkedList<Photo>> invertedIndex;

    public InvIndexPhotoManager() {
        invertedIndex = new BST<>();
    }

    public void addPhoto(Photo p) {
        LinkedList<String> tags = p.getTags();

        for (tags.findFirst(); !tags.last(); tags.findNext()) {
            String tag = tags.retrieve();
            boolean found = invertedIndex.findkey(tag.hashCode());

            if (!found) {
                invertedIndex.insert(tag.hashCode(), new LinkedList<Photo>());
            }

            LinkedList<Photo> photoList = invertedIndex.retrieve();
            photoList.insert(p);  // assume insert() adds to front or end
        }
    }

    public void deletePhoto(String path) {
        invertedIndex.findFirst();
        while (!invertedIndex.last()) {
            LinkedList<Photo> list = invertedIndex.retrieve();
            boolean removed = false;

            for (list.findFirst(); !list.last(); list.findNext()) {
                if (list.retrieve().getPath().equals(path)) {
                    list.remove();  // remove the photo from the list
                    removed = true;
                    break;
                }
            }

            if (removed && list.empty()) {
                invertedIndex.remove_key(invertedIndex.key());
            } else {
                invertedIndex.findNext();
            }
        }
    }

    public BST<LinkedList<Photo>> getPhotos() {
        return invertedIndex;
    }
}
