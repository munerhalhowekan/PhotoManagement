public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.nbComps = 0;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public PhotoManager getManager() {
        return manager;
    }

    public LinkedList<Photo> getPhotos() {
        nbComps = 0;
        if (manager instanceof InvIndexPhotoManager) {
            LinkedList<Photo> photos = ((InvIndexPhotoManager) manager).getPhotosByCondition(condition);
            // Count comparisons: number of tags * number of photos checked
            if (condition != null && !condition.isEmpty()) {
                String[] tags = condition.split("\\s+AND\\s+");
                nbComps = tags.length * photos.size();
            }
            return photos;
        } else {
            LinkedList<Photo> result = new LinkedList<>();
            LinkedList<Photo> allPhotos = manager.getPhotos();

            if (condition == null || condition.isEmpty()) {
                return allPhotos.copy();
            }

            String[] tags = condition.split("\\s+AND\\s+");
            for (int i = 0; i < allPhotos.size(); i++) {
                Photo photo = allPhotos.get(i);
                boolean satisfies = true;
                for (String tag : tags) {
                    nbComps++;
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

    public int getNbComps() {
        return nbComps;
    }
}