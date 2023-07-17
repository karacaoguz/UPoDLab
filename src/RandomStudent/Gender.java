package RandomStudent;

public enum Gender {
    MALE("Bey"),
    FEMALE("Hanım");
    private final String postFix;

    Gender(String postFix) {
        this.postFix = postFix;
    }

    public String getPostFix() {
        return postFix;
    }
}
