
public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
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
        LinkedList<Photo> result = new LinkedList<Photo>();
        String[] requiredTags = condition.trim().equals("") ? new String[0] : condition.split("\s+AND\s+");

        ListNode<Photo> current = manager.getPhotos().getHead();
        while (current != null) {
            boolean matches = true;
            for (String tag : requiredTags) {
                nbComps++;
                if (!current.data.getTags().contains(tag)) {
                    matches = false;
                    break;
                }
            }
            if (matches) result.insert(current.data);
            current = current.next;
        }
        return result;
    }

    public int getNbComps() {
        return nbComps;
    }
}
