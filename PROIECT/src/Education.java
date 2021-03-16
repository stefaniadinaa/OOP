import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

public class Education implements Comparable<Education> {
    private LocalDate start;
    private LocalDate end;
    private String institution;
    private String levelOfEducation;
    private Double gpa;

    public Education(LocalDate start, LocalDate end, String institution, String levelOfEducation, Double gpa) {
        // arunca exceptie de tipul DateTimeException cu mesajul "Date invalide"
        // in cazul in care data de start nu este mai mica decat data de final
        // Observatie: nu am gasit InvalidDatesExpression, probabil ca v-ati referit la DateTimeException
        try {
            if (end != null) {
                if (start.compareTo(end) != -1) {
                    throw new DateTimeException("Date invalide");
                }
            }
        } catch (DateTimeException e) {
            System.out.println(e.getMessage());
        }
        this.start = start;
        this.end = end;
        this.institution = institution;
        this.levelOfEducation = levelOfEducation;
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Education education) {
        if (this.equals(education)) {
            return 0;
        }

        // daca oricare dintre cicluri nu a fost incheiat,
        // fac ordonare crescatoare dupa data de start
        if (end == null || education.end == null) {
            return start.compareTo(education.start);
        }

        // daca datele de ambsolvire sunt egale,
        // sortez in ordine descrescatoare dupa gpa
        if (end.equals(education.end)) {
            return -gpa.compareTo(education.gpa);
        }

        // ordonare descrecatoare dupa data de absolvire
        return -end.compareTo(education.end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(start, education.start) &&
                Objects.equals(end, education.end) &&
                Objects.equals(institution, education.institution) &&
                Objects.equals(levelOfEducation, education.levelOfEducation) &&
                Objects.equals(gpa, education.gpa);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getInstitution() {
        return institution;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
