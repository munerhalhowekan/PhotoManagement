public class Test {
    public static void main(String[] args) {
        InvIndexPhotoManager manager = new InvIndexPhotoManager();
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);

        Album album1 = new Album("Album1", "bear", manager);
        Album album2 = new Album("Album2", "animal AND grass", manager);

        System.out.println("Get photo1 path and tags:");
        System.out.println("photo1 path: " + photo1.getPath());
        printTags(photo1.getTags());

        System.out.println("Get album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        printPhotos(album2.getPhotos());
        System.out.println("Number of comparisons: " + album2.getNbComps());

        System.out.println("Delete the photo 'bear.jpg': ");
        manager.deletePhoto("bear.jpg");
        System.out.println("Photos in album2 after deletion:");
        printPhotos(album2.getPhotos());
        System.out.println("Number of comparisons: " + album2.getNbComps());
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (String tag : tagsArray) {
            result.insert(tag);
        }
        return result;
    }

    private static void printTags(LinkedList<String> tags) {
        System.out.print("Tags: ");
        for (int i = 0; i < tags.size(); i++) {
            System.out.print(tags.get(i) + " ");
        }
        System.out.println();
    }

    private static void printPhotos(LinkedList<Photo> photos) {
        System.out.println("Photos:");
        for (int i = 0; i < photos.size(); i++) {
            Photo p = photos.get(i);
            System.out.println("Path: " + p.getPath());
            printTags(p.getTags());
        }
    }
}