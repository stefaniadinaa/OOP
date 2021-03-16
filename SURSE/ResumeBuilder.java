import java.util.TreeSet;

public class ResumeBuilder {
    Information information;
    TreeSet<Education> education;
    TreeSet<Experience> experience;
    public ResumeBuilder() {
        try {
            throw new ResumeIncompleteException("Mai baga");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResumeBuilder(Information information) {
        this.information = information;
        this.education = new TreeSet<>();
        this.experience = new TreeSet<>();
    }

    public ResumeBuilder education(Education education) {
        this.education.add(education);
        return this;
    }

    public ResumeBuilder experience(Experience experience) {
        this.experience.add(experience);
        return this;
    }
}
