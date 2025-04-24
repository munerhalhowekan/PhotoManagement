
public class Test {
    public static void main(String[] args) {
        PhotoManager manager = new PhotoManager();

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

        System.out.println("\nGet album2 name, condition, and photos:");
        System.out.println("album2 name: " + album2.getName());
        System.out.println("album2 condition: " + album2.getCondition());
        printPhotoPaths(album2.getPhotos());

        System.out.println("\nDelete the photo 'bear.jpg':");
        manager.deletePhoto("bear.jpg");

        System.out.println("\nGet updated photos in album2:");
        printPhotoPaths(album2.getPhotos());

        System.out.println("\nNumber of comparisons used to retrieve photos in album2:");
        System.out.println(album2.getNbComps());
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<String>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (String tag : tagsArray) {
            result.insert(tag);
        }
        return result;
    }

    private static void printTags(LinkedList<String> tags) {
        ListNode<String> current = tags.getHead();
        System.out.print("Tags: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    private static void printPhotoPaths(LinkedList<Photo> photos) {
        ListNode<Photo> current = photos.getHead();
        System.out.println("Photos:");
        while (current != null) {
            System.out.println(" - " + current.data.getPath());
            current = current.next;
        }
    }
}
