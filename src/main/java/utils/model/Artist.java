package utils.model;

import java.util.Objects;

public class Artist {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String dateOfDeath;
    private String homeTown;
    private int artWorksCount;

    public Artist(String firstName, String lastName, String dateOfBirth, String dateOfDeath, String homeTown, int artWorksCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.homeTown = homeTown;
        this.artWorksCount = artWorksCount;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public int getArtWorksCount() {
        return artWorksCount;
    }

    public void setArtWorksCount(int artWorksCount) {
        this.artWorksCount = artWorksCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return artWorksCount == artist.artWorksCount &&
                firstName.equals(artist.firstName) &&
                lastName.equals(artist.lastName) &&
                dateOfBirth.equals(artist.dateOfBirth) &&
                dateOfDeath.equals(artist.dateOfDeath) &&
                homeTown.equals(artist.homeTown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, dateOfDeath, homeTown, artWorksCount);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfDeath='" + dateOfDeath + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", artWorksCount=" + artWorksCount +
                '}';
    }
}
