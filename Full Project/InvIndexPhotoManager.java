public class InvIndexPhotoManager extends PhotoManager {
    private BST<String, LinkedList<Photo>> invertedIndex;

    public InvIndexPhotoManager() {
        super();
        invertedIndex = new BST<>();
    }

    @Override
    public void addPhoto(Photo p) {
        super.addPhoto(p);
        LinkedList<String> tags = p.getTags();
        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            LinkedList<Photo> photos = invertedIndex.search(tag);
            if (photos == null) {
                photos = new LinkedList<>();
                invertedIndex.insert(tag, photos);
            }
            photos.insert(p);
        }
    }

    @Override
    public void deletePhoto(String path) {
        LinkedList<Photo> allPhotos = super.getPhotos();
        Photo photoToDelete = null;
         for (int i = 0; i < allPhotos.size(); i++) {
            Photo p = allPhotos.get(i);
            if (p.getPath().equals(path)) {
                photoToDelete = p;
                break;
            }
        }
        if (photoToDelete != null) {
            super.deletePhoto(path);
            LinkedList<String> tags = photoToDelete.getTags();
            for (int i = 0; i < tags.size(); i++) {
                String tag = tags.get(i);
                LinkedList<Photo> photos = invertedIndex.search(tag);
                if (photos != null) {
                    photos.remove(photoToDelete);
                    if (photos.size() == 0) {
                        invertedIndex.delete(tag);
                    }
                }
            }
        }
    }

    public BST<String, LinkedList<Photo>> getInvertedIndex() {
        return invertedIndex;
    }

    public LinkedList<Photo> getPhotosByCondition(String condition) {
        LinkedList<Photo> result = new LinkedList<>();
        if (condition == null || condition.isEmpty()) {
            return super.getPhotos();
        }

        String[] tags = condition.split("\\s+AND\\s+");
        LinkedList<Photo> smallestList = null;
        int smallestSize = Integer.MAX_VALUE;

        for (String tag : tags) {
            LinkedList<Photo> photos = invertedIndex.search(tag);
            if (photos == null) {
                return new LinkedList<>();
            }
            if (photos.size() < smallestSize) {
                smallestSize = photos.size();
                smallestList = photos;
            }
        }

        for (int i = 0; i < smallestList.size(); i++) {
            Photo photo = smallestList.get(i);
            boolean satisfies = true;
            for (String tag : tags) {
                if (!photo.getTags().contains(tag)) {
                    satisfies = false;
                    break;
                }
            }
            if (satisfies) {
                result.insert(photo);
            }
        }
        return result;
    }
}