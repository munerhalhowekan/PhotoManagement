public class PhotoManager {
    private LinkedList<Photo> photos;

    public PhotoManager() {
        photos = new LinkedList<>();
    }

    public LinkedList<Photo> getPhotos() {
        return photos.copy();
    }

    public void addPhoto(Photo p) {
        photos.insert(p);
    }

    public void deletePhoto(String path) {
        for (int i = 0; i < photos.size(); i++) {
            Photo p = photos.get(i);
            if (p.getPath().equals(path)) {
                photos.remove(p);
                break;
            }
        }
    }
}