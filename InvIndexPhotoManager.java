
public class InvIndexPhotoManager {
    private LinkedList<Photo> photos;
    private BST<LinkedList<Photo>> index;

    public InvIndexPhotoManager() {
        photos = new LinkedList<Photo>();
        index = new BST<LinkedList<Photo>>();
    }

    public void addPhoto(Photo p) {
        photos.insert(p);
        ListNode<String> tagNode = p.getTags().getHead();
        while (tagNode != null) {
            String tag = tagNode.data;
            LinkedList<Photo> photoList = index.search(tag);
            if (photoList == null) {
                photoList = new LinkedList<Photo>();
            }
            photoList.insert(p);
            index.insert(tag, photoList);
            tagNode = tagNode.next;
        }
    }

    public void deletePhoto(String path) {
        ListNode<Photo> current = photos.getHead();
        while (current != null) {
            if (current.data.getPath().equals(path)) {
                Photo toDelete = current.data;
                ListNode<String> tagNode = toDelete.getTags().getHead();
                while (tagNode != null) {
                    String tag = tagNode.data;
                    LinkedList<Photo> tagPhotos = index.search(tag);
                    if (tagPhotos != null) {
                        tagPhotos.remove(toDelete);
                        if (tagPhotos.getHead() == null) {
                            index.delete(tag);
                        } else {
                            index.insert(tag, tagPhotos);
                        }
                    }
                    tagNode = tagNode.next;
                }
                photos.remove(toDelete);
                return;
            }
            current = current.next;
        }
    }

    public BST<LinkedList<Photo>> getPhotos() {
        return index;
    }
}
